package com.zbam.tajer.ui.register.login;

import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.LoginResponse;
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

public class LoginViewModel extends BaseViewModel<LoginActivity>{


    public LoginViewModel(DataManager dataManager) {
        super(dataManager);
    }

    private LoginResponse loginResponse;

    public void onClickLogin()
    {
            getActivity().login();
    }
    public void onClickForgotPassword()
    {
        getActivity().openForgotPasswordActivity();
    }

    public void onClickSignUp()
    {
        getActivity().openSignupActivity();
    }

    public void login(BaseActivity baseActivity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(baseActivity,iApiCall
                ,true,getDataManager(), AppConstants.API_CODE_LOGIN,this);
        iApiCall.callLogin(map).enqueue(baseCallback);
    }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {

        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_LOGIN:
                        loginResponse = (LoginResponse) response.body().getData().get(0);
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

    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {

    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {

    }
}
