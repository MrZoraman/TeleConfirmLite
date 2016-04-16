package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.Permissions;
import com.lagopusempire.teleconfirmlite.TeleConfirmLite;
import com.lagopusempire.teleconfirmlite.messages.Messages;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;

public class TpcbackCommand extends CommandBase {

    @Override
    protected void register(TeleConfirmLite plugin, CommandManager commandManager) {
        CommandSpec cmdSpec = CommandSpec.builder()
                .description(Text.of("Returns you to your previous location."))
                .permission(Permissions.BACK.getNode())
                .executor(this)
                .build();
        commandManager.register(plugin, cmdSpec, "tpcback", "tpaback");
    }

    @Override
    protected CommandResult executeCommand(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            src.sendMessage(getMessageManager().getMessage(Messages.PLAYER_ONLY).toText());
            return CommandResult.success();
        }
        
        Player sender = (Player) src;
        
        Location priorLoc = getManager().getPriorLoc(sender.getUniqueId());
        if(priorLoc == null) {
            sender.sendMessage(getMessageManager().getMessage(Messages.NO_PRIOR_LOC).toText());
        } else {
            sender.setLocation(priorLoc);
        }
        
        return CommandResult.success();
    }
}
