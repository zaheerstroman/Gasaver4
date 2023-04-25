package com.gasaver.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gasaver.R;
import com.gasaver.Response.BaseResponseGasaverTProperty;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_dialog);


    }

    private void showFeedbackDilog() {
        final Dialog dialog = new Dialog(FeedBackActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.feedback_dialog);
        dialog.setCancelable(false);

        Button btn_submit = dialog.findViewById(R.id.btn_submit);
        ImageView iv_close = dialog.findViewById(R.id.iv_close);
        EditText et_feedback = dialog.findViewById(R.id.et_feedback);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_feedback.getText().toString().trim()))
                    postFeedback(dialog, et_feedback.getText().toString());
                else
                    Toast.makeText(FeedBackActivity.this, "Please Enter message to submit feedback", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void postFeedback(Dialog dialog, String msg) {

        CommonUtils.showLoading(FeedBackActivity.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();


        jsonObject.addProperty("name", SharedPrefs.getInstance(this).getString(Constants.USER_NAME));
        jsonObject.addProperty("email", SharedPrefs.getInstance(this).getString(Constants.USER_EMAIL));

        jsonObject.addProperty("phone", SharedPrefs.getInstance(this).getString(Constants.USER_MOBILE));
        jsonObject.addProperty("fcm_token", SharedPrefs.getInstance(this).getString(Constants.FCM_TOKEN));

        jsonObject.addProperty("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN));

        jsonObject.addProperty("message", msg);

        Call<BaseResponseGasaverTProperty> call = apiInterface.updateProfile(jsonObject);

        call.enqueue(new Callback<BaseResponseGasaverTProperty>() {
            @Override
            public void onResponse(Call<BaseResponseGasaverTProperty> call, Response<BaseResponseGasaverTProperty> response) {
                BaseResponseGasaverTProperty baseResponse = response.body();
                if (baseResponse != null && baseResponse.getStatusCode()) {

                    dialog.dismiss();
                    Toast.makeText(FeedBackActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<BaseResponseGasaverTProperty> call, Throwable t) {
                Toast.makeText(FeedBackActivity.this, "Something went wrong. Please Try again", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });


    }


}