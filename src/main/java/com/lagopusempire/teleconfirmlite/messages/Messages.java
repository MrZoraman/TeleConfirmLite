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
    REQUEST_CLEARED("request_cleared"),
    TARGET_ALREADY_HAS_REQUEST("target_already_has_request"),
    SENDER_ALREADY_HAS_REQUEST("sender_already_has_request"),
    SENDER_NOW_ACCEPTING_REQUESTS("sender_now_accepting_requests"),
    SENDER_NO_LONGER_ACCEPTING_REQUESTS("sender_no_longer_accepting_requests"),
    TARGET_NOT_ACCEPTING_REQUESTS("target_not_accepting_requests"),
    NO_PRIOR_LOC("no_prior_loc");
    
    private String key;
    
    private Messages(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
}
