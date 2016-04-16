package com.lagopusempire.teleconfirmlite;

public enum Permissions {
    RELOAD("tcl.reload"),
    TPC("tcl.tpc"),
    TPCHERE("tcl.tpchere"),
    TPCA("tcl.tpca"),
    TPCD("tcl.tpcd"),
    TOGGLE("tcl.tpctoggle"),
    CLEAR("tcl.tpcclear"),
    BACK("tcl.tpcback");
    
    private String node;
    
    private Permissions(String node) {
        this.node = node;
    }
    
    public String getNode() {
        return node;
    }
}
