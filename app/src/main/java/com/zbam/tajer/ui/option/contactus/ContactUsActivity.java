package com.zbam.tajer.ui.option.contactus;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivityContactUsBinding;
import com.zbam.tajer.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 9/17/2018.
 */

public class ContactUsActivity extends BaseActivity<ActivityContactUsBinding, ContactUsViewModel> {

    @Inject
    ContactUsViewModel mContactUsViewModel;


    private ActivityContactUsBinding mActivityContactUsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtil.setTransparent(this);
        mActivityContactUsBinding = getViewDataBinding();
        mContactUsViewModel.setActivity(this);
        getContactUsTopics();
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }
    

    private void getContactUsTopics() {
        try {
            HashMap<String, String> map = new HashMap<>();
//            map.put(AppConstants.REQ_KEY_PASSENGER_ID, mContactUsViewModel.getDataManager().getPassengerId());
//            map.put(AppConstants.REQ_KEY_PERSON_TYPE, AppConstants.TYPE_PASSENGER);
            //  mContactUsViewModel.getContactUsTopics(this, iApiCall, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public ContactUsViewModel getViewModel() {
        return mContactUsViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_us;
    }
    
}