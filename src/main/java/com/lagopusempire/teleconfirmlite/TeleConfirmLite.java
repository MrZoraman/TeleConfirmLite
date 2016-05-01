package com.lagopusempire.teleconfirmlite;

import com.lagopusempire.teleconfirmlite.commands.CommandRegistrar;
import com.google.inject.Inject;
import com.lagopusempire.phiinae.IYamlConfig;
import com.lagopusempire.phiinae.YamlConfig;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
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
        commandRegistrar.registerCommands(this);
    }

    public boolean load() {
        try {
            Path pluginDir = privateConfigDir.getParent();
            File pluginPath = pluginDir.toFile();
            File messagesConfFile = new File(pluginPath + File.separator + "messages.yml");
            if(!messagesConfFile.exists()) {
                logger.info("Writing default messages.yml");
                try (InputStream defaultMessagesStream = this.getClass().getResourceAsStream("messages.yml")) {
                    Utils.ExportResource(defaultMessagesStream, messagesConfFile);
                }
            }
            
            IYamlConfig messagesConfig = new YamlConfig(new FileInputStream(messagesConfFile));
            try (InputStream templateMessages = this.getClass().getResourceAsStream("messages.yml")) {
                messagesConfig.merge(templateMessages);
            }
            messagesConfig.write(new FileOutputStream(messagesConfFile));
            
            mm = new MessageManager(messagesConfig);
            commandRegistrar.setEnabled(true);
            return true;
        } catch (Exception e) {
            logger.error("Failed to load configuration files!", e);
            commandRegistrar.setEnabled(false);
            return false;
        } finally {
            commandRegistrar.initCommands(requestManager, mm);
        }
    }
}
