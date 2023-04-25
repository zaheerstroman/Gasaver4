package com.gasaver.activity;

import static com.gasaver.utils.Constants.REQUEST_LOCATION;
import static com.gasaver.utils.Constants.USER_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.gasaver.R;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;

public class
SplashActivity extends AppCompatActivity {

    private String TAG = SplashActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Bundle bundle = getIntent().getExtras();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        if (bundle != null && bundle.get("data") != null) {


            //here can get notification message
            String datas = bundle.get("data").toString();
            Toast.makeText(this, datas, Toast.LENGTH_SHORT).show();

        }
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            launchApp();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //launch app
                launchApp();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void launchApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefs.getInstance(SplashActivity.this).getString(USER_ID).isEmpty()) {
                    Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
                    startActivity(intent);
                } else {

                    Log.e(TAG, "user_id: " + SharedPrefs.getInstance(SplashActivity.this).getString(USER_ID));
                    Log.e(TAG, "api_key: " + SharedPrefs.getInstance(SplashActivity.this).getString(Constants.API_KEY));
                    Intent intent = new Intent(SplashActivity.this, MainActivityGas.class);

                    startActivity(intent);
                }
                finish();
            }
        }, 1000);
    }

}