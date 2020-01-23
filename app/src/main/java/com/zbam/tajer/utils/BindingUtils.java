package com.zbam.tajer.utils;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by z.bazoubandi on 7/30/2018.
 */

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("bitmap")
    public static void setImageBitmap(ImageView iv , Bitmap bitmap){

        iv.setImageBitmap(bitmap);
    }

    @BindingAdapter("bitmap")
    public static void setImageBitmap(CircleImageView iv , Bitmap bitmap){

        iv.setImageBitmap(bitmap);
    }


    @BindingAdapter("visibilitySlideRight")
    public static void setVisibilitySlideRight(View v, boolean b) {
        try {
            TransitionSet set = new TransitionSet()
                    .addTransition(new Slide(Gravity.RIGHT))
                    .setDuration(500);
            TransitionManager.beginDelayedTransition((ViewGroup) v.getParent(), set);
            v.setVisibility(b ? View.VISIBLE : View.GONE);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @BindingAdapter("visibilitySlideBottom")
    public static void setVisibilitySlideBottom(View v, boolean b) {
        try {
            TransitionSet set = new TransitionSet()
                    .addTransition(new Slide(Gravity.BOTTOM))
                    .setDuration(500);
            TransitionManager.beginDelayedTransition((ViewGroup) v.getParent(), set);
            v.setVisibility(b ? View.VISIBLE : View.GONE);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
