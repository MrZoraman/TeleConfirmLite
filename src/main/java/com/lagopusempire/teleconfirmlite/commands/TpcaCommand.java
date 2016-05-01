package com.lagopusempire.teleconfirmlite.commands;

import com.google.common.collect.ImmutableMap;
import com.lagopusempire.teleconfirmlite.Permissions;
import com.lagopusempire.teleconfirmlite.RequestDetails;
import com.lagopusempire.teleconfirmlite.TeleConfirmLite;
import com.lagopusempire.teleconfirmlite.messages.Messages;
import java.util.Map;
import java.util.Optional;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class TpcaCommand extends CommandBase {
    
    private static boolean preventCrossWorldTp = false;
    
    public static void setPreventCrossWorldTp(boolean value) {
        preventCrossWorldTp = value;
    }

    @Override
    protected void register(TeleConfirmLite plugin, CommandManager commandManager) {
         CommandSpec cmdSpec = CommandSpec.builder()
                .description(Text.of("Accepts a teleport request."))
                .permission(Permissions.TPCA.getNode())
                .permission(Permissions.USER.getNode())
                .executor(this)
                .build();
         commandManager.register(plugin, cmdSpec, "tpca", "tpaccept", "tpyes");
    }

    @Override
    protected CommandResult executeCommand(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            src.sendMessage(getMessageManager().getMessage(Messages.PLAYER_ONLY).toText());
            return CommandResult.success();
        }
        
        Player sender = (Player) src;
        
        RequestDetails details = getManager().getRequestDetails(sender.getUniqueId());
        
        if(details == null) {
            sender.sendMessage(getMessageManager().getMessage(Messages.NO_PENDING_REQUESTS).toText());
            return CommandResult.success();
        }
        
        Map<String, String> msgArgs = ImmutableMap.of(
                "target", details.getTargetName(),
                "sender", sender.getName()
        );
        
        Optional<Player> targetOpt = Sponge.getServer().getPlayer(details.getTarget());
        if(!targetOpt.isPresent()) {
            sender.sendMessage(getMessageManager().getMessage(Messages.TARGET_OFFLINE).apply(msgArgs).toText());
            return CommandResult.success();
        }
        
        Player target = targetOpt.get();
        
        if(preventCrossWorldTp && !target.getWorld().equals(sender.getWorld())) {
            sender.sendMessage(getMessageManager().getMessage(Messages.SENDER_CROSS_WORLD_FAIL).apply(msgArgs).toText());
            target.sendMessage(getMessageManager().getMessage(Messages.TARGET_CROSS_WORLD_FAIL).apply(msgArgs).toText());
            return CommandResult.success();
        }
        
        switch(details.getType()) {
            case COME_HERE:
                getManager().setPriorLocation(target);
                target.setLocation(sender.getLocation());
                break;
            case GO_THERE:
                getManager().setPriorLocation(sender);
                sender.setLocation(target.getLocation());
        }
        
        getManager().clearRequestDetails(sender.getUniqueId());
        
        return CommandResult.success();
    }
}
