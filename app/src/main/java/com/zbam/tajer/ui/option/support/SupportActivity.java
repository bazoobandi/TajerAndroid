package com.zbam.tajer.ui.option.support;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.costum.adapter.SupportAdapter;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.SupportTopicResponse;
import com.zbam.tajer.databinding.ActivitySupportBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.utils.AppConstants;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zb on 2/14/2018.
 */

public class SupportActivity extends BaseActivity<ActivitySupportBinding, SupportViewModel> {

    @Inject
    SupportViewModel mSupportViewModel;

    @Inject
    SupportAdapter mSupportAdapter ;

    @Inject
    LinearLayoutManager llm ;


    private ActivitySupportBinding mActivitySupportBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtil.setTransparent(this);
        mActivitySupportBinding = getViewDataBinding();
        mSupportViewModel.setActivity(this);
        initView();
        getSupportTopics();
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    private void initView() {
        try {
            mActivitySupportBinding.rvSupportTopics.setLayoutManager(llm);
            mActivitySupportBinding.rvSupportTopics.setItemAnimator(new DefaultItemAnimator());
            mActivitySupportBinding.rvSupportTopics.setAdapter(mSupportAdapter);
//            mSupportAdapter.setListener(new SupportAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(SupportTopicResponse topic) {
//                   // openMakeComplaintActivity(topic, mSupportViewModel.getRideId());
//                }
//            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void getSupportTopics() {
        try {
            HashMap<String, String> map = new HashMap<>();
//            map.put(AppConstants.REQ_KEY_PASSENGER_ID, mSupportViewModel.getDataManager().getPassengerId());
//            map.put(AppConstants.REQ_KEY_PERSON_TYPE, AppConstants.TYPE_PASSENGER);
          //  mSupportViewModel.getSupportTopics(this, iApiCall, map);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public SupportViewModel getViewModel() {
        return mSupportViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_support;
    }

    public void updateSupportTopics(List<SupportTopicResponse> supportTopicResponses) {
        mSupportAdapter.addItems(supportTopicResponses);
    }
}
