package com.lagopusempire.teleconfirmlite.commands;

import com.google.common.collect.ImmutableMap;
import com.lagopusempire.teleconfirmlite.AcceptResultPack;
import com.lagopusempire.teleconfirmlite.messages.Messages;
import java.util.Map;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;

public class TpcaCommand extends CommandBase {

    @Override
    protected CommandResult executeCommand(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            src.sendMessage(getMessageManager().getMessage(Messages.PLAYER_ONLY).toText());
            return CommandResult.success();
        }
        
        Player sender = (Player) src;
        AcceptResultPack pack = getManager().accept(sender);
        
        Map<String, String> msgArgs = ImmutableMap.of(
                "target", pack.getTargetName()
        );
        
        switch(pack.getResult()) {
            case NO_PENDING_REQUEST:
                sender.sendMessage(getMessageManager().getMessage(Messages.NO_PENDING_REQUESTS).toText());
                break;
            case TARGET_OFFLINE:
                sender.sendMessage(getMessageManager().getMessage(Messages.TARGET_OFFLINE).apply(msgArgs).toText());
                break;
            case SUCCESS:
                Location loc = pack.getLoc();
                sender.setLocation(loc);
        }
        
        return CommandResult.success();
    }
    
}
