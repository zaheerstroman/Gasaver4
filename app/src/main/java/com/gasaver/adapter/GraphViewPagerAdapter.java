package com.gasaver.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.BannersResponse;
import com.gasaver.Response.GraphReportsResponse;
import com.gasaver.activity.GraphActivityGeeks;
import com.gasaver.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

//
public class GraphViewPagerAdapter extends PagerAdapter {

    Context context;

    GraphReportsResponse banners;

    GraphReportsResponse images;

    LayoutInflater mLayoutInflater;
    boolean fromPropertyDetails = false;


    public GraphViewPagerAdapter(Context context, GraphReportsResponse images) {
        this.context = context;
        this.banners = images;

        this.fromPropertyDetails = fromPropertyDetails;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public GraphViewPagerAdapter(Context context, GraphReportsResponse images, boolean fromPropertyDetails) {
        this.context = context;
//        this.images = images;
        this.images = images;

        this.fromPropertyDetails = fromPropertyDetails;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public GraphViewPagerAdapter(GraphActivityGeeks context, String graphReport) {
        this.context = context;
        this.banners = images;

    }




    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return false;
        return view == ((LinearLayout) object);

    }

    @Override
    public int getCount() {
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.image_item, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);

        // setting the image in the imageView
        if (fromPropertyDetails)


            // Adding the View
            Objects.requireNonNull(container).addView(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
