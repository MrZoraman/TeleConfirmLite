package com.lagopusempire.teleconfirmlite;

import com.google.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
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

//    // These are all injected on plugin load for users to work from
//    @Inject private Logger logger;
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

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        Path pluginDir = privateConfigDir.getParent();
        File pluginPath = pluginDir.toFile();
        File messagesConfFile = new File(pluginPath, "messages.conf");
        URL jarMessages = this.getClass().getResource("messages.conf");
        
        ConfigurationLoader<CommentedConfigurationNode> messagesConf = HoconConfigurationLoader.builder()
                .setPath(Paths.get(messagesConfFile.toURI()))
                .setURL(jarMessages)
                .build();
        
        try {
            ConfigurationNode rootNode = messagesConf.load();
            Map<Object, ? extends ConfigurationNode> children = rootNode.getChildrenMap();
//            System.out.println("thing: " + children.get("thing").getString());
//            System.out.println("thing2: " + children.get("thing2").getString());
//            System.out.println("string: " + rootNode.getString());
//            System.out.println("rootNode: " + rootNode);
            messagesConf.save(rootNode);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        new CommandRegistrar().registerCommands(this, requestManager);
    }
}
