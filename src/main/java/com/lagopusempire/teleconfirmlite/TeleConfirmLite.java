package com.lagopusempire.teleconfirmlite;

import com.lagopusempire.teleconfirmlite.commands.CommandRegistrar;
import com.google.inject.Inject;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
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

//    // These are all injected on plugin load for users to work from
    @Inject
    private Logger logger;
//    // Give us a configuration to work from
////    @Inject @DefaultConfig(sharedRoot = true) private ConfigurationLoader<CommentedConfigurationNode> configLoader;
//    @Inject private Game game;

//    @Inject
//    @DefaultConfig(sharedRoot = false)
//    private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path privateConfigDir;

    private final RequestManager requestManager = new RequestManager();
    
    private CommandRegistrar commandRegistrar;
    private MessageManager mm;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        commandRegistrar = new CommandRegistrar(this);
        load();
    }

    public boolean load() {
        try {
            ConfigurationNode rootNode;
            
            Path pluginDir = privateConfigDir.getParent();
            File pluginPath = pluginDir.toFile();
            File messagesConfFile = new File(pluginPath, "messages.conf");
            Path messagesConfPath = Paths.get(messagesConfFile.toURI());
            if(!messagesConfFile.exists()) {
                logger.info("Writing default messages.conf");
                URL jarMessages = this.getClass().getResource("messages.conf");
                ConfigurationLoader<CommentedConfigurationNode> messagesConf = HoconConfigurationLoader
                        .builder()
                        .setPath(messagesConfPath)
                        .setURL(jarMessages)
                        .build();
                messagesConf.load();
                rootNode = messagesConf.load();
                messagesConf.save(rootNode);
            } else {
                ConfigurationLoader<CommentedConfigurationNode> messagesConf = HoconConfigurationLoader.builder()
                        .setPath(messagesConfPath)
                        .build();
                rootNode = messagesConf.load();
            }
            
            mm = new MessageManager(rootNode);
            commandRegistrar.initCommands(requestManager, mm);
            return true;
        } catch (IOException e) {
            logger.error("Failed to load configuration files!", e);
            return false;
        } finally {
            commandRegistrar.setEnabled(false);
            commandRegistrar.initCommands(requestManager, mm);
        }
    }
}
