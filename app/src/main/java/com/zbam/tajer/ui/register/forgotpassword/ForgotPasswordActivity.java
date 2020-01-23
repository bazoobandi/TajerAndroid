package com.zbam.tajer.ui.register.forgotpassword;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivityForgotPasswordBinding;
import com.zbam.tajer.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 7/23/2018.
 */

public class ForgotPasswordActivity extends BaseActivity<ActivityForgotPasswordBinding,ForgotPasswordViewModel>{

    @Inject
    ForgotPasswordViewModel mForgotPasswordViewModel;

    ActivityForgotPasswordBinding mActivityForgotPasswordBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityForgotPasswordBinding = getViewDataBinding();
        mForgotPasswordViewModel.setActivity(this);
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    public ForgotPasswordViewModel getViewModel() {
        return mForgotPasswordViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
}
