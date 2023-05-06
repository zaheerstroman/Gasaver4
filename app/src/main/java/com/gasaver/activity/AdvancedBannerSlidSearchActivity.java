package com.gasaver.activity;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.BannersResponse;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvancedBannerSlidSearchActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3;

    public static ViewPager viewPager;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500;
    private final long PERIOD_MS = 3000;
    public static Context context;

    BannersResponse bannersResponse;
    RecyclerView recyclerview_Company_Logos;
    private int[] imageIds = new int[]{

            R.drawable.gas_sever_icons,
            R.drawable.gas_sever_icons2,
            R.drawable.gas_sever_icons3
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_banner_slid_search);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Promotions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            super.onBackPressed();
            finish();
        });
        recyclerview_Company_Logos = findViewById(R.id.recyclerview_Company_Logos);

        ArrayList<String> imageUrlList = new ArrayList<>();
        imageUrlList.add("https://houseofspiritshyd.in/gasaver/admin/assets/images_backup/company/4725_1680756148_BP.png");
        imageUrlList.add("https://houseofspiritshyd.in/gasaver/admin/assets/images_backup/company/4725_1680756148_BP.png");
        imageUrlList.add("https://houseofspiritshyd.in/gasaver/admin/assets/images_backup/company/8033_1680850274_category-03.jpg");
        ArrayList<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("1");
        ids.add("1");
        context = getApplicationContext();
// Add more image URLs as needed

        recyclerview_Company_Logos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerview_Company_Logos.setAdapter(new recyclerview_Company_Logos_Adaptor(this,imageUrlList , ids));
        viewPager = findViewById(R.id.view_pager);




        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);


        fetchBanners();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }


    private void fetchBanners() {
        CommonUtils.showLoading(getApplicationContext(), "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", "2168");
        jsonObject.addProperty("token", "51da1d874ab9626e5f851d02fa31472259d759ae508c988f38184582c0433fb1");

        Call<BannersResponse> call = apiInterface.fetchBanners(jsonObject);

        call.enqueue(new Callback<BannersResponse>() {

            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {

                bannersResponse = response.body();
                CommonUtils.hideLoading();
                ArrayList<String> logoList = new ArrayList<>();
                ArrayList<String> urls = new ArrayList<>();
                ArrayList<String> ids = new ArrayList<>();
                for (int i = 0; i < bannersResponse.getCompanyDetails().size(); i++) {
                    String logoUrl = bannersResponse.getCompanyBasePath() + bannersResponse.getCompanyDetails().get(i).getLogo();
                    ids.add(bannersResponse.getCompanyDetails().get(i).getId().toString());
                    // Add the logo URL to the list only if it is not null or empty and its size is greater than 0
                    if (!logoUrl.isEmpty()) {
                        logoUrl.length();
                        logoList.add(logoUrl);
                    }
                }
                for (int i = 0; i < bannersResponse.getAddsDetails().size(); i++) {
                    urls.add(bannersResponse.getBasePath()+ bannersResponse.getAddsDetails().get(i).getAttachment());
                }

// Set the adapter with the logo list

                recyclerview_Company_Logos.setLayoutManager(new LinearLayoutManager(AdvancedBannerSlidSearchActivity.this.getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerview_Company_Logos.setAdapter(new recyclerview_Company_Logos_Adaptor(AdvancedBannerSlidSearchActivity.this,logoList,ids));
                ImageAdapter adapter = new ImageAdapter(AdvancedBannerSlidSearchActivity.this, urls);
                viewPager.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });
    }
    private void fetchBannersAds(String id) {
        CommonUtils.showLoading(AdvancedBannerSlidSearchActivity.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", "2168");
        jsonObject.addProperty("token", "51da1d874ab9626e5f851d02fa31472259d759ae508c988f38184582c0433fb1");
        jsonObject.addProperty("company_id",id);
        Call<BannersResponse> call = apiInterface.fetchBanners(jsonObject);

        call.enqueue(new Callback<BannersResponse>() {

            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {

                bannersResponse = response.body();
                CommonUtils.hideLoading();
                ArrayList<String> urls = new ArrayList<>();
                if (response.isSuccessful()) {
                    bannersResponse = response.body();
                    if (response.body().getMessage() == null)
                    {
                        for (int i = 0; i < bannersResponse.getAddsDetails().size(); i++) {
                            urls.add(bannersResponse.getBasePath() + bannersResponse.getAddsDetails().get(i).getAttachment());
                        }
                        // Set the adapter with the logo list



                        ImageAdapter adapter = new ImageAdapter(AdvancedBannerSlidSearchActivity.context, urls);
                        AdvancedBannerSlidSearchActivity.viewPager.setAdapter(adapter);
                    }
                     else {
                        // No promotions data available, handle this case
                        ArrayList<String> urls1 = new ArrayList<>();
                        urls1.add("");
                        ImageAdapter adapter = new ImageAdapter(AdvancedBannerSlidSearchActivity.context, urls);
                        AdvancedBannerSlidSearchActivity.viewPager.setAdapter(adapter);
                        Toast.makeText(AdvancedBannerSlidSearchActivity.context, "No Data Fund", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle unsuccessful response
                }


            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });
    }


    private static class recyclerview_Company_Logos_Adaptor extends RecyclerView.Adapter<recyclerview_Company_Logos_ViewHolder> {
        private List<String> imageUrls;
        private Activity activity;
        private final ArrayList<String> ids;

        public recyclerview_Company_Logos_Adaptor(Activity activity ,List<String> imageUrls , ArrayList<String> ids) {
            this.imageUrls = imageUrls;
            this.activity = activity;
            this.ids = ids;
        }

        @NonNull
        @Override
        public recyclerview_Company_Logos_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_company_logo_profile, parent, false);
            return new recyclerview_Company_Logos_ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull recyclerview_Company_Logos_ViewHolder holder, int position) {
            String imageUrl = imageUrls.get(position);
            // Load the image from the URL and set it in the ImageView using Picasso or Glide
            Glide.with(activity).load(imageUrl).circleCrop().into(holder.companyLogoImageView);
            holder.companyLogoImageView.setOnClickListener(view -> {
                AdvancedBannerSlidSearchActivity advancedBannerSlidSearchActivity = new AdvancedBannerSlidSearchActivity();
                advancedBannerSlidSearchActivity.fetchBannersAds(ids.get(position));
            });
        }

        @Override
        public int getItemCount() {
            return imageUrls.size();
        }
    }


    private static class recyclerview_Company_Logos_ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView companyLogoImageView;


        public recyclerview_Company_Logos_ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogoImageView = itemView.findViewById(R.id.iv_profile_img);
        }
    }

    public void changeColor(){
        ImageButton btn1 = (ImageButton) findViewById(R.id.iv_profile_img);
        btn1.setBackgroundColor(Color.GREEN);
    }

    public void changeColor(View view){
        ImageButton btn1 = (ImageButton) findViewById(R.id.iv_profile_img);
        btn1.setBackgroundColor(Color.GREEN);
    }
    public class ImageAdapter extends PagerAdapter {

        private Context context;
        private ArrayList<String> imageUrls;

        public ImageAdapter(Context context, ArrayList<String> imageUrls) {
            this.context = context;
            this.imageUrls = imageUrls;
        }

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(imageUrls.get(position)).into(imageView);
            int radius = context.getResources().getDimensionPixelSize(R.dimen.view_pager_radius);
            imageView.setClipToOutline(true);
            imageView.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }



}



