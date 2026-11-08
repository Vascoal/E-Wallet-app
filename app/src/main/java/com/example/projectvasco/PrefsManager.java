package com.example.projectvasco;

import android.content.Context;
import android.content.SharedPreferences;

// Satu-satunya kelas yang menyentuh SharedPreferences.
public class PrefsManager {

    private static final String PREF_NAME = "dompetku_prefs";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_DARK = "dark_mode";
    private static final String KEY_HIDE = "hide_balance";

    private final SharedPreferences prefs;

    public PrefsManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getUserName() {
        return prefs.getString(KEY_NAME, "Pengguna");
    }

    public void setUserName(String name) {
        prefs.edit().putString(KEY_NAME, name).apply();
    }

    public boolean isDarkMode() {
        return prefs.getBoolean(KEY_DARK, false);
    }

    public void setDarkMode(boolean dark) {
        prefs.edit().putBoolean(KEY_DARK, dark).apply();
    }

    public boolean isHideBalance() {
        return prefs.getBoolean(KEY_HIDE, false);
    }

    public void setHideBalance(boolean hide) {
        prefs.edit().putBoolean(KEY_HIDE, hide).apply();
    }
}