package com.zbam.tajer.ui.register.signup;

import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseViewModel;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 7/22/2018.
 */

public class SignUpViewModel extends BaseViewModel<SignUpActivity>{


    public SignUpViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickSignUp()
    {

    }

    public void onClickLoginPage()
    {

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
