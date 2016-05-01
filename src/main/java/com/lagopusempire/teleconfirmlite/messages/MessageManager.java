package com.lagopusempire.teleconfirmlite.messages;

import com.lagopusempire.phiinae.IYamlConfig;

public class MessageManager {
    private final IYamlConfig yamlConfig;
    
    public MessageManager(IYamlConfig yamlConfig) {
        this.yamlConfig = yamlConfig;
    }
    
    public MessageFormatter getMessage(Messages message) {
        String key = message.getKey();
        if(!yamlConfig.containsKey(key)) {
            return new MessageFormatter("Message '" + key + "' not found!");
        }
        
        return new MessageFormatter(yamlConfig.getValue(key));
    }
}
