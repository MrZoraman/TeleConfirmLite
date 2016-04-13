package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.RequestManager;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import org.spongepowered.api.command.spec.CommandExecutor;

public abstract class CommandBase implements CommandExecutor {
    private RequestManager manager;
    private MessageManager mm;
    
    CommandBase(RequestManager manager, MessageManager mm) {
        this.manager = manager;
        this.mm = mm;
    }
    
    void setManagers(RequestManager manager, MessageManager mm) {
        this.manager = manager;
        this.mm = mm;
    }
    
    protected RequestManager getManager() {
        return manager;
    }
    
    protected MessageManager getMessageManager() {
        return mm;
    }
}
