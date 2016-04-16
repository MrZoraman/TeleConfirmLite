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

public class TpcToggleCmd extends CommandBase {

    @Override
    protected void register(TeleConfirmLite plugin, CommandManager commandManager) {
        CommandSpec cmdSpec = CommandSpec.builder()
                .description(Text.of("Toggle teleportation,"))
                .permission(Permissions.TOGGLE.getNode())
                .permission(Permissions.USER.getNode())
                .permission(Permissions.ALL.getNode())
                .executor(this)
                .build();
        commandManager.register(plugin, cmdSpec, "tpctoggle", "tptoggle");
    }

    @Override
    protected CommandResult executeCommand(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            src.sendMessage(getMessageManager().getMessage(Messages.PLAYER_ONLY).toText());
            return CommandResult.success();
        }
        
        Player sender = (Player) src;
        
        boolean acceptingRequests = getManager().toggleAcceptingRequests(sender.getUniqueId());
        if(acceptingRequests) {
            sender.sendMessage(getMessageManager().getMessage(Messages.SENDER_NOW_ACCEPTING_REQUESTS).toText());
        } else {
            sender.sendMessage(getMessageManager().getMessage(Messages.SENDER_NO_LONGER_ACCEPTING_REQUESTS).toText());
        }
        
        return CommandResult.success();
    }
}
