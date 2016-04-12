package com.lagopusempire.teleconfirmlite.messages;

public enum Messages {
    ;
    
    private String key;
    
    private Messages(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
}
