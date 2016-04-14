package com.lagopusempire.teleconfirmlite;

import org.spongepowered.api.world.Location;

public class TclPlayerData {
    private static final int TIMEOUT = 10;
    
    private Location priorLoc = null;
    private RequestDetails requestDetails = null;
    private boolean acceptingRequests = true;
    private long requestStartTime = -1;
    
    public void toggleAcceptingRequests() {
        acceptingRequests = !acceptingRequests;
    }

    public Location getPriorLoc() {
        return priorLoc;
    }

    public void setPriorLoc(Location priorLoc) {
        this.priorLoc = priorLoc;
    }

    public RequestDetails getRequestDetails() {
        long now = System.currentTimeMillis() / 1000;
        if(requestStartTime + TIMEOUT < now) {
            setRequestDetails(null);
        }
        
        return requestDetails;
    }

    public void setRequestDetails(RequestDetails details) {
        this.requestDetails = details;
        
        if(details != null) {
            requestStartTime = System.currentTimeMillis() / 1000;
        }
    }

    public boolean isAcceptingRequests() {
        return acceptingRequests;
    }

    public void setAcceptingRequests(boolean acceptingRequests) {
        this.acceptingRequests = acceptingRequests;
    }
}
