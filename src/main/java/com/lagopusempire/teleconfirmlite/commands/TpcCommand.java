package com.lagopusempire.teleconfirmlite.commands;

import com.google.common.collect.ImmutableMap;
import com.lagopusempire.teleconfirmlite.RequestManager;
import com.lagopusempire.teleconfirmlite.RequestType;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import com.lagopusempire.teleconfirmlite.messages.Messages;
import java.util.Map;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextElement;

/**
 *
 * @author MrZoraman
 */
public class TpcCommand implements CommandExecutor {
    private final RequestManager manager;
    private final MessageManager mm;
    
    public TpcCommand(RequestManager manager, MessageManager mm) {
        this.manager = manager;
        this.mm = mm;
    }
    
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            src.sendMessage(mm.getMessage(Messages.PLAYER_ONLY));
            return CommandResult.success();
        }
        
        Player sender = (Player) src;
        Player target = args.<Player>getOne("playername").get();
        
        manager.request(sender, target, RequestType.GO_THERE);
        
        Map<String, TextElement> msgArgs = ImmutableMap.of(
                "sender", Text.of("mohm"),
                "target", Text.of("sas")
        );
        
        sender.sendMessage(mm.getMessage(Messages.SENDER_REQUEST_TO).apply(msgArgs).build());
        target.sendMessage(mm.getMessage(Messages.TARGET_REQUEST_TO).apply(msgArgs).build());
        
        return CommandResult.success();
    }
}
