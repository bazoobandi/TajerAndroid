package com.zbam.tajer.ui.option.aboutus;

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
 * Created by z.bazoubandi on 9/17/2018.
 */

public class AboutUsViewModel extends BaseViewModel<AboutUsActivity> {


    public AboutUsViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickBack(){
        getActivity().onBackPressed();
    }


    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_SUPPORT_TOPICS:
                        //List<AboutUsTopicResponse> supportTopicResponses = response.body().getData();
                        // getActivity().updateAboutUsTopics(supportTopicResponses);
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

    public void getAboutUsTopics(BaseActivity baseActivity, IApiCall iApiCall, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(
                    baseActivity,
                    iApiCall,
                    true,
                    getDataManager(),
                    AppConstants.API_CODE_SUPPORT_TOPICS,
                    this
            );
            //iApiCall.getAboutUsTopics(map).enqueue(baseCallback);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

