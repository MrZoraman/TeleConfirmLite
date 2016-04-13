package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.RequestManager;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import org.spongepowered.api.command.spec.CommandExecutor;

public abstract class CommandBase implements CommandExecutor {
    private RequestManager manager = null;
    private MessageManager mm = null;
    
    private boolean isReady = false;
    
    void setManagers(RequestManager manager, MessageManager mm) {
        this.manager = manager;
        this.mm = mm;
        
        isReady = true;
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
