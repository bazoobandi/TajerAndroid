package com.zbam.tajer.ui.main.channelDetails;

import android.os.Bundle;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivityChannelDetailsBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.dialog.contactInfo.ContactInfoDialog;
import com.zbam.tajer.ui.dialog.imageView.ImageViewShowDialog;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;

import javax.inject.Inject;


/**
 * Created by z.bazoubandi on 10/28/2018.
 */

public class ChannelDetailsActivity extends BaseActivity<ActivityChannelDetailsBinding,ChannelDetailsViewModel> {


    @Inject
    ChannelDetailsViewModel mChannelDetailsViewModel;

    ActivityChannelDetailsBinding mActivityChannelDetailsBinding;

    private String token;
    private ContactInfoDialog contactInfoDialog;
    private ImageViewShowDialog imageViewShowDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityChannelDetailsBinding = getViewDataBinding();
        mChannelDetailsViewModel.setActivity(this);
        if (getIntent().hasExtra(AppConstants.REQ_KEY_CHANNEL_TOKEN)){
            mChannelDetailsViewModel.setToken(getIntent().getExtras().getString(AppConstants.REQ_KEY_CHANNEL_TOKEN));
        }
        getChannelDetails();
    }


    public void getChannelDetails()
    {
        HashMap<String,String> map = new HashMap<>();
        map.put(AppConstants.REQ_KEY_CHANNEL_TOKEN, mChannelDetailsViewModel.getToken());
        //map.put(AppConstants.REQ_KEY_TOKEN, "EB8190CF1CMTc0OTY=");
        mChannelDetailsViewModel.callChannelDetails(this,map,iApiCall);
    }



    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_channel_details;
    }

    @Override
    public ChannelDetailsViewModel getViewModel() {
        return mChannelDetailsViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }



}

