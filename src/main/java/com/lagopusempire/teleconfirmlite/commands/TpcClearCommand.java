package com.lagopusempire.teleconfirmlite.commands;

import com.google.common.collect.ImmutableMap;
import com.lagopusempire.teleconfirmlite.Permissions;
import com.lagopusempire.teleconfirmlite.RequestDetails;
import com.lagopusempire.teleconfirmlite.TeleConfirmLite;
import com.lagopusempire.teleconfirmlite.messages.Messages;
import java.util.Map;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class TpcClearCommand extends CommandBase {

    @Override
    protected void register(TeleConfirmLite plugin, CommandManager commandManager) {
        CommandSpec cmdSpec = CommandSpec.builder()
                .description(Text.of("Removes a pending request."))
                .permission(Permissions.CLEAR.getNode())
                .permission(Permissions.USER.getNode())
                .executor(this)
                .build();
        commandManager.register(plugin, cmdSpec, "tpcclear", "tpaclear", "tpclear");
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
        
        sender.sendMessage(getMessageManager().getMessage(Messages.REQUEST_CLEARED).apply(msgArgs).toText());
        
        return CommandResult.success();
    }
}
