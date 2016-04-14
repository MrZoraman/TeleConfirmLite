package com.lagopusempire.teleconfirmlite.commands;

import com.google.common.collect.ImmutableMap;
import com.lagopusempire.teleconfirmlite.RequestDetails;
import com.lagopusempire.teleconfirmlite.messages.Messages;
import java.util.Map;
import java.util.Optional;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

public class TpcdCommand extends CommandBase {

    @Override
    protected CommandResult executeCommand(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            src.sendMessage(getMessageManager().getMessage(Messages.PLAYER_ONLY).toText());
            return CommandResult.success();
        }
        
        Player sender = (Player) src;
        
        RequestDetails details = getManager().deny(sender.getUniqueId());
        if(details == null) {
            sender.sendMessage(getMessageManager().getMessage(Messages.NO_PENDING_REQUESTS).toText());
            return CommandResult.success();
        }
        
        Map<String, String> msgArgs = ImmutableMap.of(
            "target", details.getTargetName(),
            "sender", sender.getName()
        );
        
        sender.sendMessage(getMessageManager().getMessage(Messages.SENDER_REQUEST_DENIED).apply(msgArgs).toText());
        
        Optional<Player> target = Sponge.getServer().getPlayer(details.getTarget());
        if(target.isPresent()) {
            target.get().sendMessage(getMessageManager().getMessage(Messages.TARGET_REQUEST_DENIED).apply(msgArgs).toText());
        }
        
        return CommandResult.success();
    }
}
