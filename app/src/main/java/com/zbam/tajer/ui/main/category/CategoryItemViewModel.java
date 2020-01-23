package com.zbam.tajer.ui.main.category;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.response.CategoryResponse;

/**
 * Created by z.bazoubandi on 8/25/2018.
 */

public class CategoryItemViewModel {

    public ObservableField<String > categoryTitle = new ObservableField<>("");
    public ObservableField<Boolean > showIconSub = new ObservableField<>(true);
    public ObservableField<Boolean > showLogo = new ObservableField<>(true);
    public ObservableField<Bitmap > categoryIconUrl = new ObservableField<>();


    public CategoryItemViewModel(Context mContext, CategoryResponse item) {
        try{
            categoryTitle.set(item.getCategoryName());

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            new Thread(runnable).start();
            Glide.with(mContext)
                    .load(item.getCategoryIcon())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.bg_placeholder)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            categoryIconUrl.set(resource);
                        }
                    });

            if(item.getIsLeaf().equals("y"))
            {
                showIconSub.set(false);
            }else
            {
                showIconSub.set(true);
            }

            if(item.getCategoryIcon().equals(""))
            {
                showLogo.set(false);
            }else
            {
                showLogo.set(true);
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
