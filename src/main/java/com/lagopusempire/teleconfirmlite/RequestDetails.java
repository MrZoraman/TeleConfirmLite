/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lagopusempire.teleconfirmlite;

import java.util.UUID;

/**
 *
 * @author MrZoraman
 */
public class RequestDetails {
    private final UUID target;
    private final RequestType type;
    
    public RequestDetails(UUID target, RequestType type) {
        this.target = target;
        this.type = type;
    }
    
    public UUID getTarget() {
        return target;
    }
    
    public RequestType getType() {
        return type;
    }
}
