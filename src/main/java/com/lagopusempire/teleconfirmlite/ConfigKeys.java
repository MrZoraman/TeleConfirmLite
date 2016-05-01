package com.lagopusempire.teleconfirmlite;

public enum ConfigKeys {
    CLEAR_REQUESTS_ON_WORLD_CHANGE("clearRequestsOnWorldChange"),
    REQUEST_TIMEOUT("requestTimeout"),
    PREVENT_CROSS_WORLD_TP("preventCrossWorldTp");
    
        
    private final String key;
    
    private ConfigKeys(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
}
