package com.lagopusempire.teleconfirmlite.messages;

public enum Messages {
    PLAYER_ONLY("player_only"),
    SENDER_REQUEST_TO("request.to.sender"),
    TARGET_REQUEST_TO("request.to.target"),
    SENDER_REQUEST_HERE("request.here.sender"),
    TARGET_REQUEST_HERE("request.here.target"),
    NO_PENDING_REQUESTS("accept.no_pending_request"),
    TARGET_OFFLINE("accept.target_offline"),
    SENDER_REQUEST_DENIED("request.denied.sender"),
    TARGET_REQUEST_DENIED("request.denied.target"),
    REQUEST_CLEARED("request_cleared"),
    TARGET_ALREADY_HAS_REQUEST("request.already_has.target"),
    SENDER_ALREADY_HAS_REQUEST("request.already_has.sender"),
    SENDER_NOW_ACCEPTING_REQUESTS("sender_accepting_requests.active"),
    SENDER_NO_LONGER_ACCEPTING_REQUESTS("sender_accepting_requests.inactive"),
    TARGET_NOT_ACCEPTING_REQUESTS("request.target_not_accepting_requests"),
    NO_PRIOR_LOC("no_prior_loc"),
    SENDER_CROSS_WORLD_FAIL("accept.no_cross_world_tp.sender"),
    TARGET_CROSS_WORLD_FAIL("accept.no_cross_world_tp.target");
    
    private String key;
    
    private Messages(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }
}
