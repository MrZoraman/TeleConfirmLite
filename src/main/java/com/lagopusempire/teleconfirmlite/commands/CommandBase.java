package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.RequestManager;
import com.lagopusempire.teleconfirmlite.TeleConfirmLite;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public abstract class CommandBase implements CommandExecutor {
    private RequestManager manager = null;
    private MessageManager mm = null;
    
    private boolean isReady = false;
    
    private boolean pluginIsEnabled = true;
    
    @Override
    public final CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(!pluginIsEnabled) {
            src.sendMessage(
                    Text.builder()
                    .append(Text.of("TeleConfirmLite is not working properly! See the log for details."))
                    .color(TextColors.RED)
                    .build()
            );
            return CommandResult.success();
        }
        
        return executeCommand(src, args);
    }
    
    protected abstract CommandResult executeCommand(CommandSource src, CommandContext args) throws CommandException;
    protected abstract void register(TeleConfirmLite plugin, CommandManager commandManager);
    
    void setManagers(RequestManager manager, MessageManager mm) {
        this.manager = manager;
        this.mm = mm;
        
        isReady = true;
    }
    
    void setEnabled(boolean enabled) {
        this.pluginIsEnabled = enabled;
    }
    
    protected RequestManager getManager() {
        if(!isReady) {
            throw new IllegalStateException("TeleConfirmLite commands are not propery initilized yet!");
        }
        
        return manager;
    }
    
    protected MessageManager getMessageManager() {
        if(!isReady) {
            throw new IllegalStateException("TeleConfirmLite commands are not propery initilized yet!");
        }
        
        return mm;
    }
}
