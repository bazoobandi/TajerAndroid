package com.zbam.tajer.ui.option.rules;

import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.ui.option.aboutus.AboutUsActivity;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 9/29/2018.
 */

public class RulesViewModel extends BaseViewModel<RulesActivity> {


    public RulesViewModel(DataManager dataManager) {
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



}
