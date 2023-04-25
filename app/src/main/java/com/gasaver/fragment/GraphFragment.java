package com.gasaver.fragment;

import static android.view.View.FOCUS_LEFT;
import static android.view.View.FOCUS_RIGHT;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.gasaver.R;
import com.gasaver.adapter.GraphViewPagerAdapter;
import com.gasaver.adapter.ViewPagerAdapter;
import com.gasaver.databinding.FragmentGraphBinding;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.google.gson.JsonObject;
import com.jjoe64.graphview.GraphView;


public class GraphFragment extends Fragment implements View.OnClickListener {

    ViewPager mViewPager;

    GraphViewPagerAdapter mViewPagerAdapter;

    ImageView iv_left_nav_viewpager, iv_right_nav_viewpager, iv_left_nav_proj, iv_right_nav_proj, iv_left_nav_prop, iv_right_nav_prop, iv_left_nav_agents, iv_right_nav_agents, iv_left_nav_dev, iv_right_nav_dev;

    int[] images = {R.drawable.sample2, R.drawable.sample3, R.drawable.sample4, R.drawable.sample5};

    GraphView graphView;

    private FragmentGraphBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        init(view);


        return view;


    }

    private void init(View view) {

        iv_left_nav_viewpager = view.findViewById(R.id.iv_left_nav_viewpager);
        iv_right_nav_viewpager = view.findViewById(R.id.iv_right_nav_viewpager);

        mViewPager =  view.findViewById(R.id.viewpager);

        iv_left_nav_viewpager.setOnClickListener(this);
//        iv_left_nav_viewpager.setOnClickListener((View.OnClickListener) this);
        iv_right_nav_viewpager.setOnClickListener(this);

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

    private void fetchGraphReports() {

        CommonUtils.showLoading(getApplicationContext(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        JsonObject jsonObject = new JsonObject();


    }
}