package com.lagopusempire.teleconfirmlite.messages;

public enum Messages {
    PLAYER_ONLY("player_only"),
    SENDER_REQUEST_TO("request.to.sender"),
    TARGET_REQUEST_TO("request.to.target"),
    SENDER_REQUEST_HERE("request.here.sender"),
    TARGET_REQUEST_HERE("request.here.target"),
    NO_PENDING_REQUESTS("no_pending_request"),
    TARGET_OFFLINE("target_offline"),
    SENDER_REQUEST_DENIED("request.denied.sender"),
    TARGET_REQUEST_DENIED("request.denied.target"),
    REQUEST_CLEARED("request_cleared"),
    TARGET_ALREADY_HAS_REQUEST("request.already_has.target"),
    SENDER_ALREADY_HAS_REQUEST("request.already_has.sender"),
    SENDER_NOW_ACCEPTING_REQUESTS("sender_now_accepting_requests"),
    SENDER_NO_LONGER_ACCEPTING_REQUESTS("sender_accepting_requests.no"),
    TARGET_NOT_ACCEPTING_REQUESTS("sender_accepting_requests.yes"),
    NO_PRIOR_LOC("no_prior_loc");
    
    private String key;
    
    private Messages(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
}
