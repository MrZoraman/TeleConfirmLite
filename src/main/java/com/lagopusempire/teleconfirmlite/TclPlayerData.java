package com.lagopusempire.teleconfirmlite;

import org.spongepowered.api.world.Location;

public class TclPlayerData {
    private Location priorLoc = null;
    private RequestDetails requestDetails = null;
    private boolean acceptingRequests = true;
    
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
        return requestDetails;
    }

    public void setRequestDetails(RequestDetails details) {
        this.requestDetails = details;
    }

    public boolean isAcceptingRequests() {
        return acceptingRequests;
    }

    public void setAcceptingRequests(boolean acceptingRequests) {
        this.acceptingRequests = acceptingRequests;
    }
}
