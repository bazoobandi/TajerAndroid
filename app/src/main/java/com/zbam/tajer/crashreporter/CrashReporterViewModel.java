package com.zbam.tajer.crashreporter;

import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 9/30/2018.
 */

public class CrashReporterViewModel  extends BaseViewModel<CrashReporterActivity> {

    public CrashReporterViewModel(DataManager dataManager)
    {
        super(dataManager);
    }

    public void onClickStartApp()
    {
        getActivity().openSplashActivity();
    }

    public void callSendAppCrashInfo(BaseActivity activity
            , IApiCall iApiCall, HashMap<String,String> map)
    {
        try {
            BaseCallback baseCallback = new BaseCallback
                    (activity, iApiCall, false
                            , getDataManager()
                            , AppConstants.API_CODE_CRASH_INFO,
                            this);
            iApiCall.callCrashData(map).enqueue(baseCallback);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {

    }


    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {

    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {

    }
}
