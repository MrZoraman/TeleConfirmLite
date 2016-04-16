package com.lagopusempire.teleconfirmlite;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;

public class RequestManager {
    
    private final Map<UUID, TclPlayerData> requests = new HashMap<>();
    
    private TclPlayerData getPlayerData(UUID playerId) {
        TclPlayerData data = requests.get(playerId);
        if(data == null) {
            data = new TclPlayerData();
            requests.put(playerId, data);
        }
        return data;
    }
    
    public void request(Player sender, UUID targetId, RequestType type) {
        TclPlayerData data = getPlayerData(targetId);
        //these details are constructed from the target's prespective
        RequestDetails details = new RequestDetails(sender.getUniqueId(), sender.getName(), type.reverse());
        data.setRequestDetails(details);
    }
    
    public RequestDetails getRequestDetails(UUID senderId) {
        return getPlayerData(senderId).getRequestDetails();
    }
    
    public void setPriorLocation(Player player) {
        TclPlayerData data = getPlayerData(player.getUniqueId());
        data.setPriorLoc(player.getLocation());
    }
    
    public void clearRequestDetails(UUID playerId) {
        getPlayerData(playerId).setRequestDetails(null);
    }
    
    public Location getPriorLoc(UUID playerId) {
        TclPlayerData data = getPlayerData(playerId);
        Location priorLoc = data.getPriorLoc();
        data.setPriorLoc(null);
        return priorLoc;
    }
    
    public boolean toggleAcceptingRequests(UUID playerId) {
        return getPlayerData(playerId).toggleAcceptingRequests();
    }
    
    public boolean isAcceptingRequests(UUID playerId) {
        return getPlayerData(playerId).isAcceptingRequests();
    }
}
