package com.zbam.tajer.ui.main;

import android.app.Activity;

import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;

/**
 * Created by z.bazoubandi on 7/15/2018.
 */

public class MainActivity extends BaseActivity{


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }
}
