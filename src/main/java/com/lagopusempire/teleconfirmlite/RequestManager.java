package com.lagopusempire.teleconfirmlite;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.spongepowered.api.Sponge;
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
    
    public boolean request(Player sender, Player target, RequestType type) {
        TclPlayerData data = getPlayerData(target.getUniqueId());
        if(data.getRequestDetails() != null) {
            return false;
        }
        //these details are constructed from the target's prespective
        RequestDetails details = new RequestDetails(sender.getUniqueId(), sender.getName(), type.reverse());
        data.setRequestDetails(details);
        return true;
    }
    
    public AcceptResultPack accept(Player sender) {
        UUID senderId = sender.getUniqueId();
        TclPlayerData senderData = getPlayerData(senderId);
        RequestDetails details = senderData.getRequestDetails();
        if(details == null) {
            return new AcceptResultPack(AcceptResult.NO_PENDING_REQUEST, "(no target)");
        }
        
        UUID target = details.getTarget();
        Optional<Player> targetPlayer = Sponge.getServer().getPlayer(target);
        if(!targetPlayer.isPresent()) {
            return new AcceptResultPack(AcceptResult.TARGET_OFFLINE, details.getTargetName());
        }
        
        senderData.setRequestDetails(null);
        return new AcceptResultPack(details.getType(), targetPlayer.get());
    }
    
    public void setPriorLocation(Player player) {
        TclPlayerData data = getPlayerData(player.getUniqueId());
        data.setPriorLoc(player.getLocation());
    }
    
    public RequestDetails clearRequest(UUID playerId) {
        TclPlayerData data = getPlayerData(playerId);
        
        RequestDetails details = data.getRequestDetails();
        data.setRequestDetails(null);
        return details;
    }
    
    public Location getPriorLoc(UUID playerId) {
        TclPlayerData data = getPlayerData(playerId);
        Location priorLoc = data.getPriorLoc();
        data.setPriorLoc(null);
        return priorLoc;
    }
    
    public void toggle(UUID playerId) {
        getPlayerData(playerId).toggleAcceptingRequests();
    }
}
