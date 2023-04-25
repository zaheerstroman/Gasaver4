package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
//import com.e.gasserviceapp.ApiInterface;
import com.gasaver.R;
import com.gasaver.Response.ProfileUserDataResponseGasaverT;
import com.gasaver.fragment.EditProfileFragment;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.android.material.imageview.ShapeableImageView;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileMainActivity extends AppCompatActivity implements View.OnClickListener {
//
    ShapeableImageView iv_profile_img;
    ImageView iv_edit;
    LinearLayout ll_logout, layout_myResponses, layout_savedSearches, layout_shortListed, layout_contaced, layout_myRequirements, layout_myProperties;
    TextView tv_profile_name, tv_email, tv_phone, tv_current_plan, tv_role;

    private ProfileUserDataResponseGasaverT profileUserDataResponseGasaverT;

    private EditProfileFragment editProfileFragment;
    Button btn_upgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        init();
        fetchProfileDetails();
    }

    private void init() {
        tv_role = findViewById(R.id.tv_role);
        iv_profile_img = findViewById(R.id.iv_profile_img);
        tv_profile_name = findViewById(R.id.tv_profile_name);

        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);

        tv_current_plan = findViewById(R.id.tv_current_plan);

        iv_edit = findViewById(R.id.iv_edit);

        ll_logout = findViewById(R.id.ll_logout);


        iv_edit.setOnClickListener(this);
    }

    private void fetchProfileDetails() {

//        Fragment
//        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        //Activity
        CommonUtils.showLoading(ProfileMainActivity.this, "Please Wait", false);


        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)


                .addFormDataPart("user_id", SharedPrefs.getInstance(ProfileMainActivity.this).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(ProfileMainActivity.this).getString(Constants.TOKEN))
                .build();


        Call<ProfileUserDataResponseGasaverT> call = apiInterface.fetchProfileDetails(requestBody);

        call.enqueue(new Callback<ProfileUserDataResponseGasaverT>() {
            @Override
            public void onResponse(Call<ProfileUserDataResponseGasaverT> call, Response<ProfileUserDataResponseGasaverT> response) {
                CommonUtils.hideLoading();

                profileUserDataResponseGasaverT = (ProfileUserDataResponseGasaverT) response.body();

                if (profileUserDataResponseGasaverT != null && profileUserDataResponseGasaverT.getData() != null) {

                    tv_email.setText((CharSequence) profileUserDataResponseGasaverT.getData().getEmail());
                    tv_profile_name.setText("Welcome " + profileUserDataResponseGasaverT.getData().getName() + " " + profileUserDataResponseGasaverT.getData().getLastName() + "!");

                    tv_phone.setText(profileUserDataResponseGasaverT.getData().getMobile());

                    tv_current_plan.setText(profileUserDataResponseGasaverT.getData().getUserCode());
                    tv_role.setText("Code: " + profileUserDataResponseGasaverT.getData().getCode());
                    tv_current_plan.setText(profileUserDataResponseGasaverT.getData().getUserCode());

                    Glide.with(ProfileMainActivity.this).load(Constants.PROFILE_IMG_URL + profileUserDataResponseGasaverT.getData().getProofAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);

                }


            }

            @Override
            public void onFailure(Call<ProfileUserDataResponseGasaverT> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.ll_logout:
                SharedPrefs.getInstance(ProfileMainActivity.this).clearSharedPrefs();

                Intent intent1 = new Intent(ProfileMainActivity.this, MainActivityGas.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;

        }

    }


}