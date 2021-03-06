package com.lagopusempire.teleconfirmlite;

import com.lagopusempire.teleconfirmlite.commands.CommandRegistrar;
import com.google.inject.Inject;
import com.lagopusempire.phiinae.IYamlConfig;
import com.lagopusempire.phiinae.YamlConfig;
import com.lagopusempire.teleconfirmlite.commands.TpcaCommand;
import com.lagopusempire.teleconfirmlite.messages.MessageManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.Listener;

@Plugin(id = PomData.ARTIFACT_ID,
        authors = PomData.AUTHORS,
        description = PomData.DESCRIPTION,
        name = PomData.NAME,
        version = PomData.VERSION)
public class TeleConfirmLite {

    @Inject
    private Logger logger;
    
    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path privateConfigFile;

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
            Path pluginDir = privateConfigFile.getParent();
            File pluginPath = pluginDir.toFile();
            
            //messages.yml
            File messagesConfFile = new File(pluginPath + File.separator + "messages.yml");
            if(!messagesConfFile.exists()) {
                logger.info("Writing default messages.yml");
                try (InputStream defaultMessagesStream = this.getClass().getResourceAsStream("messages.yml")) {
                    Utils.ExportResource(defaultMessagesStream, messagesConfFile);
                }
            }
            
            IYamlConfig messagesConfig = new YamlConfig(new FileInputStream(messagesConfFile));
            try (InputStream templateMessages = this.getClass().getResourceAsStream("messages.yml")) {
                if(messagesConfig.merge(templateMessages)) {
                    messagesConfig.write(new FileOutputStream(messagesConfFile));
                }
            }
            
            mm = new MessageManager(messagesConfig);
            
            //config.yml
            File configFile = new File(pluginPath + File.separator + "config.yml");
            if(!configFile.exists()) {
                logger.info("Writing default config.yml");
                try (InputStream defaultConfigStream = this.getClass().getResourceAsStream("config.yml")) {
                    Utils.ExportResource(defaultConfigStream, configFile);
                }
            }
            
            IYamlConfig config = new YamlConfig(new FileInputStream(configFile));
            try (InputStream templateConfig = this.getClass().getResourceAsStream("config.yml")) {
                if(config.merge(templateConfig)) {
                    config.write(new FileOutputStream(configFile));
                }
            }
            
            TclPlayerData.setTimeout(config.getValue(ConfigKeys.REQUEST_TIMEOUT.getKey()));
            TpcaCommand.setPreventCrossWorldTp(config.getValue(ConfigKeys.PREVENT_CROSS_WORLD_TP.getKey()));
            
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
