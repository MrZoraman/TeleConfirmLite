package com.lagopusempire.teleconfirmlite.messages;

import java.util.Map;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.serializer.TextSerializers;

public class MessageManager {
    private final Map<Object, ? extends ConfigurationNode> messageNodes;
    
    public MessageManager(ConfigurationNode root) {
        messageNodes = root.getChildrenMap();
    }
    
    public TextTemplate getMessage(Messages message) {
        String key = message.getKey();
        ConfigurationNode node = messageNodes.get(key);
        if(node == null || node.getString() == null) {
            return TextTemplate.of("message '" + key + "' not found!");
        }
        
        return TextTemplate.of(TextSerializers.FORMATTING_CODE.deserialize(node.getString()));
    }
}
