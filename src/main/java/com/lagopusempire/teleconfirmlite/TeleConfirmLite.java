package com.lagopusempire.teleconfirmlite;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
//import org.spongepowered.api.service.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.text.Text;

/**
 * A simple sponge plugin
 */
@Plugin(id = PomData.ARTIFACT_ID,
        authors = PomData.AUTHORS,
        description = PomData.DESCRIPTION,
        name = PomData.NAME,
        version = PomData.VERSION)
public class TeleConfirmLite {

    // These are all injected on plugin load for users to work from
    @Inject private Logger logger;
    // Give us a configuration to work from
//    @Inject @DefaultConfig(sharedRoot = true) private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    @Inject private Game game;
    
    private final RequestManager requestManager = new RequestManager();

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        new CommandRegistrar().registerCommands(this, requestManager);
    }
}
