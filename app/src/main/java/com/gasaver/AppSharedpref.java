package com.gasaver;

import android.content.Context;
import android.content.SharedPreferences;

import com.gasaver.Response.StationData_0;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppSharedpref {


    private SharedPreferences preferences, servicePreference, loginpreference, paymenturlresponse;
    private SharedPreferences sessionPreferences;
    private Context context;
    private String SCANURL = "scanurl";
    private String PASSWORD = "password";
    private String SEARCHTECT = "searchvalue";
    private String MOBILENUMBER = "mobilenumber";
    private String CATEGORIES = "categories";

    public AppSharedpref(Context context) {
        String APP_PREF_NAME = "My_Pref";
        String SELECTED_SERVICES = "SELECTEDSERVICE";
        String LOGINRESPONSE = "loginresponse";
        String PAYMENTURLRESPONSE = "paymenturlresponse";
        String sessionPref = "sessionPreference";
        int PRIVATE_MODE = 0;
        this.context = context;
        preferences = context.getSharedPreferences(APP_PREF_NAME, PRIVATE_MODE);
        servicePreference = context.getSharedPreferences(SELECTED_SERVICES, PRIVATE_MODE);
        loginpreference = context.getSharedPreferences(LOGINRESPONSE, PRIVATE_MODE);
        paymenturlresponse = context.getSharedPreferences(PAYMENTURLRESPONSE, PRIVATE_MODE);
        sessionPreferences = context.getSharedPreferences(sessionPref, PRIVATE_MODE);
    }

    //getDefaultData


    //getUserData


    //SigninActivity
    public void savepassworddata(String name) {
        try {
            preferences.edit().putString(PASSWORD, name).apply();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //SignupActivity
    public String getuserid() {
        return preferences.getString(SEARCHTECT, null);
    }


    public void saveSerarchnumberurl(String name) {
        try {
            preferences.edit().putString(MOBILENUMBER, name).apply();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //------------------------------------- Employeelist --------------------------------

    public void setemployeelistdetailsdata(List<StationData_0.Data> restaurentList) {

        SharedPreferences.Editor editor;
        editor = preferences.edit();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.create();
        String adjn = gson.toJson(restaurentList);

        editor.putString(CATEGORIES, adjn);
        editor.apply();
        editor.commit();
    }

    public List<StationData_0.Data> getemployeelistdetailsdata() {


        List<StationData_0.Data> servicesList = null;
        try {
            if (preferences.contains(CATEGORIES)) {
                String jsonFavorites = preferences.getString(CATEGORIES, null);
                Gson gson = new Gson();

                StationData_0.Data[] favoriteItems = gson.fromJson(jsonFavorites,
                        StationData_0.Data[].class);


                servicesList = Arrays.asList(favoriteItems);
            } else {
                servicesList = new ArrayList<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return servicesList;
    }


}
