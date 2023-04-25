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
import com.gasaver.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    ArrayList<BannersResponse.AddsDetail> banners;

    ArrayList<BannersResponse.AddsDetail> images;


    // Layout Inflater
    LayoutInflater mLayoutInflater;
    boolean fromPropertyDetails = false;


    //TPROPERTY :- HomeFragment:----(Banner):-----------------------------------------------------
    public ViewPagerAdapter(Context context, ArrayList<BannersResponse.AddsDetail> images) {

        this.context = context;

        this.banners = images;


        this.fromPropertyDetails = fromPropertyDetails;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    //TPROPERTY :- PropertyDetailsActivity:----(Banner):-------------
    // Viewpager Constructor
    public ViewPagerAdapter(Context context, ArrayList<BannersResponse.AddsDetail> images, boolean fromPropertyDetails) {
        this.context = context;
        this.images = images;

        this.fromPropertyDetails = fromPropertyDetails;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if (fromPropertyDetails)
            return images.size();
        // return the number of images
        return banners.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
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

            Glide.with(context).load(Constants.BANNER_IMAGE_URL + images.get(position)).into(imageView);

        else
            Glide.with(context).load(Constants.BANNER_IMAGE_URL + banners.get(position).getAttachment()).into(imageView);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (fromPropertyDetails)
//                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.PROPERTY_IMAGE_URL + images.get(position))));
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BANNER_IMAGE_URL + images.get(position))));

                }
                catch (Exception e) {
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
