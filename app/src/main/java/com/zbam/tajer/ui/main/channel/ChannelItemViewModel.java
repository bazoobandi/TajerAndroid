package com.zbam.tajer.ui.main.channel;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.response.ChannelResponse;
import com.zbam.tajer.data.model.api.response.ChannelResponse;

/**
 * Created by z.bazoubandi on 10/28/2018.
 */

public class ChannelItemViewModel {

    public ObservableField<String> channelTitle = new ObservableField<>("");
    public ObservableField<String> salesType = new ObservableField<>("");
    public ObservableField<Bitmap> channelUlr = new ObservableField<>();
    public ObservableField<String> categoryName = new ObservableField<>();


    public ChannelItemViewModel(Context mContext, ChannelResponse item) {
        try{
            channelTitle.set(item.getChannelName());
            salesType.set(item.getSalesType());
            categoryName.set(item.getCategoryName() + " > " + item.getSubCategoryName());

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            new Thread(runnable).start();
            Glide.with(mContext)
                    .load(item.getChannelLogo())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.bg_placeholder)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            channelUlr.set(resource);
                        }
                    });
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
