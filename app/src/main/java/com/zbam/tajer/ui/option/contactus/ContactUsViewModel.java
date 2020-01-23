package com.zbam.tajer.ui.option.contactus;

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

public class ContactUsViewModel extends BaseViewModel<ContactUsActivity> {


    public ContactUsViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickBack(){
        getActivity().onBackPressed();
    }

    public void onClickSendMessage()
    {

    }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_SUPPORT_TOPICS:
                        //List<ContactUsTopicResponse> supportTopicResponses = response.body().getData();
                        // getActivity().updateContactUsTopics(supportTopicResponses);
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

    public void getContactUsTopics(BaseActivity baseActivity, IApiCall iApiCall, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(
                    baseActivity,
                    iApiCall,
                    true,
                    getDataManager(),
                    AppConstants.API_CODE_SUPPORT_TOPICS,
                    this
            );
            //iApiCall.getContactUsTopics(map).enqueue(baseCallback);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
