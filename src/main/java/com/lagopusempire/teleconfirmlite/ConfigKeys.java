package com.lagopusempire.teleconfirmlite;

public enum ConfigKeys {
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
