package com.zbam.tajer.ui.option.rules;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivityRulesBinding;
import com.zbam.tajer.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 9/29/2018.
 */

public class RulesActivity extends BaseActivity<ActivityRulesBinding, RulesViewModel> {

    @Inject
    RulesViewModel mRulesViewModel;


    private ActivityRulesBinding mActivityAboutUsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtil.setTransparent(this);
        mActivityAboutUsBinding = getViewDataBinding();
        mRulesViewModel.setActivity(this);
        mActivityAboutUsBinding.txtRules.setText(Html.fromHtml(getString(R.string.rules)));
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }



    @Override
    public RulesViewModel getViewModel() {
        return mRulesViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rules;
    }

}
