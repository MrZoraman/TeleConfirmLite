package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.messages.Messages;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

public class TpcToggleCmd extends CommandBase {

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
