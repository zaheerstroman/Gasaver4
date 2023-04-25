package com.gasaver.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.gasaver.R;
import com.gasaver.Response.UploadDataResponse;
import com.gasaver.databinding.ActivityUploadBinding;
import com.gasaver.databinding.UploadsListItemBinding;
import com.gasaver.network.APIClient;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity {

    ActivityUploadBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getReportsData();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("My Uploads");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void getReportsData() {
        CommonUtils.showLoading(this, "Please Wait", false);
        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN));
        Call<UploadDataResponse> call = apiInterface.getReportsData(jsonObject);
        call.enqueue(new Callback<UploadDataResponse>() {
            @Override
            public void onResponse(Call<UploadDataResponse> call, Response<UploadDataResponse> response) {
                try {
                    CommonUtils.hideLoading();
                    binding.recyclerviewList.setAdapter(new UploadAdapter(UploadActivity.this,response.body().getData()));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UploadDataResponse> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

    }

    class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.ViewHolder> {
        ArrayList<UploadDataResponse.UploadData> list;
        Context context;

        public UploadAdapter(Context context,ArrayList<UploadDataResponse.UploadData> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            UploadsListItemBinding uploadsListItemBinding=UploadsListItemBinding.inflate( LayoutInflater.from(parent.getContext()));
            return new ViewHolder(uploadsListItemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            UploadDataResponse.UploadData uploadData=list.get(position);
           holder.binding.tvStationCode.setText(uploadData.getStation_name()+" ("+uploadData.getStationId()+")");
           holder.binding.tvLoc.setText(uploadData.getBrand());
           holder.binding.tvAmnt.setText(uploadData.getAmount());
           holder.binding.tvType.setText(uploadData.getSubcategory_name());
           holder.binding.tvTime.setText(uploadData.getLastupdated());
           holder.binding.tvStatus.setText(uploadData.getStatus());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
          UploadsListItemBinding binding;

            public ViewHolder(@NonNull UploadsListItemBinding ubinding) {
                super(ubinding.getRoot());
                binding=ubinding;
            }
        }
    }

}