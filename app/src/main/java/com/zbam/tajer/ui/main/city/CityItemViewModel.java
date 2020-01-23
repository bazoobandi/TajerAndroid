package com.zbam.tajer.ui.main.city;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.response.CityResponse;

/**
 * Created by z.bazoubandi on 9/4/2018.
 */

public class CityItemViewModel {

    public ObservableField<String > cityTitle = new ObservableField<>("");
    public ObservableField<Boolean > showIconSub = new ObservableField<>(true);
    public ObservableField<Boolean > showLogo = new ObservableField<>(true);


    public CityItemViewModel(Context mContext, CityResponse item) {
        try{
            cityTitle.set(item.getCityName());

            if(!item.getHaveLocality().equals("y"))
            {
                showIconSub.set(false);
            }else
            {
                showIconSub.set(true);
            }



        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
