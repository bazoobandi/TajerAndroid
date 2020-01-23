package com.zbam.tajer.ui.main.channelDetails;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.ChannelShowResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.ui.main.channelDetails.ChannelDetailsActivity;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 10/28/2018.
 */

public class ChannelDetailsViewModel extends BaseViewModel<ChannelDetailsActivity> {


    public ChannelDetailsViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public ObservableField<String> channelTel = new ObservableField<>("");
    public ObservableField<String> channelMobile = new ObservableField<>("");
    public ObservableField<String> channelCity = new ObservableField<>("");
    public ObservableField<String> channelAddress = new ObservableField<>("");
    public ObservableField<Bitmap> channelUlr = new ObservableField<>();
    public ObservableField<String> channelTitle = new ObservableField<>();
    public ObservableField<String> channelDesc = new ObservableField<>();
    public ObservableField<String> channelWebSite = new ObservableField<>();
    public ObservableField<String> channelEmail = new ObservableField<>();
    public ObservableField<String> channelId = new ObservableField<>();



    private ChannelShowResponse channelShowResponse;
    private Context mContext;
    private String token;

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }

    public void onClickShowDetails()
    {
        try {
            HashMap<String,String> map = new HashMap<>();
            String channelToken = "";
            if(channelShowResponse.getChannelToken()!=null)
                channelToken = channelShowResponse.getChannelToken();

            map.put(AppConstants.REQ_KEY_CHANNEL_TOKEN_AD_LIST,channelToken);
            getActivity().openAdvertisingActivity(map);
        }catch (Exception e)
        {
           e.printStackTrace();
        }

    }

    public void onClickJoin()
    {
        //getActivity().openContactInfoDialog();
    }

    public void callChannelDetails(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
    {
        BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_CHANNEL_DETAILS,this);
       iApiCall.callChannelDetails(map).enqueue(baseCallback);
    }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_CHANNEL_DETAILS:

                       channelShowResponse = (ChannelShowResponse) response.body().getData().get(0);
                       updateData(channelShowResponse);
                        break;
                }
            } else {
                CommonUtils.showAlert(getActivity(), getActivity().getString(R.string.title_warning), response.body().getSettings().getMessage(), null);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void updateData(ChannelShowResponse channelShowResponse)
    {
        channelTitle.set(channelShowResponse.getChannelName());
        channelAddress.set(channelShowResponse.getChannelAddress());
        channelCity.set(channelShowResponse.getCityName());
        channelTel.set(channelShowResponse.getChannelTel());
        channelMobile.set(channelShowResponse.getChannelMobile());
        channelDesc.set(channelShowResponse.getChannelDesc());
        channelWebSite.set(channelShowResponse.getChannelWebsite());
        channelEmail.set(channelShowResponse.getChannelEmail());
        channelId.set(channelShowResponse.getChannelUserName());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        new Thread(runnable).start();
        Glide.with(getActivity())
                .load(channelShowResponse.getChannelLogo())
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.bg_placeholder)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        channelUlr.set(resource);
                    }
                });
    }

    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {

    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {

    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

