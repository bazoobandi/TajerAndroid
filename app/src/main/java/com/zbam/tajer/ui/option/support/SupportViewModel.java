
package com.zbam.tajer.ui.option.support;


import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.SupportTopicResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.ui.option.support.SupportActivity;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;
import java.util.List;

import retrofit2.Response;


/**
 * Created by zb on 2/14/2018.
 */

public class SupportViewModel extends BaseViewModel<SupportActivity> {


    public SupportViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickBack(){
        getActivity().onBackPressed();
    }

    public void onClickCallSupport(){
        //getActivity().makePhoneCallIntent(getDataManager().getConfigData().getSupportCallNumber());
    }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_SUPPORT_TOPICS:
                        //List<SupportTopicResponse> supportTopicResponses = response.body().getData();
                       // getActivity().updateSupportTopics(supportTopicResponses);
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

    public void getSupportTopics(BaseActivity baseActivity, IApiCall iApiCall, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(
                    baseActivity,
                    iApiCall,
                    true,
                    getDataManager(),
                    AppConstants.API_CODE_SUPPORT_TOPICS,
                    this
            );
            //iApiCall.getSupportTopics(map).enqueue(baseCallback);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
