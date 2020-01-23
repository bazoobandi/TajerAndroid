package com.zbam.tajer.ui.option.usernamechannel;

import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.ui.option.createchannel.CreateChannelActivity;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 11/13/2018.
 */

public class UserNameChannelViewModel extends BaseViewModel<UserNameChannelActivity> {


    public UserNameChannelViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }
    public void onClickCreate()
    {
        getActivity().openContactIntent();
    }


    public void callChannelUserNameCreate(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
    {
        BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_CHANNEL_USERNAME_CREATE,this);
        iApiCall.callAdvertisingCreate(map).enqueue(baseCallback);
    }


    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_CHANNEL_USERNAME_CREATE:
                        getActivity().openContactIntent();
                        break;
                }
            }
        }catch (Exception e)
        {
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
