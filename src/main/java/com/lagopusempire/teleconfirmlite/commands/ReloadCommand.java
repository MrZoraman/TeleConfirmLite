package com.lagopusempire.teleconfirmlite.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

public class ReloadCommand implements CommandExecutor {
    
    
    
    @Override
    public CommandResult execute(CommandSource cs, CommandContext cc) throws CommandException {
        return CommandResult.success();
    }
}
