package com.lagopusempire.teleconfirmlite.messages;

public enum Messages {
    PLAYER_ONLY("player_only"),
    SENDER_REQUEST_TO("sender_request_to"),
    TARGET_REQUEST_TO("target_request_to"),
    SENDER_REQUEST_HERE("sender_request_here"),
    TARGET_REQUEST_HERE("target_request_here"),
    NO_PENDING_REQUESTS("no_pending_request"),
    TARGET_OFFLINE("target_offline"),
    SENDER_REQUEST_DENIED("sender_request_denied"),
    TARGET_REQUEST_DENIED("target_request_denied"),
    REQUEST_CLEARED("reques_cleared");
    
    private String key;
    
    private Messages(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
}
