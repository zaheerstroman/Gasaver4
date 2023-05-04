package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gasaver.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivityGas extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_gas);
        setContentView(R.layout.activity_splash_screen1);

        firebaseAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (firebaseAuth.getCurrentUser() != null) {

                    startActivity(new Intent(SplashActivityGas.this, UserSignInActivity.class));


                    finish();
                } else {


                    startActivity(new Intent(SplashActivityGas.this, GettingStarted.class));


                    finish();
                }


            }
        }, 3000);


    }

}