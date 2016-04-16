package com.lagopusempire.teleconfirmlite.commands;

import com.google.common.collect.ImmutableMap;
import com.lagopusempire.teleconfirmlite.RequestType;
import com.lagopusempire.teleconfirmlite.messages.Messages;
import java.util.Map;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

/**
 *
 * @author MrZoraman
 */
public class TpcCommand extends CommandBase {
    
    @Override
    public CommandResult executeCommand(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            src.sendMessage(getMessageManager().getMessage(Messages.PLAYER_ONLY).toText());
            return CommandResult.success();
        }
        
        Player sender = (Player) src;
        Player target = args.<Player>getOne("playername").get();
        
        Map<String, String> msgArgs = ImmutableMap.of(
                "sender", sender.getName(),
                "target", target.getName()
        );
        
        if(getManager().getRequestDetails(sender.getUniqueId()) != null) {
            sender.sendMessage(getMessageManager().getMessage(Messages.SENDER_ALREADY_HAS_REQUEST).apply(msgArgs).toText());
            target.sendMessage(getMessageManager().getMessage(Messages.TARGET_ALREADY_HAS_REQUEST).apply(msgArgs).toText());
        } else {
            getManager().request(sender, target.getUniqueId(), RequestType.GO_THERE);
            
            sender.sendMessage(getMessageManager().getMessage(Messages.SENDER_REQUEST_TO).apply(msgArgs).toText());
            target.sendMessage(getMessageManager().getMessage(Messages.TARGET_REQUEST_TO).apply(msgArgs).toText());
        }
        
        return CommandResult.success();
    }
}
