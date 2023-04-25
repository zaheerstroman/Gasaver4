package com.gasaver.fragment;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchCityNamesTask extends AsyncTask<Void, Void, List<String>> {

    private Context mContext;

    private AutoCompleteTextView mAutoCompleteTextView;

    public FetchCityNamesTask(Context context, AutoCompleteTextView autoCompleteTextView) {
        mContext = context;
        mAutoCompleteTextView = autoCompleteTextView;
    }


    @Override
    protected List<String> doInBackground(Void... params) {
        // Build the URL for the GeoNames API request
        String url = "http://api.geonames.org/searchJSON?formatted=true&country=AU&maxRows=1000&username=paradox122";

        // Set up the HTTP client
        OkHttpClient client = new OkHttpClient();

        // Create the request object
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Send the request and extract the list of city names from the response
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray geonamesArray = jsonObject.getJSONArray("geonames");
            List<String> cityNames = new ArrayList<>();
            for (int i = 0; i < geonamesArray.length(); i++) {
                String cityName = geonamesArray.getJSONObject(i).getString("name");
                cityNames.add(cityName);
            }
            return cityNames;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<String> cityNames) {
        if (cityNames != null) {
            // Set up the autocomplete adapter with the list of city names
            ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_dropdown_item_1line, cityNames);
            mAutoCompleteTextView.setAdapter(adapter);
        } else {
            // Handle the case where the network operation failed
        }
    }
}
