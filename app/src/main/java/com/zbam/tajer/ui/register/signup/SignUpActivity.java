package com.zbam.tajer.ui.register.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.databinding.library.baseAdapters.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivitySignupBinding;
import com.zbam.tajer.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 7/22/2018.
 */

public class SignUpActivity extends BaseActivity<ActivitySignupBinding,SignUpViewModel> {


    @Inject
    SignUpViewModel mSignUpViewModel;

    ActivitySignupBinding mActivitySignupBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignupBinding = getViewDataBinding();
        mSignUpViewModel.setActivity(this);
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
    }

    @Override
    public SignUpViewModel getViewModel() {
        return mSignUpViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
}
