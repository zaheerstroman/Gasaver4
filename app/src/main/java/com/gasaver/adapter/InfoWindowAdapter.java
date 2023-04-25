package com.gasaver.adapter;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.gasaver.Response.DefaultDataModel;
import com.gasaver.Response.StationDataResponse;
import com.gasaver.databinding.InfoWindowLayoutBinding;
import com.gasaver.utils.CommonUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


    private InfoWindowLayoutBinding binding;
    private Context context;
    private ArrayList<DefaultDataModel> defaultDataModels;


    public InfoWindowAdapter(Context context , ArrayList<DefaultDataModel> defaultDataModels) {

        this.context = context;
        this.defaultDataModels = defaultDataModels;

        binding = InfoWindowLayoutBinding.inflate(LayoutInflater.from(context), null, false);

    }

    @Override
    public View getInfoWindow(Marker marker) {


       StationDataResponse.StationDataModel stationDataModel=new Gson().fromJson( marker.getSnippet(), StationDataResponse.StationDataModel.class);

        String markerTag = (String) marker.getTag();
        Log.d("TAG", "onMarkerClick: " + markerTag);

        Location location=new Location("");
        location.setLatitude(Double.parseDouble(stationDataModel.getLatitude()));
        location.setLongitude(Double.parseDouble(stationDataModel.getLongitude()));

        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marker.hideInfoWindow();
            }
        });



        binding.txtLocationName.setText(stationDataModel.getBrand());


        double distance = SphericalUtil.computeDistanceBetween(new LatLng(location.getLatitude(), location.getLongitude()), marker.getPosition());

        if (distance > 1000) {
            double kilometers = distance / 1000;
            binding.txtLocationDistance.setText(CommonUtils.roundUpDecimal(distance, 2) + " KM");
        }

        else {
            binding.txtLocationDistance.setText(CommonUtils.roundUpDecimal(distance, 2) + " Meters");

        }

        float speed = location.getSpeed();


        if (speed > 0) {
            double time = distance / speed;
            binding.txtLocationTime.setText(CommonUtils.roundUpDecimal(time, 2) + " sec");
        }


        else {
            binding.txtLocationTime.setText("N/A");
        }

        binding.txtBrand.setText(stationDataModel.getBrand());
        try {


            binding.txtPriceLocationDistance.setText("");
            binding.txtPriceLocationDistance2.setText("");
            binding.txtPriceLocationDistance3.setText("");

/// Sort the default data models by price in ascending order
            List<DefaultDataModel> sortedDefaultDataModels = defaultDataModels.stream()
                    .sorted(Comparator.comparingDouble(dm -> Double.parseDouble(dm.getAmount())))
                    .collect(Collectors.toList());

// Set the price values and colors for the UI text views
            binding.txtPriceLocationDistance.setText(sortedDefaultDataModels.get(0).getType() + " $ " + sortedDefaultDataModels.get(0).getAmount());
            binding.txtPriceLocationDistance.setTextColor(Color.parseColor("#44AE53"));

            binding.txtPriceLocationDistance2.setText(sortedDefaultDataModels.get(1).getType() + " $ " + sortedDefaultDataModels.get(1).getAmount());
            binding.txtPriceLocationDistance2.setTextColor(Color.parseColor("#FFA500")); // orange color

            binding.txtPriceLocationDistance3.setText(sortedDefaultDataModels.get(2).getType() + " $ " + sortedDefaultDataModels.get(2).getAmount());
            binding.txtPriceLocationDistance3.setTextColor(Color.RED);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return binding.getRoot();
    }


    @Override
    public View getInfoContents(Marker marker) {

        String markerTag = (String) marker.getTag();
        Log.d("TAG", "onMarkerClick: " + markerTag);

        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cvStation.setVisibility(View.GONE);
            }
        });

        return binding.getRoot();
    }
}
