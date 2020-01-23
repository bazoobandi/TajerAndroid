package com.zbam.tajer.ui.dialog.imageView;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;

/**
 * Created by z.bazoubandi on 10/20/2018.
 */

public class AdvertisingImgeViewShowItemViewModel {

    public ObservableField<Bitmap> advertisingUlr = new ObservableField<>();
    public ObservableField<Float> dpHeightImg = new ObservableField<>();


    public AdvertisingImgeViewShowItemViewModel(Context mContext, AdvertisingImageResponse item) {
        try{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            new Thread(runnable).start();

            Glide.with(mContext)
                    .load(item.getImgUrl())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.bg_placeholder)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            advertisingUlr.set(resource);
                        }
                    });
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
