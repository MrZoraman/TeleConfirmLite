package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.TeleConfirmLite;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ReloadCommand implements CommandExecutor {
    
    private final TeleConfirmLite plugin;
    
    ReloadCommand(TeleConfirmLite plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        boolean success = plugin.load();
        if(success) {
            src.sendMessage(
                    Text.builder()
                    .append(Text.of("TeleConfirmLite reloaded successfully!"))
                    .color(TextColors.GREEN)
                    .build()
            );
        } else {
            src.sendMessage(
                    Text.builder()
                    .append(Text.of("Something went wrong while reloading TeleConfirmLite. Check the logs for details."))
                    .color(TextColors.RED)
                    .build()
            );
        }
        
        return CommandResult.success();
    }
}
