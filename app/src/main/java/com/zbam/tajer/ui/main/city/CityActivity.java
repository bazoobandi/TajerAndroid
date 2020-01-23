package com.zbam.tajer.ui.main.city;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FilterQueryProvider;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.costum.adapter.CityAdapter;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.CityResponse;
import com.zbam.tajer.databinding.ActivityCityBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.main.advertising.AdvertisingActivity;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateActivity;
import com.zbam.tajer.ui.option.createchannel.CreateChannelActivity;
import com.zbam.tajer.utils.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 9/4/2018.
 */

public class CityActivity extends BaseActivity<ActivityCityBinding,CityViewModel> {

    @Inject
    CityViewModel mCityViewModel;

    @Inject
    CityAdapter mCityAdapter;

    @Inject
    LinearLayoutManager llm;

    private ActivityCityBinding mActivityCityBinding;
    private String CityOpenBy;
    CityResponse cityResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActivityCityBinding = getViewDataBinding();
        mCityViewModel.setActivity(this);
        if(getIntent().hasExtra(AppConstants.REQ_KEY_INTENT_City_OPEN_BY))
        {
            CityOpenBy = getIntent().getExtras().getString(AppConstants.REQ_KEY_INTENT_City_OPEN_BY);
        }

        init();
        getCityList("0");
        mCityViewModel.isFirstLevel.set(true);
        mCityViewModel.isNotFirstLevel.set(false);
    }

    public void init()
    {
        mActivityCityBinding.rvCityList.setLayoutManager(llm);
        mActivityCityBinding.rvCityList.setAdapter(mCityAdapter);
        mActivityCityBinding.rvCityList.setItemAnimator(new DefaultItemAnimator());

        mCityAdapter.setListener(new CityAdapter.OnCityClickListener() {

            @Override
            public void onItemClickListener(CityResponse cityResponse) {
                try {
                        if (CityOpenBy != null && CityOpenBy.equals("show")) {
                            if (!cityResponse.getHaveLocality().equals("y")) {
                                HashMap<String, String> params = new HashMap<>();
                                //params.put(AppConstants.REQ_KEY_City_SEARCH,CityResponse.getCityId());
                                openAdvertisingActivity(params);
                            }else {
                                getCityList(cityResponse.getCityId());
                                mCityViewModel.isFirstLevel.set(false);
                                mCityViewModel.isNotFirstLevel.set(true);
                                mCityViewModel.CityId.set(cityResponse.getCityId());
                                mCityViewModel.CityName.set(cityResponse.getCityName());
                            }
                        }

                        if (CityOpenBy != null && CityOpenBy.equals("search")) {
                            if (cityResponse.getCityLocationType().equals("city")) {
                                HashMap<String, String> params = new HashMap<>();
                                mCityViewModel.getDataManager().updateCity(cityResponse.getCityId(), cityResponse.getCityName());
                                params.put(AppConstants.REQ_KEY_CITY_GLOBAL_SEARCH, cityResponse.getCityId());
                                openAdvertisingActivity(params);
                            }else {
                                getCityList(cityResponse.getCityId());
                                mCityViewModel.isFirstLevel.set(false);
                                mCityViewModel.isNotFirstLevel.set(true);
                                mCityViewModel.CityId.set(cityResponse.getCityId());
                                mCityViewModel.CityName.set(cityResponse.getCityName());
                            }

                            if (cityResponse.getCityLocationType().equals("state") && cityResponse.getCityId().equals("0")) {
                                HashMap<String, String> params = new HashMap<>();
                                mCityViewModel.getDataManager().updateCity(cityResponse.getCityId(), cityResponse.getCityName());
                                params.put(AppConstants.REQ_KEY_CITY_GLOBAL_SEARCH, cityResponse.getCityId());
                                openAdvertisingActivity(params);
                            }
                        }

                        if (CityOpenBy != null && CityOpenBy.equals("create")) {
                            if (!cityResponse.getHaveLocality().equals("y")) {
                                AdvertisingCreateActivity.createInterface.onItemCityClickListener(cityResponse.getCityName()
                                        , cityResponse.getCityId());
                                closeCityActivity();
                            }else {
                                getCityList(cityResponse.getCityId());
                                mCityViewModel.isFirstLevel.set(false);
                                mCityViewModel.isNotFirstLevel.set(true);
                                mCityViewModel.CityId.set(cityResponse.getCityId());
                                mCityViewModel.CityName.set(cityResponse.getCityName());
                            }
                        }
                        if (CityOpenBy != null && CityOpenBy.equals("jobCreate")) {
                            if (cityResponse.getCityLocationType().equals("city")) {
                                AdvertisingCreateActivity.createInterface.onItemCityByJobClickListener(cityResponse.getCityName()
                                        , cityResponse.getCityId());
                                closeCityActivity();
                            }else {
                                getCityList(cityResponse.getCityId());
                                mCityViewModel.isFirstLevel.set(false);
                                mCityViewModel.isNotFirstLevel.set(true);
                                mCityViewModel.CityId.set(cityResponse.getCityId());
                                mCityViewModel.CityName.set(cityResponse.getCityName());
                            }
                        }if (CityOpenBy != null && CityOpenBy.equals("channelCreate")) {
                            if (cityResponse.getCityLocationType().equals("city")) {
                                CreateChannelActivity.createInterface.onItemCityClickListener(cityResponse.getCityName()
                                        , cityResponse.getCityId());
                                closeCityActivity();
                            }else {
                                getCityList(cityResponse.getCityId());
                                mCityViewModel.isFirstLevel.set(false);
                                mCityViewModel.isNotFirstLevel.set(true);
                                mCityViewModel.CityId.set(cityResponse.getCityId());
                                mCityViewModel.CityName.set(cityResponse.getCityName());
                            }
                        }
                        if (CityOpenBy != null && CityOpenBy.equals("tripCreate")) {
                            if (cityResponse.getCityLocationType().equals("city")) {
                                AdvertisingCreateActivity.createInterface.onItemCityByTripClickListener(cityResponse.getCityName()
                                        , cityResponse.getCityId());
                                closeCityActivity();
                            }else {
                                getCityList(cityResponse.getCityId());
                                mCityViewModel.isFirstLevel.set(false);
                                mCityViewModel.isNotFirstLevel.set(true);
                                mCityViewModel.CityId.set(cityResponse.getCityId());
                                mCityViewModel.CityName.set(cityResponse.getCityName());
                            }
                        }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });



        mActivityCityBinding.etCitySearchStatic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCityAdapter.filter(s.toString());
            }
        });
    }

    public void getCityList(String parentId)
    {
        HashMap<String,String> map = new HashMap<>();

        map.put(AppConstants.REQ_KEY_PARENT_ID,parentId);
        mCityViewModel.callCityList(this,iApiCall,map);
    }

    public void getParentCity(String parentId)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put(AppConstants.REQ_KEY_CITY_SEARCH,parentId);
        mCityViewModel.callParentCity(this,iApiCall,map);
    }

    public void updateCity(List<CityResponse> news) {
        mCityAdapter.addItems(news);
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    public CityViewModel getViewModel() {
        return mCityViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
}
