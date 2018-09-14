package com.iamagamedev.sharpdeveloperstestjava.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.iamagamedev.sharpdeveloperstestjava.repository.models.TransactionObject;

public class SharedPreferencesClass {

    public static void saveStringInPreferences(String saveString, String token) {
        Context context = ThisApplication.getInstance();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(saveString, token);
        editor.apply();
    }

    public static String getStringFromPreferences(String tagString) {
        String result = "";
        Context context = ThisApplication.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences.contains(tagString)) {
            result = preferences.getString(tagString, null);
        }
        if (result != null && !result.equals("")) {
            return result;
        }
        return null;
    }

    public static void deleteFromPrefs(){
        Context context = ThisApplication.getInstance();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
    }

    public static boolean isItInPreferences(String saveStr) {
        Context context = ThisApplication.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences.contains(saveStr)) {
            String result = preferences.getString(saveStr, null);
            if(!result.isEmpty() || !result.equals("")){
                return true;
            }
        }
        return false;
    }
}
