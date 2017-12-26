package com.test.lam;

import java.util.prefs.Preferences;

public class Configs {
    public static final String LAM = "heo";
    public static final String serverName = "ahiho";
    private Configs() {
    }
    
    public Preferences prefs;

    private static class LazyConfigsHolder {
        private static final Configs INSTANCE = new Configs();
    }

    public static Configs getInstance() {
        return LazyConfigsHolder.INSTANCE;
    }
}