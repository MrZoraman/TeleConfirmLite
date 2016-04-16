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

public class TpchereCommand extends CommandBase {

    @Override
    protected CommandResult executeCommand(CommandSource src, CommandContext args) throws CommandException {
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
        
        boolean success = getManager().request(sender, target, RequestType.COME_HERE);
        Messages senderMessage = success ? Messages.SENDER_REQUEST_HERE : Messages.SENDER_ALREADY_HAS_REQUEST;
        Messages targetMessage = success ? Messages.TARGET_REQUEST_HERE : Messages.TARGET_ALREADY_HAS_REQUEST;
        
        sender.sendMessage(getMessageManager().getMessage(senderMessage).apply(msgArgs).toText());
        target.sendMessage(getMessageManager().getMessage(targetMessage).apply(msgArgs).toText());
        
        return CommandResult.success();
    }
    
}
