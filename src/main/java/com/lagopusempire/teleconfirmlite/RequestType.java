package com.lagopusempire.teleconfirmlite;

/**
 *
 * @author MrZoraman
 */
public enum RequestType {
    GO_THERE,
    COME_HERE;
    
    public RequestType reverse() {
        switch(this) {
            case GO_THERE:
                return COME_HERE;
            case COME_HERE:
                return GO_THERE;
        }
        
        return null;
    }
}
