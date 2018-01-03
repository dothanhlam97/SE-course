package com.test.lam;

import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import java.io.File;

public class Configs {
    public static final String SUCCESS = "OK";
    public static final String FAIL = "FAIL";

    private Configs() {
    }

    public Preferences prefs;
    
    private static class LazyConfigsHolder {
        private static final Configs INSTANCE = new Configs();
    }

    public static Configs getInstance() {
        return LazyConfigsHolder.INSTANCE;
    }

    public void initConfigPath(String configIniPath) {
        try {
            prefs = new IniPreferences(new Ini(new File(configIniPath)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}