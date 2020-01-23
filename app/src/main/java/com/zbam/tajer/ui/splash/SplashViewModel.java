package com.zbam.tajer.ui.splash;

import android.databinding.ObservableField;

import com.zbam.tajer.BuildConfig;
import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.LastVersionResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.CommonUtils;

import java.util.HashMap;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 7/23/2018.
 */

public class SplashViewModel extends BaseViewModel<SplashActivity>{


    public ObservableField<Boolean> isNetworkValid = new ObservableField<>(true);
    private LastVersionResponse lastVersionResponse;

    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickRetry()
    {
        getActivity().startApp();
    }

    public void callGetLastVersion(BaseActivity baseActivity , IApiCall iApiCall , HashMap<String ,String> map) {
        try{
            BaseCallback baseCallback = new BaseCallback(
                    baseActivity ,
                    iApiCall ,
                    false,
                    getDataManager() ,
                    AppConstants.API_CODE_GET_LAST_VERSION ,
                    this);
            iApiCall.getLastVersion(map).enqueue(baseCallback);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public void setNetworkStatus(boolean networkStatus) {
        isNetworkValid.set(networkStatus);
    }


    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {

        try {
            if(response.body().getSettings().issuccess())
            {
                switch (requestCode)
                {
                    case AppConstants.API_CODE_GET_LAST_VERSION :

                        lastVersionResponse = (LastVersionResponse) response.body().getData().get(0);
                        if(Integer.parseInt(lastVersionResponse.getVersionNumber()) >  BuildConfig.VERSION_CODE)
                        {
                            CommonUtils.showAlert(getActivity(),getActivity().getString(R.string.title_warning), getActivity().getString(R.string.title_new_apk),null);
                            getActivity().getNewAPK(lastVersionResponse.getVersionUrl());
                        }else
                        {
                            HashMap<String,String> map = new HashMap<>();
                            getActivity().openAdvertisingActivity(map);
                        }

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
