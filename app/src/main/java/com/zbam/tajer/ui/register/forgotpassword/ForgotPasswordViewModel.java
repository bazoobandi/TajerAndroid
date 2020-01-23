package com.zbam.tajer.ui.register.forgotpassword;

import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseViewModel;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 7/23/2018.
 */

public class ForgotPasswordViewModel extends BaseViewModel<ForgotPasswordActivity>{


    public ForgotPasswordViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickBack()
    {

    }

    public void onClickForgotPassword()
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
