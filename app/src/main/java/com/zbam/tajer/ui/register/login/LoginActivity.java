package com.zbam.tajer.ui.register.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivityLoginBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 7/23/2018.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel>{

    @Inject
    LoginViewModel mLoginViewModel;

    ActivityLoginBinding mActivityLoginBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setActivity(this);
    }

    public void login()
    {
        HashMap<String,String> map = new HashMap<>();

        String username = mActivityLoginBinding.edtLoginUsername.getText().toString();
        String password = mActivityLoginBinding.edtLoginPassword.getText().toString();

        map.put(AppConstants.REQ_KEY_USERNAME, username);
        map.put(AppConstants.REQ_KEY_PASSWORD, password);
        mLoginViewModel.login(this,iApiCall,map);
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
}
