package com.gasaver.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static SharedPrefs sharePref = new SharedPrefs();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static SharedPrefs getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharePref;
    }
    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }
    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public Integer getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    } public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean b) {
        return sharedPreferences.getBoolean(key, false);
    }
    public void clearSharedPrefs(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }





}
