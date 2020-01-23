package com.zbam.tajer.ui.main.advertising;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;

/**
 * Created by z.bazoubandi on 7/24/2018.
 */

public class AdvertisingItemViewModel {

    public ObservableField<String> advertisingTitle = new ObservableField<>("");
    public ObservableField<String> advertisingPrice = new ObservableField<>("");
    public ObservableField<Boolean> advertisingShowPrice = new ObservableField<>(true);
    public ObservableField<Boolean> advertisingShowPriceType = new ObservableField<>(true);
    public ObservableField<String> advertisingPriceType = new ObservableField<>("");
    public ObservableField<Bitmap> advertisingUlr = new ObservableField<>();
    public ObservableField<String> advertisingPrettyTime = new ObservableField<>();
    public ObservableField<String> advertisingCityName = new ObservableField<>();


    public AdvertisingItemViewModel(Context mContext, AdvertisingResponse item) {
        try{
            advertisingTitle.set(item.getAdvertisingTitle());
            advertisingPrice.set(item.getAdvertisingPrice());
            advertisingPrettyTime.set(item.getAdvertisingPrettyTime());
            advertisingCityName.set(item.getAdvertisingCityName());
            advertisingPriceType.set(item.getAdvertisingPriceType());
            if(Long.parseLong(item.getAdvertisingPrice()) >0)
            {
                advertisingShowPrice.set(true);
                advertisingShowPriceType.set(false);
            }else
            {
                advertisingShowPrice.set(false);
                advertisingShowPriceType.set(true);
            }


            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            new Thread(runnable).start();
            Glide.with(mContext)
                    .load(item.getAdvertisingUlr())
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


//    //public ObservableField<Bitmap> getBookImg() {
//        return advertisingUlr;
//    }
}
