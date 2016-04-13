package com.lagopusempire.teleconfirmlite.messages;

import java.util.Map;
import ninja.leaping.configurate.ConfigurationNode;

public class MessageManager {
    private final Map<Object, ? extends ConfigurationNode> messageNodes;
    
    public MessageManager(ConfigurationNode root) {
        messageNodes = root.getChildrenMap();
    }
    
    public MessageFormatter getMessage(Messages message) {
        String key = message.getKey();
        ConfigurationNode node = messageNodes.get(key);
        if(node == null || node.getString() == null) {
            return new MessageFormatter("Message '" + key + "' not found!");
        }
        
        return new MessageFormatter(node.getString());
    }
}
