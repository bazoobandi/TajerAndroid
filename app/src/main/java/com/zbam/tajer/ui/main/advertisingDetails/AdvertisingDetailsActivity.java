package com.zbam.tajer.ui.main.advertisingDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.util.DisplayMetrics;
import android.view.View;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.costum.adapter.AdvertisingDetailsImgeAdapter;
import com.zbam.tajer.costum.adapter.AdvertisingDetailsOthersAdapter;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingOthersResponse;
import com.zbam.tajer.databinding.ActivityAdvertisingDetailsBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.dialog.contactInfo.ContactInfoDialog;
import com.zbam.tajer.ui.dialog.imageView.ImageViewShowDialog;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.LinePagerIndicatorDecoration;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by z.bazoubandi on 8/2/2018.
 */

public class AdvertisingDetailsActivity extends BaseActivity
        <ActivityAdvertisingDetailsBinding,AdvertisingDetailsViewModel> implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    @Inject
    AdvertisingDetailsViewModel mAdvertisingDetailsViewModel;

    @Inject
    AdvertisingDetailsImgeAdapter mAdvertisingDetailsImgeAdapter;

    @Inject
    AdvertisingDetailsOthersAdapter mAdvertisingDetailsOthersAdapter;

    @Inject
    @Named("HORIZONTAL_LLM")
    LinearLayoutManager llm;

    @Inject
    @Named("VERTICAL_LLM")
    LinearLayoutManager llmOthers;

    ActivityAdvertisingDetailsBinding mActivityAdvertisingDetailsBinding;

    private String token;
    private ContactInfoDialog contactInfoDialog;
    private ImageViewShowDialog imageViewShowDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAdvertisingDetailsBinding = getViewDataBinding();
        mAdvertisingDetailsViewModel.setActivity(this);
        if (getIntent().hasExtra(AppConstants.REQ_KEY_TOKEN)){
            mAdvertisingDetailsViewModel.setToken(getIntent().getExtras().getString(AppConstants.REQ_KEY_TOKEN));
        }
        initImg();
        initOthers();
        getAdvertisingDetails();
    }

    public void initImg()
    {
        mActivityAdvertisingDetailsBinding.rvAdvertisingDetailsImg.setAdapter(mAdvertisingDetailsImgeAdapter);
        mActivityAdvertisingDetailsBinding.rvAdvertisingDetailsImg.setLayoutManager(llm);
        mActivityAdvertisingDetailsBinding.rvAdvertisingDetailsImg.addItemDecoration(new LinePagerIndicatorDecoration());
        mAdvertisingDetailsImgeAdapter.setListener(new AdvertisingDetailsImgeAdapter.OnAdvertisingClickListener() {
            @Override
            public void onItemClickListener(List<AdvertisingImageResponse> advertisingImageResponse) {
                openImageViewShowDialog(advertisingImageResponse);
            }
        });


        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView( mActivityAdvertisingDetailsBinding.rvAdvertisingDetailsImg);

        mActivityAdvertisingDetailsBinding.rvAdvertisingDetailsImg.setItemAnimator(new DefaultItemAnimator());

        //screen size
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float dpHeightImg = dpHeight/2;

    }

    public void initOthers()
    {
        mActivityAdvertisingDetailsBinding.rvAdvertisingDetailsOthers.setAdapter(mAdvertisingDetailsOthersAdapter);
        mActivityAdvertisingDetailsBinding.rvAdvertisingDetailsOthers.setLayoutManager(llmOthers);
        mActivityAdvertisingDetailsBinding.rvAdvertisingDetailsOthers.setItemAnimator(new DefaultItemAnimator());

    }

    public void updateImage(List<AdvertisingImageResponse> items)
    {
        mAdvertisingDetailsImgeAdapter.addItems(items);
    }

    public void updateOthersFields(List<AdvertisingOthersResponse> items)
    {
        mAdvertisingDetailsOthersAdapter.addItems(items);
    }

    public void getAdvertisingDetails()
    {
        HashMap<String,String> map = new HashMap<>();
        map.put(AppConstants.REQ_KEY_TOKEN, mAdvertisingDetailsViewModel.getToken());
        //map.put(AppConstants.REQ_KEY_TOKEN, "EB8190CF1CMTc0OTY=");
        mAdvertisingDetailsViewModel.callAdvertisingDetails(this,map,iApiCall);
    }

    public void openContactInfoDialog()
    {
        try
        {
            String tel= mAdvertisingDetailsViewModel.telAdvertising.get().toString();
            String mobile = mAdvertisingDetailsViewModel.mobileAdvertising.get().toString();
            String email = mAdvertisingDetailsViewModel.emailAdvertising.get().toString();

            contactInfoDialog = new ContactInfoDialog(tel,mobile,email);
            contactInfoDialog.setOnCancelListener(new ContactInfoDialog.OnCancelListener() {
                @Override
                public void onCancel() {

                }
            });
            contactInfoDialog.show(getSupportFragmentManager());
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void openImageViewShowDialog(List<AdvertisingImageResponse> advertisingImageResponse)
    {
        try
        {
            imageViewShowDialog = new ImageViewShowDialog(advertisingImageResponse);
            imageViewShowDialog.setOnCancelListener(new ImageViewShowDialog.OnCancelListener() {
                @Override
                public void onCancel() {

                }
            });
            imageViewShowDialog.show(getSupportFragmentManager());
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advertising_details;
    }

    @Override
    public AdvertisingDetailsViewModel getViewModel() {
        return mAdvertisingDetailsViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

}
