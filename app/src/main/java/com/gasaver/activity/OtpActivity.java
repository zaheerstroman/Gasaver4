package com.gasaver.activity;

import static com.gasaver.utils.Constants.MOBILE_EMAIL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gasaver.R;
import com.gasaver.Response.BaseResponseGasaverTProperty;
import com.gasaver.Response.OtpResponseGasaverTProperty;
import com.gasaver.customviews.Pinview;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_submit, tv_otp_resend, tv_resend_otp_timer, tv_resend_code, tv_info;
    ImageView iv_back;

    Pinview tv_pin_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);


        init();
    }

    private void init() {

        tv_info = findViewById(R.id.tv_info);
        tv_info.setText("An 4 digit Code has been sent to \n" + getIntent().getStringExtra("mobile_number"));
        tv_pin_otp = findViewById(R.id.tv_pin_otp);
        tv_pin_otp.requestPinEntryFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tv_pin_otp.isVisibleToUserForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO);
        }


        iv_back = findViewById(R.id.iv_back);
        tv_otp_resend = findViewById(R.id.tv_otp_resend);
        tv_resend_otp_timer = findViewById(R.id.tv_resend_otp_timer);

        tv_submit = findViewById(R.id.tv_submit);

        iv_back.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        tv_otp_resend.setOnClickListener(this);
    }



    public void startTimer(final long finish, long tick) {
        tv_otp_resend.setEnabled(false);
        tv_otp_resend.setClickable(false);
        new CountDownTimer(finish, tick) {

            public void onTick(long millisUntilFinished) {
                long remainedSecs = millisUntilFinished / 1000;
                String stringTime = String.format("%02d:%02d", (remainedSecs / 60), (remainedSecs % 60));
                tv_resend_otp_timer.setText(stringTime);// manage it according to you
            }

            public void onFinish() {
                tv_resend_otp_timer.setText("00:00");
                tv_otp_resend.setEnabled(true);
                tv_otp_resend.setClickable(true);
                cancel();
            }
        }
                .start();
    }

    private void verifyOtp() {
        CommonUtils.showLoading(OtpActivity.this, "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)

                .addFormDataPart("mobile_number", getIntent().getStringExtra("mobile_number"))
                .addFormDataPart("fcm_token", getIntent().getStringExtra("fcm_token"))
                .addFormDataPart("otp", tv_pin_otp.getValue())
                .build();
        Call<OtpResponseGasaverTProperty> call = apiInterface.verifyOtp(requestBody);

        call.enqueue(new Callback<OtpResponseGasaverTProperty>() {
            @Override
            public void onResponse(Call<OtpResponseGasaverTProperty> call, Response<OtpResponseGasaverTProperty> response) {
                CommonUtils.hideLoading();
                CommonUtils.hideKeyboard(OtpActivity.this);
                if (response.body().getStatusCode()) {

                    saveDetailsInSharedPrefs(response.body());
                    Intent intent = new Intent(OtpActivity.this, MainActivityGas.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else if (response.body().getMessage() != null)
                    Toast.makeText(OtpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<OtpResponseGasaverTProperty> call, Throwable t) {
                CommonUtils.hideLoading();
                t.printStackTrace();
            }
        });

    }

    private void saveDetailsInSharedPrefs(OtpResponseGasaverTProperty otpResponse) {
        SharedPrefs sharedPrefs = SharedPrefs.getInstance(OtpActivity.this);
        sharedPrefs.saveString(Constants.STATUS_CODE, String.valueOf(otpResponse.getStatusCode()));

        sharedPrefs.saveString(Constants.DEPARTMENT_ID, String.valueOf((Integer) otpResponse.getUserDetails().get(0).getDepartmentId()));
        sharedPrefs.saveString(Constants.USER_CODE, otpResponse.getUserDetails().get(0).getUserCode());
        sharedPrefs.saveString(Constants.USER_MOBILE, otpResponse.getUserDetails().get(0).getMobile());
        sharedPrefs.saveString(MOBILE_EMAIL, otpResponse.getUserDetails().get(0).getMobile());
        sharedPrefs.saveString(Constants.MOBILE_VERIFIED, otpResponse.getUserDetails().get(0).getMobileVerified());
        sharedPrefs.saveString(Constants.NO_VENDOR, otpResponse.getUserDetails().get(0).getNoVendor());

        //Vineela Vardhireddy
        sharedPrefs.saveString(Constants.USER_ID, String.valueOf(otpResponse.getUserDetails().get(0).getId()));
        sharedPrefs.saveString(Constants.IS_VERIFIED, otpResponse.getUserDetails().get(0).getIsVerified());
        sharedPrefs.saveString(Constants.LOGGEDIN, String.valueOf(otpResponse.getLoggedIn()));

        sharedPrefs.saveString(Constants.TOKEN, (String) otpResponse.getToken());
        sharedPrefs.saveString(Constants.TC_base_path, (String) otpResponse.getBase_path());
        sharedPrefs.saveString(Constants.privacyPolicy, (String) otpResponse.getPrivacyPolicy());
        sharedPrefs.saveString(Constants.termsAndConditions, (String) otpResponse.getTermsAndConditions());


    }

    private void resendOtp() {
        CommonUtils.showLoading(OtpActivity.this, "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("mobile_number", getIntent().getStringExtra("mobile_number"))
                //Send OTP Copy form to Resend OTP
                .addFormDataPart("otp", tv_pin_otp.getValue())


                .build();

        Call<BaseResponseGasaverTProperty> call = apiInterface.resendOtp(requestBody);

        call.enqueue(new Callback<BaseResponseGasaverTProperty>() {
            @Override
            public void onResponse(Call<BaseResponseGasaverTProperty> call, Response<BaseResponseGasaverTProperty> response) {
                CommonUtils.hideLoading();
//                startTimer(60000, 1000);
            }

            @Override
            public void onFailure(Call<BaseResponseGasaverTProperty> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_otp_resend:
                resendOtp();
                break;
            case R.id.tv_submit:
                if (tv_pin_otp.getValue().length() == 4)
                    verifyOtp();
                else
                    Toast.makeText(this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}