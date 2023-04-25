package com.gasaver.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.gasaver.R;
import com.gasaver.Response.CopunsResponse;
import com.gasaver.Response.GetRewardDataResponse;
import com.gasaver.Response.UploadDataResponse;
import com.gasaver.databinding.ActivityCopunsBinding;
import com.gasaver.databinding.ActivityUploadBinding;
import com.gasaver.databinding.CopunsListItemBinding;
import com.gasaver.databinding.UploadsListItemBinding;
import com.gasaver.network.APIClient;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CopunsActivity extends AppCompatActivity {

    ActivityCopunsBinding binding;

    CopunsResponse copunsResponse = new CopunsResponse();
    String baseUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCopunsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getCopunsData();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("My Coupon");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });
        final String photoUrl = "http://pngriver.com/wp-content/uploads/2017/12/download-Android-Technology-logo-PNG-transparent-images-transparent-backgrounds-PNGRIVER-COM-Android-Mobile-App-Development-Company-In-India-brillmindztech-39975001-800-799.png";


        final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setBackgroundColor(Color.BLACK);
        imagePopup.setFullScreen(false);
        imagePopup.setHideCloseIcon(false);
        imagePopup.setImageOnClickClose(true);

        imagePopup.initiatePopupWithPicasso(photoUrl);


    }

    private void getCopunsData() {

        CommonUtils.showLoading(this, "Please Wait", false);
        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN));

        Call<CopunsResponse> call = apiInterface.getCopunsData(jsonObject);

        call.enqueue(new Callback<CopunsResponse>() {
            @Override
            public void onResponse(Call<CopunsResponse> call, Response<CopunsResponse> response) {
                try {
                    CommonUtils.hideLoading();

                    binding.recyclerviewCopunsList.setAdapter(new CopunsAdapter(CopunsActivity.this, response.body().getCopunsDetails()));

                    Toast.makeText(CopunsActivity.this, "Pleas Click the QR Code", Toast.LENGTH_SHORT).show();


                    binding.recyclerviewCopunsList.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "Item is clicked", Toast.LENGTH_SHORT).show();

                        }

//                        @Override
//                        public void OnClick(View view) {
//                            Toast.makeText(view.getContext(), "Item is clicked", Toast.LENGTH_SHORT).show();
//                        }
                    });

                    Log.e(TAG, "onResponse: " + response.body().getBasePath());
//                    baseUrl = response.body().getBasePath();
                    CopunsResponse.BASE_URL = response.body().getBasePath();


                    copunsResponse.setBasePath(response.body().getBasePath());


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<CopunsResponse> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

    }


    class CopunsAdapter extends RecyclerView.Adapter<CopunsActivity.CopunsAdapter.ViewHolder> {
        ArrayList<CopunsResponse.CopunsDetail> list;
        Context context;

        public CopunsAdapter(Context context, ArrayList<CopunsResponse.CopunsDetail> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CopunsListItemBinding copunsListItemBinding = CopunsListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new ViewHolder(copunsListItemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CopunsResponse.CopunsDetail copunsDetail = list.get(position);

            holder.binding.tvStationCode1.setText(copunsDetail.getName());



            Glide.with(CopunsActivity.this).load(CopunsResponse.BASE_URL + copunsDetail.getAttachment()).error(R.drawable.profile_img).into(holder.i);

            Log.e(TAG, CopunsResponse.BASE_URL + copunsDetail.getAttachment() );


            holder.i.setOnClickListener(v -> {

                Log.e("Width", "" + Resources.getSystem().getDisplayMetrics().widthPixels);
                final ImagePopup imagePopup = new ImagePopup(CopunsActivity.this);
                imagePopup.setBackgroundColor(Color.WHITE);
                imagePopup.setFullScreen(false);
                imagePopup.setHideCloseIcon(true);
                imagePopup.setImageOnClickClose(true);
                imagePopup.setHideCloseIcon(true);
                imagePopup.setMaxWidth(60);
                imagePopup.setMaxHeight(200);
                imagePopup.initiatePopupWithPicasso(CopunsResponse.BASE_URL + copunsDetail.getAttachment());
                imagePopup.viewPopup();
            });



        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            //            UploadsListItemBinding binding;
            CopunsListItemBinding binding;

            ImageView i;

            public ViewHolder(@NonNull CopunsListItemBinding ubinding) {
                super(ubinding.getRoot());
                binding = ubinding;
                i = binding.getRoot().findViewById(R.id.iv_profile_img1);
                Drawable drawable = getResources().getDrawable(R.drawable.united_full_copy);
                i.setImageDrawable(drawable);
            }
        }
    }


}