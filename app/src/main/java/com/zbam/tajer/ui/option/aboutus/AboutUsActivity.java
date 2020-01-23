package com.zbam.tajer.ui.option.aboutus;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivityAboutusBinding;
import com.zbam.tajer.ui.base.BaseActivity;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 9/17/2018.
 */

public class AboutUsActivity extends BaseActivity<ActivityAboutusBinding, AboutUsViewModel> {

    @Inject
    AboutUsViewModel mAboutUsViewModel;


    private ActivityAboutusBinding mActivityAboutUsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtil.setTransparent(this);
        mActivityAboutUsBinding = getViewDataBinding();
        mAboutUsViewModel.setActivity(this);
        mActivityAboutUsBinding.txtAboutUs.setText(Html.fromHtml(getString(R.string.about_us_text)));
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }



    @Override
    public AboutUsViewModel getViewModel() {
        return mAboutUsViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_aboutus;
    }

}