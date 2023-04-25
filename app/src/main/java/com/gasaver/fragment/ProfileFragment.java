package com.gasaver.fragment;

import static android.app.Activity.RESULT_OK;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.BaseResponse;
import com.gasaver.Response.ProfileUserDataResponseGasaverT;
import com.gasaver.activity.CopunsActivity;
import com.gasaver.activity.PrivacyPolicyActivity;
import com.gasaver.activity.RateUsActivity;
import com.gasaver.activity.RewardActivity;
import com.gasaver.activity.SettingsActivity;
import com.gasaver.activity.ShareitActivity;
import com.gasaver.activity.SplashActivity;
import com.gasaver.activity.UploadActivity;
import com.gasaver.activity.WebViewActivity;
import com.gasaver.databinding.ActivityProfileBinding;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
ProfileFragment extends BottomSheetDialogFragment implements View.OnClickListener {


    private ActivityProfileBinding binding;
    private EditProfileFragment editProfileFragment;
    private ProfileUserDataResponseGasaverT responseProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        binding.layoutTerms.setOnClickListener(this);
        binding.layoutPrivacy.setOnClickListener(this);
        binding.layoutFeedbackSavedSearches.setOnClickListener(this);
        binding.layoutMyRewardsProperties.setOnClickListener(this);
        binding.layoutRateUsShortListed.setOnClickListener(this);
        binding.layoutSharitContaced.setOnClickListener(this);
        binding.layoutSettingsMyRequirements.setOnClickListener(this);
        binding.layoutMyUploadResponses.setOnClickListener(this);
        binding.ivEdit.setOnClickListener(this);
        binding.llLogout.setOnClickListener(this);

        binding.layoutMyCopunsProperties.setOnClickListener(this);


        fetchProfileDetails();
        return binding.getRoot();

    }


    private void fetchProfileDetails() {

        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN)).build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN));

        Call<ProfileUserDataResponseGasaverT> call = apiInterface.fetchProfileDetails(jsonObject);


        call.enqueue(new Callback<ProfileUserDataResponseGasaverT>() {
            @Override
            public void onResponse(Call<ProfileUserDataResponseGasaverT> call, Response<ProfileUserDataResponseGasaverT> response) {
                CommonUtils.hideLoading();
                try {
                    responseProfile = response.body();
                    binding.tvProfileName.setText(responseProfile.getData().getName().toString());
                    binding.tvPhone.setText(responseProfile.getData().getMobile());
                    binding.tvEmail.setText(responseProfile.getData().getEmail().toString());
                    binding.tvRole.setText(responseProfile.getData().getUserCode());
//                    binding.txtRewardPoints.setText(responseProfile.getRewardPoints());

                    try {
                        SharedPrefs.getInstance(getActivity()).saveBoolean(Constants.allow_email, responseProfile.getData().getAllow_mail().equalsIgnoreCase("Yes"));
                        SharedPrefs.getInstance(getActivity()).saveBoolean(Constants.allow_sms, responseProfile.getData().getAllow_sms().equalsIgnoreCase("Yes"));
                        SharedPrefs.getInstance(getActivity()).saveBoolean(Constants.allow_push, responseProfile.getData().getAllow_push().equalsIgnoreCase("Yes"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    Glide.with(getActivity()).load(response.body().getBase_path() + response.body().getData().getProfilePhoto())
                            .error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfileImg);


                } catch (Exception e) {
                    e.printStackTrace();
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

            case R.id.layout_terms:

                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("Attachment", SharedPrefs.getInstance(getActivity()).getString(Constants.TC_base_path) + SharedPrefs.getInstance(getActivity()).getString(Constants.termsAndConditions));

                startActivity(intent);
                break;
            case R.id.layout_privacy:
                intent = new Intent(getActivity(), PrivacyPolicyActivity.class);
                intent.putExtra("Attachment", SharedPrefs.getInstance(getActivity()).getString(Constants.TC_base_path) + SharedPrefs.getInstance(getActivity()).getString(Constants.privacyPolicy));

                startActivity(intent);
                break;
            case R.id.layout_myUploadResponses:
                intent = new Intent(getActivity(), UploadActivity.class);
                intent.putExtra("SELECTED_POS", 0);
                startActivity(intent);
                break;

            case R.id.layout_myCopunsProperties:
                intent = new Intent(getActivity(), CopunsActivity.class);
                intent.putExtra("SELECTED_POS", 0);
                startActivity(intent);
                break;


            case R.id.layout_feedback_savedSearches:

                showFeedbackDilog();
                break;

            case R.id.layout_rateUs_shortListed:
                intent = new Intent(getActivity(), RateUsActivity.class);
                intent.putExtra("SELECTED_POS", 4);
                startActivity(intent);
                break;

            case R.id.layout_sharit_contaced:
                intent = new Intent(getActivity(), ShareitActivity.class);
                intent.putExtra("SELECTED_POS", 3);
                startActivity(intent);
                break;

            case R.id.layout_settings_myRequirements:
                intent = new Intent(getActivity(), SettingsActivity.class);
                intent.putExtra("SELECTED_POS", 2);
                startActivity(intent);
                break;

            case R.id.layout_myRewardsProperties:
                intent = new Intent(getActivity(), RewardActivity.class);
//                intent = new Intent(getActivity(), UploadActivity.class);
                intent.putExtra("SELECTED_POS", 1);
                startActivity(intent);
                break;
            case R.id.iv_edit:

                if (responseProfile != null) {
                    intent = new Intent(getActivity(), EditProfileFragment.class);

                    intent.putExtra("reward_points", (responseProfile.getRewardPoints()));
                    intent.putExtra("bar_code", (responseProfile.getBarCode()));
                    intent.putExtra("basepath", (responseProfile.getBase_path()));
                    intent.putExtra("data", new Gson().toJson(responseProfile.getData()));
                    startActivityForResult(intent,102);
                }
                break;

            case R.id.ll_logout:
                SharedPrefs.getInstance(getActivity()).clearSharedPrefs();

                Intent intent1 = new Intent(getActivity(), SplashActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;
        }

    }

    private void showFeedbackDilog() {
        final Dialog dialog = new Dialog(getActivity());
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
                    Toast.makeText(getActivity(), "Please Enter message to submit feedback", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void postFeedback(Dialog dialog, String msg) {

        CommonUtils.showLoading(getActivity(), "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN));

        jsonObject.addProperty("description", msg);

        Call<BaseResponse> call = apiInterface.feedback(jsonObject);

        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.isStatus_code()) {

                    dialog.dismiss();
                    Toast.makeText(getActivity(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong. Please Try again", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==102&&resultCode==RESULT_OK)
            fetchProfileDetails();
    }
}