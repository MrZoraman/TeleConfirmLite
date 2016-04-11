package com.lagopusempire.teleconfirmlite;

import org.spongepowered.api.world.Location;

public class AcceptResultPack {
    private final AcceptResult result;
    private final Location loc;
    
    public AcceptResultPack(Location loc) {
        this.result = AcceptResult.SUCCESS;
        this.loc = loc;
    }
    
    public AcceptResultPack(AcceptResult result) {
        if(result == AcceptResult.SUCCESS) {
            throw new IllegalArgumentException("Cannot create an AcceptResultPack with result=success and location=null!");
        }
        
        this.result = result;
        this.loc = null;
    }

    public AcceptResult getResult() {
        return result;
    }

    public Location getLoc() {
        return loc;
    }
}
