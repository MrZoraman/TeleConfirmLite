package com.lagopusempire.teleconfirmlite;

import org.spongepowered.api.world.Location;

public class AcceptResultPack {
    private final AcceptResult result;
    private final Location loc;
    private final String targetName;
    
    public AcceptResultPack(Location loc, String targetName) {
        this.result = AcceptResult.SUCCESS;
        this.loc = loc;
        this.targetName = targetName;
    }
    
    public AcceptResultPack(AcceptResult result, String targetName) {
        if(result == AcceptResult.SUCCESS) {
            throw new IllegalArgumentException("Cannot create an AcceptResultPack with result=success and location=null!");
        }
        
        this.result = result;
        this.loc = null;
        this.targetName = targetName;
    }

    public AcceptResult getResult() {
        return result;
    }

    public Location getLoc() {
        return loc;
    }
    
    public String getTargetName() {
        return targetName;
    }
}
