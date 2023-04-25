package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.gasaver.R;
import com.gasaver.Response.BaseResponse;
import com.gasaver.databinding.ActivitySettingsBinding;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());


        init();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });


    }

    private void init(){


        setContentView(binding.getRoot());
        binding.switchPushNoti.setChecked(SharedPrefs.getInstance(this).getBoolean(Constants.allow_push));
        binding.switchSmsNot.setChecked(SharedPrefs.getInstance(this).getBoolean(Constants.allow_sms));
        binding.switchEmailNot.setChecked(SharedPrefs.getInstance(this).getBoolean(Constants.allow_email));
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("user_id", SharedPrefs.getInstance(SettingsActivity.this).getString(Constants.USER_ID));
                jsonObject.addProperty("token", SharedPrefs.getInstance(SettingsActivity.this).getString(Constants.TOKEN));
                jsonObject.addProperty("allow_email", binding.switchEmailNot.isChecked() ? "Yes" : "No");
                jsonObject.addProperty("allow_sms", binding.switchSmsNot.isChecked() ? "Yes" : "No");
                jsonObject.addProperty("allow_push", binding.switchPushNoti.isChecked() ? "Yes" : "No");
                saveUserSettings(jsonObject);
            }
        });
    }

    private void saveUserSettings(JsonObject jsonObject) {
        CommonUtils.showLoading(SettingsActivity.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call<BaseResponse> call = apiInterface.settUserSettings(jsonObject);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                CommonUtils.hideLoading();
                if (response.body().isStatus_code()) {

                    SharedPrefs.getInstance(SettingsActivity.this).saveBoolean(Constants.allow_email, binding.switchEmailNot.isChecked());
                    SharedPrefs.getInstance(SettingsActivity.this).saveBoolean(Constants.allow_sms, binding.switchSmsNot.isChecked());
                    SharedPrefs.getInstance(SettingsActivity.this).saveBoolean(Constants.allow_push, binding.switchPushNoti.isChecked());

                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

    }
}