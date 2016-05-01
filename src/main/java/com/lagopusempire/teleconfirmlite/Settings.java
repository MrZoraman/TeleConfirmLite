package com.lagopusempire.teleconfirmlite;

import com.lagopusempire.phiinae.IYamlConfig;

public class Settings {
    private static boolean clearRequestsOnWorldChange = false;
    private static int requestTimeout = 30;
    private static boolean preventCrossWorldTp = false;
    
    public static void setSettings(IYamlConfig config) {
        clearRequestsOnWorldChange = config.getValue("clearRequestsOnWorldChange");
        requestTimeout = config.getValue("requestTimeout");
        preventCrossWorldTp = config.getValue("preventCrossWorldTp");
    }

    public static boolean isClearRequestsOnWorldChange() {
        return clearRequestsOnWorldChange;
    }

    public static int getRequestTimeout() {
        return requestTimeout;
    }

    public static boolean isPreventCrossWorldTp() {
        return preventCrossWorldTp;
    }
}
