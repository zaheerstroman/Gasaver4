package com.gasaver.activity;

import static android.view.View.FOCUS_LEFT;
import static android.view.View.FOCUS_RIGHT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gasaver.R;
import com.gasaver.Response.BannersResponse;
import com.gasaver.Response.GraphReportsResponse;
//import com.gasaver.adapter.GraphViewPagerAdapter;
import com.gasaver.adapter.GraphViewPagerAdapter;
import com.gasaver.adapter.ViewPagerAdapter;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.google.gson.JsonObject;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraphActivityGeeks extends AppCompatActivity implements View.OnClickListener{


    ViewPager mViewPager;

    GraphViewPagerAdapter mViewPagerAdapter;

    ImageView iv_left_nav_viewpager, iv_right_nav_viewpager, iv_left_nav_proj, iv_right_nav_proj, iv_left_nav_prop, iv_right_nav_prop, iv_left_nav_agents, iv_right_nav_agents, iv_left_nav_dev, iv_right_nav_dev;

    //    int[] images = {R.drawable.sample2};
    int[] images = {R.drawable.sample2, R.drawable.sample3, R.drawable.sample4, R.drawable.sample5};


    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_geeks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("My Graph");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });

        fetchGraphReports();


        iv_left_nav_viewpager = findViewById(R.id.iv_left_nav_viewpager);
        iv_right_nav_viewpager = findViewById(R.id.iv_right_nav_viewpager);
        mViewPager = findViewById(R.id.viewpager);

        iv_left_nav_viewpager.setOnClickListener((View.OnClickListener) this);
        iv_right_nav_viewpager.setOnClickListener((View.OnClickListener) this);


        mViewPager = findViewById(R.id.viewpager);

    }


    private void fetchGraphReports() {

        CommonUtils.showLoading(getApplicationContext(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("user_id", "119");
        jsonObject.addProperty("token", "24631cdd0323cea063e6cb4e5b2b0f6606540a5ae48428ed41e412131efb3c5a");

        Call<GraphReportsResponse> call = apiInterface.fetchGraphReports(jsonObject);

        call.enqueue(new Callback<GraphReportsResponse>() {

            @Override
            public void onResponse(Call<GraphReportsResponse> call, Response<GraphReportsResponse> response) {

                GraphReportsResponse graphReportsResponse = response.body();


                if (graphReportsResponse != null && !graphReportsResponse.getGraphReport().isEmpty()) {

                    if (graphReportsResponse.getMessage() != null && !graphReportsResponse.getMessage().isEmpty()) {


                    }
                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<GraphReportsResponse> call, Throwable t) {

                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_nav_viewpager:
                mViewPager.arrowScroll(FOCUS_LEFT);
                break;
            case R.id.iv_right_nav_viewpager:
                mViewPager.arrowScroll(FOCUS_RIGHT);
                break;
        }

    }
}