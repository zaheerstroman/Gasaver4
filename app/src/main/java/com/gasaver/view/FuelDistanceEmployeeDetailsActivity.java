package com.gasaver.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gasaver.AppSharedpref;
import com.gasaver.R;
import com.gasaver.Response.StationData_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FuelDistanceEmployeeDetailsActivity extends AppCompatActivity {

    AppSharedpref appSharedpref;
    Context context = FuelDistanceEmployeeDetailsActivity.this;

    List<StationData_0.Data> list = new ArrayList<>();

    TextView userid, username, useremail, userphonenumber, userAddressi,

    useraddress, useraddresstreet, useraddresssuite, useraddresscity, useraddresszipcode,
            userLatitude, userLongitude, userBrand, userDistance,
            usercompanydetails, usercompanyname, userwebsite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_distance_employee_details);

        appSharedpref = new AppSharedpref(context);
        list = appSharedpref.getemployeelistdetailsdata();


        List<StationData_0.Data> postElectronicsRequests = list;


        userid = findViewById(R.id.userid);
        username = findViewById(R.id.username);
        useremail = findViewById(R.id.useremail);
        userAddressi = findViewById(R.id.userAddressi);

        useraddresstreet = findViewById(R.id.useraddresstreet);
        useraddresssuite = findViewById(R.id.useraddresssuite);
        useraddresscity = findViewById(R.id.useraddresscity);
        useraddresszipcode = findViewById(R.id.useraddresszipcode);
        userphonenumber = findViewById(R.id.userphonenumber);
        usercompanydetails = findViewById(R.id.usercompanydetails);
        usercompanyname = findViewById(R.id.usercompanyname);
        userwebsite = findViewById(R.id.userwebsite);

        userLatitude = findViewById(R.id.userLatitude);
        userLongitude = findViewById(R.id.userLongitude);
        userBrand = findViewById(R.id.userBrand);
        userDistance = findViewById(R.id.userDistance);



        List<StationData_0.Data> listview = new ArrayList<>();


        for (int j = 0; j < postElectronicsRequests.size(); j++) {

            userid.setText(String.valueOf(postElectronicsRequests.get(j).getId()));
            username.setText(String.valueOf(postElectronicsRequests.get(j).getStationid()));
            useremail.setText(String.valueOf(postElectronicsRequests.get(j).getAddress().toLowerCase(Locale.ROOT)));

            userphonenumber.setText(String.valueOf(postElectronicsRequests.get(j).getDepartmentId()));

            userAddressi.setText(String.valueOf(postElectronicsRequests.get(j).getAddress()));

            useraddresstreet.setText(String.valueOf(postElectronicsRequests.get(j).getPrices()));


//            latitude
            userLatitude.setText(String.valueOf(postElectronicsRequests.get(j).getLatitude()));
            userLongitude.setText(String.valueOf(postElectronicsRequests.get(j).getLongitude()));
            userBrand.setText(String.valueOf(postElectronicsRequests.get(j).getBrand()));
            userDistance.setText(String.valueOf(postElectronicsRequests.get(j).getDistance()));


            listview.add(postElectronicsRequests.get(j));


            userphonenumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < listview.size(); j++) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);

                        callIntent.setData(Uri.parse("tel:" + String.valueOf(listview.get(j).getDepartmentId())));

                        startActivity(callIntent);
                        if (ActivityCompat.checkSelfPermission(FuelDistanceEmployeeDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }

                    }


                }
            });
            useremail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < listview.size(); j++) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        intent.putExtra(Intent.EXTRA_EMAIL, listview.get(j).getId());
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

                        startActivity(Intent.createChooser(intent, "Email via..."));
                    }

                }
            });
        }


    }
}