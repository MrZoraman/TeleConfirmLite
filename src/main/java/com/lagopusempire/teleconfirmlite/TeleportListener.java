package com.lagopusempire.teleconfirmlite;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DisplaceEntityEvent;

public class TeleportListener {
    private final RequestManager manager;
    
    TeleportListener(RequestManager manager) {
        this.manager = manager;
    }
    
    @Listener
    public void onPlayerTeleport(DisplaceEntityEvent.Teleport.TargetPlayer event) {
        if(!event.getFromTransform().getExtent().equals(event.getToTransform().getExtent())) {
            //world has changed
            Player p = event.getTargetEntity();
            manager.clearRequestDetails(p.getUniqueId());
        }
    }
}
