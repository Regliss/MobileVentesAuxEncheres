package com.example.mobileventesauxencheres.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preference {
   
    private static final String PREF_TOKEN = "TOKEN";

    private static SharedPreferences getPreference(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c);
    }

    // enregistrement de la ville
    public static void setToken(Context c, String city) {
        getPreference(c).edit().putString(PREF_TOKEN, city).apply();
    }

    // récupération de la ville
    public static String getToken(Context c) {
        return getPreference(c).getString(PREF_TOKEN, null);
    }
}