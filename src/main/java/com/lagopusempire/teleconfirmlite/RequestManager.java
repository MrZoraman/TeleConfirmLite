package com.lagopusempire.teleconfirmlite;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;

/**
 *
 * @author MrZoraman
 */
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
    
    public void request(Player player, Player target, RequestType type) {
        //these details are constructed from the target's prespective
        RequestDetails details = new RequestDetails(player.getUniqueId(), player.getName(), type.reverse());
        TclPlayerData data = getPlayerData(target.getUniqueId());
        data.setRequestDetails(details);
    }
    
//    public boolean hasPendingRequest(UUID playerId) {
//        if(!requests.containsKey(playerId)) {
//            return false;
//        }
//        TclPlayerData data = getPlayerData(playerId);
//        return data.getRequestDetails() == null;
//    }
    
    public AcceptResultPack accept(Player player) {
        UUID playerId = player.getUniqueId();
        TclPlayerData data = getPlayerData(playerId);
        RequestDetails details = data.getRequestDetails();
        if(details == null) {
            return new AcceptResultPack(AcceptResult.NO_PENDING_REQUEST, "(no target)");
        }
        UUID target = details.getTarget();
        Optional<Player> targetPlayer = Sponge.getServer().getPlayer(target);
        if(targetPlayer.isPresent()) {
            data.setPriorLoc(player.getLocation());
            data.setRequestDetails(null);
            return new AcceptResultPack(targetPlayer.get().getLocation(), details.getTargetName());
        }
        return new AcceptResultPack(AcceptResult.TARGET_OFFLINE, details.getTargetName());
    }
    
    public boolean deny(UUID playerId) {
        return requests.remove(playerId) != null;
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
