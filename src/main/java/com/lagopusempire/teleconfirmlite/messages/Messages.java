package com.lagopusempire.teleconfirmlite.messages;

public enum Messages {
    PLAYER_ONLY("player_only"),
    SENDER_REQUEST_TO("sender_request_to"),
    TARGET_REQUEST_TO("target_request_to");
    
    private String key;
    
    private Messages(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
}
