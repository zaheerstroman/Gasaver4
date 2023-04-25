package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gasaver.R;
import com.gasaver.Response.ProfileUserDataResponseGasaverT;
import com.gasaver.fragment.EditProfileFragment;
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

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView userProfile_G, user_Name_or_MobileNumber, user_Mobile_or_Name, tv_profile_name, tv_email, tv_phone, tv_current_plan, tv_role;
    ImageView iv_edit;
    LinearLayout ll_logout;


    private ProfileUserDataResponseGasaverT profileUserDataResponseGasaverT;

    private EditProfileFragment editProfileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile);
        setContentView(R.layout.activity_main_profile_demo);

        init();
        fetchProfileDetails();

        userProfile_G = findViewById(R.id.userProfile_G);

    }

    private void init() {

        tv_role = findViewById(R.id.tv_role);
        tv_profile_name = findViewById(R.id.tv_profile_name);

        user_Name_or_MobileNumber = findViewById(R.id.user_Name_or_MobileNumber);
        user_Mobile_or_Name = findViewById(R.id.user_Mobile_or_Name);

        tv_current_plan = findViewById(R.id.tv_current_plan);

        iv_edit = findViewById(R.id.iv_edit);
        userProfile_G = findViewById(R.id.userProfile_G);


        ll_logout = findViewById(R.id.ll_logout);


        iv_edit.setOnClickListener(this);
        userProfile_G.setOnClickListener(this);
        user_Mobile_or_Name.setOnClickListener(this);


    }

    private void fetchProfileDetails() {

//        Fragment
//        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        //Activity
        CommonUtils.showLoading(UserProfileActivity.this, "Please Wait", false);


        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)

                .addFormDataPart("user_id", SharedPrefs.getInstance(UserProfileActivity.this).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(UserProfileActivity.this).getString(Constants.TOKEN))
                .build();

        Call<ProfileUserDataResponseGasaverT> call = apiInterface.fetchProfileDetails(requestBody);

        call.enqueue(new Callback<ProfileUserDataResponseGasaverT>() {
            @Override
            public void onResponse(Call<ProfileUserDataResponseGasaverT> call, Response<ProfileUserDataResponseGasaverT> response) {
                CommonUtils.hideLoading();

                profileUserDataResponseGasaverT = (ProfileUserDataResponseGasaverT) response.body();

                if (profileUserDataResponseGasaverT != null && profileUserDataResponseGasaverT.getData() != null) {

                    user_Name_or_MobileNumber.setText((CharSequence) profileUserDataResponseGasaverT.getData().getEmail());
                    user_Mobile_or_Name.setText((CharSequence) profileUserDataResponseGasaverT.getData().getMobile());

                    user_Mobile_or_Name.setText(profileUserDataResponseGasaverT.getData().getMobile());

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


            case R.id.userProfile_G:
                break;

            case R.id.ll_logout:
                SharedPrefs.getInstance(UserProfileActivity.this).clearSharedPrefs();


                Intent intent1 = new Intent(UserProfileActivity.this, MainActivityGas.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;
        }

    }
}