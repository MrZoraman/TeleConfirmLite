package com.lagopusempire.teleconfirmlite.commands;

import com.lagopusempire.teleconfirmlite.RequestManager;
import com.lagopusempire.teleconfirmlite.TeleConfirmLite;
import com.lagopusempire.teleconfirmlite.commands.TpcCommand;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

/**
 *
 * @author MrZoraman
 */
public class CommandRegistrar {
    
    public void registerCommands(TeleConfirmLite plugin, RequestManager manager, MessageManager mm) {
        CommandSpec tpcCmd = CommandSpec.builder()
                .description(Text.of("Request to teleport to the specified player."))
                .permission("tcl.tpc")
                .arguments(
                    GenericArguments.onlyOne(GenericArguments.player(Text.of("playername"))))
                .executor(new TpcCommand(manager, mm))
                .build();
        Sponge.getCommandManager().register(plugin, tpcCmd, "tpc", "tpa");
        
//        CommandSpec tpchereCmd = CommandSpec.builder()
//                .description(Text.of("Request that the specified player teleports to you."))
//                .permission("tcl.tpchere")
//                .arguments(
//                    GenericArguments.onlyOne(GenericArguments.player(Text.of("playername"))))
//                .executor(null)
//                .build();
//        Sponge.getCommandManager().register(plugin, tpchereCmd, "tpchere", "tpahere");
//        
//        CommandSpec tpcaCmd = CommandSpec.builder()
//                .description(Text.of("Accepts a teleport request."))
//                .permission("tcl.tpca")
//                .executor(null)
//                .build();
//        Sponge.getCommandManager().register(plugin, tpcaCmd, "tpca", "tpaccept", "tpyes");
//        
//        CommandSpec tpcdCmd = CommandSpec.builder()
//                .description(Text.of("Rejects a teleport request."))
//                .permission("tcl.tpcd")
//                .executor(null)
//                .build();
//        Sponge.getCommandManager().register(plugin, tpcdCmd, "tpcd", "tpdeny", "tpno");
//        
//        CommandSpec tptoggleCmd = CommandSpec.builder()
//                .description(Text.of("Toggle teleportation,"))
//                .permission("tcl.tpctoggle")
//                .executor(null)
//                .build();
//        Sponge.getCommandManager().register(plugin, tptoggleCmd, "tpctoggle", "tptoggle");
//        
//        CommandSpec tpcclearCmd = CommandSpec.builder()
//                .description(Text.of("Removes a pending request."))
//                .permission("tcl.tpcclear")
//                .executor(null)
//                .build();
//        Sponge.getCommandManager().register(plugin, tpcclearCmd, "tpcclear", "tpaclear", "tpclear");
//        
//        CommandSpec tpcbackCmd = CommandSpec.builder()
//                .description(Text.of("Returns you to your previous location."))
//                .permission("tcl.tpcback")
//                .executor(null)
//                .build();
//        Sponge.getCommandManager().register(plugin, tpcbackCmd, "tpcback", "tpaback");
    }
}
