package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.RequestManager;
import com.lagopusempire.teleconfirmlite.TeleConfirmLite;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import java.util.HashSet;
import java.util.Set;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

/**
 *
 * @author MrZoraman
 */
public class CommandRegistrar {
    
    private final Set<CommandBase> commands = new HashSet<>();
    
    public void registerCommands(TeleConfirmLite plugin) {
        CommandSpec reloadCmdSpec = CommandSpec.builder()
                .description(Text.of("Reloads TeleConfirmLite."))
                .permission("tcl.reload")
                .executor(new ReloadCommand(plugin))
                .build();
        Sponge.getCommandManager().register(plugin, reloadCmdSpec, "tpcreload", "tpareload");
        
        
        CommandBase tpcCmd = new TpcCommand();
        CommandSpec tpcCmdSpec = CommandSpec.builder()
                .description(Text.of("Request to teleport to the specified player."))
                .permission("tcl.tpc")
                .arguments(
                    GenericArguments.onlyOne(GenericArguments.player(Text.of("playername"))))
                .executor(tpcCmd)
                .build();
        Sponge.getCommandManager().register(plugin, tpcCmdSpec, "tpc", "tpa");
        commands.add(tpcCmd);
        
        CommandBase tpchereCmd = new TpchereCommand();
        CommandSpec tpchereCmdSpec = CommandSpec.builder()
                .description(Text.of("Request that the specified player teleports to you."))
                .permission("tcl.tpchere")
                .arguments(
                    GenericArguments.onlyOne(GenericArguments.player(Text.of("playername"))))
                .executor(tpchereCmd)
                .build();
        Sponge.getCommandManager().register(plugin, tpchereCmdSpec, "tpchere", "tpahere");
        commands.add(tpchereCmd);
        
        CommandBase tpcaCmd = new TpcaCommand();
        CommandSpec tpcaCmdSpec = CommandSpec.builder()
                .description(Text.of("Accepts a teleport request."))
                .permission("tcl.tpca")
                .executor(tpcaCmd)
                .build();
        Sponge.getCommandManager().register(plugin, tpcaCmdSpec, "tpca", "tpaccept", "tpyes");
        commands.add(tpcaCmd);
        
        CommandBase tpcdCmd = new TpcdCommand();
        CommandSpec tpcdCmdSpec = CommandSpec.builder()
                .description(Text.of("Rejects a teleport request."))
                .permission("tcl.tpcd")
                .executor(tpcdCmd)
                .build();
        Sponge.getCommandManager().register(plugin, tpcdCmdSpec, "tpcd", "tpdeny", "tpno");
        commands.add(tpcdCmd);
        
        CommandBase tpctoggleCmd = new TpcToggleCmd();
        CommandSpec tpctoggleCmdSpec = CommandSpec.builder()
                .description(Text.of("Toggle teleportation,"))
                .permission("tcl.tpctoggle")
                .executor(tpctoggleCmd)
                .build();
        Sponge.getCommandManager().register(plugin, tpctoggleCmdSpec, "tpctoggle", "tptoggle");
        commands.add(tpctoggleCmd);
        
        CommandBase tpcclearCmd = new TpcClearCommand();
        CommandSpec tpcclearCmdSpec = CommandSpec.builder()
                .description(Text.of("Removes a pending request."))
                .permission("tcl.tpcclear")
                .executor(tpcclearCmd)
                .build();
        Sponge.getCommandManager().register(plugin, tpcclearCmdSpec, "tpcclear", "tpaclear", "tpclear");
        commands.add(tpcclearCmd);
//        
//        CommandSpec tpcbackCmd = CommandSpec.builder()
//                .description(Text.of("Returns you to your previous location."))
//                .permission("tcl.tpcback")
//                .executor(null)
//                .build();
//        Sponge.getCommandManager().register(plugin, tpcbackCmd, "tpcback", "tpaback");
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
