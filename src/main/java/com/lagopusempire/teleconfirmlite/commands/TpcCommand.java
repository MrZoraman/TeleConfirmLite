package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.RequestManager;
import com.lagopusempire.teleconfirmlite.RequestType;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import com.lagopusempire.teleconfirmlite.messages.Messages;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

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
        sender.sendMessage(Text.of("Request to teleport to " + target.getName() + " sent."));
        target.sendMessage(Text.of(sender.getName() + " wants to teleport to you!"));
        
        return CommandResult.success();
    }
}
