package com.zbam.tajer.ui.option.createchannel;

import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.ChannelResponse;
import com.zbam.tajer.data.model.api.response.UploadImageResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.utils.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by z.bazoubandi on 11/12/2018.
 */

public class CreateChannelViewModel extends BaseViewModel<CreateChannelActivity>{

    public ObservableField<String> imgeUrlStr = new ObservableField<>("");
    public ObservableField<Bitmap> imgeUrl = new ObservableField<>();
    public ObservableField<String> categoryName = new ObservableField<>("");
    public ObservableField<String> categoryId = new ObservableField<>("");
    public ObservableField<String> cityName = new ObservableField<>("");
    public ObservableField<String> cityId = new ObservableField<>("");

    public String categoryOpenBy;
    public String cityOpenBy;
    String channelToken = "";

    private ChannelResponse mChannelResponse;

    public CreateChannelViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }
    public void onAddImage()
    {
        getActivity().openImageIntent(1);
    }

    public void openCategoryList()
    {
        categoryOpenBy = "channelCreate";
        getActivity().openCategoryActivity(categoryOpenBy);
    }

    public void onClearCategory()
    {
        categoryName.set("");
        categoryId.set("");
    }
    public void onClearCity()
    {
        cityName.set("");
        cityId.set("");
    }

    public void openCityList()
    {
        cityOpenBy = "channelCreate";
        getActivity().openCityActivity(cityOpenBy);
    }
    public void onClickCreate()
    {
        getActivity().callChannelCreate();
    }

    public void callChannelCreate(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
    {
        BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_CHANNEL_CREATE,this);
        iApiCall.callChannelCreate(map).enqueue(baseCallback);
    }


    public void callUploadChannelImage(BaseActivity activity, MultipartBody.Part fileToUpload, RequestBody filename, IApiCall iApiCall)
    {
        BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_UPLOAD_CHANNEL_IMAGE,this);
        iApiCall.uploadChannelImage(fileToUpload,filename).enqueue(baseCallback);
    }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_CHANNEL_CREATE:
                        List<ChannelResponse> channelResponses = response.body().getData();
                        mChannelResponse = channelResponses.get(0);
                        channelToken = mChannelResponse.getChannelToken();
                        getActivity().openUserNameChannelActivity(channelToken);
                        break;
                    case AppConstants.API_CODE_UPLOAD_CHANNEL_IMAGE:
                        getActivity().progressDialog.dismiss();
                        UploadImageResponse uploadImageResponse = (UploadImageResponse) response.body().getData().get(0);
                        uploadImage(uploadImageResponse);
                        break;
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void uploadImage(UploadImageResponse uploadImageResponse) {
        try {

//            imgeIcon1.set(false);
//            imge1.set(true);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            new Thread(runnable).start();
            Glide.with(getActivity())
                    .load(uploadImageResponse.getImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.bg_placeholder)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            imgeUrl.set(resource);
                        }
                    });
            imgeUrlStr.set(uploadImageResponse.getImageUrl());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {

    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {

    }


}
