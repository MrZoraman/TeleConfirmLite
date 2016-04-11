package com.lagopusempire.teleconfirmlite;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
//import org.spongepowered.api.service.config.DefaultConfig;
import org.spongepowered.api.event.Listener;

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

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        
    }

    @Listener
    public void disable(GameStoppingServerEvent event) {
        // Perform shutdown tasks here
    }
}
