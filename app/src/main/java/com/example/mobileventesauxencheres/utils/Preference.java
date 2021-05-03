package com.example.mobileventesauxencheres.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mobileventesauxencheres.activities.DetailActivity;
import com.example.mobileventesauxencheres.models.ApiRecords;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Preference {
   
    private static final String PREF_TOKEN = "TOKEN";
    private static final String PREFERENCE_FAVORIS = "favoris";

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

    public static void addToMyProducts(Context context, ApiRecords item) {

        List<ApiRecords> apiRecordsList = getMyProducts(context);
        for (int i = 0; i < apiRecordsList.size(); i++) {
            // Supprime le doublon
//            if (apiRecordsList.get(i).getFields().getLibelle1().toString().equals(item.getFields().getLibelle1().toString())){
//                apiRecordsList.remove(apiRecordsList.get(i));
//            }
        }
        //ajoute dans les favoris
        apiRecordsList.add(item);
        String json = new Gson().toJson(apiRecordsList);
        getPreference(context).edit().putString(PREFERENCE_FAVORIS, json).apply();
    }

    public  static  List<ApiRecords>  getMyProducts (Context context) {
        List<ApiRecords> apiRecordsList = new ArrayList<>();

        String json = getPreference(context).getString(PREFERENCE_FAVORIS, null);

        if(json != null) {
            apiRecordsList = new Gson().fromJson(json, new TypeToken<ArrayList<ApiRecords>>() {}.getType());
        }
        return apiRecordsList;
    }

    public static void resetMyProducts(Context context){

        List<ApiRecords> apiRecordsList = getMyProducts(context);
        for (int i = 0; i < apiRecordsList.size(); i++) {
            apiRecordsList.remove(apiRecordsList.get(i));
        }
        String json = new Gson().toJson(apiRecordsList);
        getPreference(context).edit().putString(PREFERENCE_FAVORIS, json).apply();
    }
}