package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.RequestManager;
import com.lagopusempire.teleconfirmlite.TeleConfirmLite;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import java.util.HashSet;
import java.util.Set;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandRegistrar {
    
    private final Set<CommandBase> commands = new HashSet<>();
    
    public CommandRegistrar(TeleConfirmLite plugin) {
        CommandSpec reloadCmdSpec = CommandSpec.builder()
                .description(Text.of("Reloads TeleConfirmLite."))
                .permission("tcl.reload")
                .executor(new ReloadCommand(plugin))
                .build();
        Sponge.getCommandManager().register(plugin, reloadCmdSpec, "tpcreload", "tpareload");
        
        commands.add(new TpcClearCommand());
        commands.add(new TpcCommand());
        commands.add(new TpcToggleCmd());
        commands.add(new TpcaCommand());
        commands.add(new TpcbackCommand());
        commands.add(new TpcdCommand());
        commands.add(new TpchereCommand());
    }
    
    public void initCommands(RequestManager manager, MessageManager mm) {
        for(CommandBase cmd : commands) {
            cmd.setManagers(manager, mm);
        }
    }
    
    public void setEnabled(boolean enabled) {
        for(CommandBase cmd : commands) {
            cmd.setEnabled(enabled);
        }
    }
}
