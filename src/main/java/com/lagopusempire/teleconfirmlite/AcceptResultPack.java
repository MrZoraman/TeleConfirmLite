package com.lagopusempire.teleconfirmlite;

import org.spongepowered.api.entity.living.player.Player;

public class AcceptResultPack {
    private final AcceptResult result;
    private final Player target;
    private final String targetName;
    private final RequestType type;
    
    public AcceptResultPack(RequestType type, Player target) {
        this.result = AcceptResult.SUCCESS;
        this.type = type;
        this.target = target;
        this.targetName = target.getName();
    }
    
    public AcceptResultPack(AcceptResult result, String targetName) {
        if(result == AcceptResult.SUCCESS) {
            throw new IllegalArgumentException("Cannot create an AcceptResultPack with result=success and location=null!");
        }
        
        this.result = result;
        this.type = null;
        this.target = null;
        this.targetName = targetName;
    }

    public AcceptResult getResult() {
        return result;
    }

    public RequestType getType() {
        return type;
    }
    
    public String getTargetName() {
        return targetName;
    }
    
    public Player getTarget() {
        return target;
    }
}
