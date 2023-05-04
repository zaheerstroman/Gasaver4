package com.gasaver.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gasaver.R;
import com.gasaver.Response.UserRegResponseGasaverTProperty;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignInActivity extends AppCompatActivity implements TextWatcher {

    EditText et_email, et_mobile, et_name;
    ImageView iv_back, btn_continue;

    TextView tv_terms_cond;
    LinearLayout ll_terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gstp);


        init();

    }

    private void init() {

        ll_terms = findViewById(R.id.ll_terms);
        tv_terms_cond = findViewById(R.id.tv_terms_cond);

        et_mobile = findViewById(R.id.et_mobile);

        btn_continue = findViewById(R.id.btn_continue);

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_terms_cond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Constants.TERMS_COND_URL));
                startActivity(i);
            }
        });

//        String text = "Name ";
        String text = "Mobile ";
        String redpart = "*";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text);
        SpannableString redColoredString = new SpannableString(redpart);
        redColoredString.setSpan(new ForegroundColorSpan(Color.RED), 0, redpart.length(), 0);
        builder.append(redColoredString);
//        et_name.setHint(builder);

//        text = "Mobile ";
        text = "Mobile Number or EmailId";
        builder = new SpannableStringBuilder();
        builder.append(text);
        builder.append(redColoredString);
        et_mobile.setHint(builder);

//        text = "EmailId ";
        builder = new SpannableStringBuilder();
        builder.append(text);
//        builder.append(redColoredString);

//        et_name.addTextChangedListener(this);
//        et_email.addTextChangedListener(this);
        et_mobile.addTextChangedListener(this);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFieldsValidated()) {
                    getFCMToken();
                }
            }
        });
    }

    private boolean isAllFieldsValidated() {


        if (TextUtils.isEmpty(et_mobile.getText().toString().trim()) || et_mobile.getText().toString().length() != 10 || !Patterns.PHONE.matcher(et_mobile.getText().toString()).matches()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(et_mobile.getText().toString()).matches()) {
//                et_email.setError("Not Valid");
                et_mobile.setError("Not Valid");
                return false;
            }
        }





        return true;
    }

    private void userLogin(String token) {
        CommonUtils.showLoading(UserSignInActivity.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)

                .addFormDataPart("mobile_number", et_mobile.getText().toString())
                .addFormDataPart("fcm_token", token)
                .build();

        Call<UserRegResponseGasaverTProperty> call = apiInterface.userLogin(requestBody);

        call.enqueue(new Callback<UserRegResponseGasaverTProperty>() {

            @Override
            public void onResponse(Call<UserRegResponseGasaverTProperty> call, Response<UserRegResponseGasaverTProperty> response) {

                if (response.body() != null && response.body().getStatusCode()) {


                    CommonUtils.hideKeyboard(UserSignInActivity.this);
                    if (response.body().getMessage() != null && !response.body().getMessage().isEmpty()) {
                        Intent intent = new Intent(UserSignInActivity.this, OtpActivity.class);
                        intent.putExtra("mobile_number",et_mobile.getText().toString());
                        intent.putExtra("fcm_token",token);
                        startActivity(intent);
                    } else {
                        Toast.makeText(UserSignInActivity.this, "No User Details Found", Toast.LENGTH_SHORT).show();
                    }

                }
                CommonUtils.hideLoading();
                if (response.body() != null && response.body().getMessage() != null)
                    Toast.makeText(UserSignInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserRegResponseGasaverTProperty> call, Throwable t) {
                CommonUtils.hideLoading();
                t.printStackTrace();
                Toast.makeText(UserSignInActivity.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getFCMToken() {
        FirebaseApp.initializeApp(UserSignInActivity.this);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.d("firebase", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.d("firebase", "token" + token);
                        userLogin(token);
                    }
                });
    }


    private void enableButtonIfReady() {
        if (!TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            btn_continue.setVisibility(View.VISIBLE);
            ll_terms.setVisibility(View.VISIBLE);
        } else {
            btn_continue.setVisibility(View.GONE);
            ll_terms.setVisibility(View.GONE);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        enableButtonIfReady();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}