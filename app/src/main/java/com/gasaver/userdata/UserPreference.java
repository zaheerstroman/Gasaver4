package com.gasaver.userdata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.gasaver.Response.StationData_0;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPreference {


    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "FUEL";
    private static final String categoryItem = "categoryItem";
    private static final String prices = "prices";


    @SuppressLint("CommitPrefEdits")
    public UserPreference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setcategoryItem(List<StationData_0.Data.Prices> productsInCartList) {

        SharedPreferences.Editor editor;
        editor = pref.edit();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.create();
        String adjn = gson.toJson(productsInCartList);

        editor.putString(categoryItem, adjn);
        editor.apply();
        editor.commit();
    }


    public List<StationData_0.Data.Prices> getcategoryItem() {

        List<StationData_0.Data.Prices> servicesList = null;


        try {
            if (pref.contains(categoryItem)) {
                String jsonFavorites = pref.getString(categoryItem, null);
                Gson gson = new Gson();

                StationData_0.Data.Prices[] favoriteItems = gson.fromJson(jsonFavorites,
                        StationData_0.Data.Prices[].class);

                servicesList = Arrays.asList(favoriteItems);
            } else {
                servicesList = new ArrayList<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return servicesList;
    }


    public void setvegcategoryItem(List<StationData_0.Data> productsInCartList) {

        SharedPreferences.Editor editor;
        editor = pref.edit();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.create();
        String adjn = gson.toJson(productsInCartList);

        editor.putString(categoryItem, adjn);
        editor.apply();
        editor.commit();
    }

    public List<StationData_0.Data> getvegcategoryItem() {

        List<StationData_0.Data> servicesList = null;

        try {
            if (pref.contains(categoryItem)) {
                String jsonFavorites = pref.getString(categoryItem, null);
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

    public void setcardlistItem(List<StationData_0.Data.Prices> productsInCartList) {

        SharedPreferences.Editor editor;
        editor = pref.edit();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.create();
        String adjn = gson.toJson(productsInCartList);

        editor.putString(categoryItem, adjn);
        editor.apply();
        editor.commit();
    }

    public List<StationData_0.Data.Prices> getcardlistItem() {

        List<StationData_0.Data.Prices> servicesList = null;

        try {
            if (pref.contains(categoryItem)) {
                String jsonFavorites = pref.getString(categoryItem, null);
                Gson gson = new Gson();

                StationData_0.Data.Prices[] favoriteItems = gson.fromJson(jsonFavorites,
                        StationData_0.Data.Prices[].class);

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
