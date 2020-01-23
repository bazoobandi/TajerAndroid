package com.zbam.tajer.ui.main.parametertype;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.costum.adapter.ParameterTypeAdapter;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.ParameterTypeResponse;
import com.zbam.tajer.databinding.ActivityParameterTypeBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateActivity;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 9/10/2018.
 */

public class ParameterTypeActivity extends BaseActivity<ActivityParameterTypeBinding,ParameterTypeViewModel> {

    @Inject
    ParameterTypeViewModel mParameterTypeViewModel;

    @Inject
    ParameterTypeAdapter mParameterTypeAdapter;

    @Inject
    LinearLayoutManager llm;

    private ActivityParameterTypeBinding mActivityParameterTypeBinding;
    private String parameterTypeOpenBy;
    private String parameterTypeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActivityParameterTypeBinding = getViewDataBinding();
        mParameterTypeViewModel.setActivity(this);
        if(getIntent().hasExtra(AppConstants.REQ_KEY_INTENT_PARAMETER_TYPE_OPEN_BY))
        {
            parameterTypeOpenBy = getIntent().getExtras().getString(AppConstants.REQ_KEY_INTENT_PARAMETER_TYPE_OPEN_BY);
        }
        if(getIntent().hasExtra(AppConstants.REQ_KEY_INTENT_PARAMETER_TYPE_ACTIVITY))
        {
            parameterTypeActivity = getIntent().getExtras().getString(AppConstants.REQ_KEY_INTENT_PARAMETER_TYPE_ACTIVITY);
        }

        init();
        getParameterTypeList("0");
        mParameterTypeViewModel.isFirstLevel.set(true);
        mParameterTypeViewModel.isNotFirstLevel.set(false);
    }

    public void init()
    {
        mActivityParameterTypeBinding.rvCarTypeList.setLayoutManager(llm);
        mActivityParameterTypeBinding.rvCarTypeList.setAdapter(mParameterTypeAdapter);
        mActivityParameterTypeBinding.rvCarTypeList.setItemAnimator(new DefaultItemAnimator());

        mParameterTypeAdapter.setListener(new ParameterTypeAdapter.OnCarTypeClickListener() {

            @Override
            public void onItemClickListener(ParameterTypeResponse parameterTypeResponse) {
                if(parameterTypeResponse.getIsLeaf().equals("y") && parameterTypeActivity.equals("car-brand"))
                {
                    if(parameterTypeOpenBy !=null && parameterTypeOpenBy.equals("show"))
                    {
                        HashMap<String,String> params= new HashMap<>();
                        params.put(AppConstants.REQ_KEY_PARAMETER_TYPE_SEARCH, parameterTypeResponse.getParameterTypeId());
                        openAdvertisingActivity(params);
                    }

                    if(parameterTypeOpenBy !=null && parameterTypeOpenBy.equals("create"))
                    {
                        AdvertisingCreateActivity.createInterface.onItemCarTypeClickListener(parameterTypeResponse.getParamTypeName()
                                , parameterTypeResponse.getParameterTypeId());
                        closeParameterTypeActivity();
                    }

                }else
                {
                    getParameterTypeList(parameterTypeResponse.getParameterTypeId());
                    mParameterTypeViewModel.isFirstLevel.set(false);
                    mParameterTypeViewModel.isNotFirstLevel.set(true);
                    mParameterTypeViewModel.parameterTypeId.set(parameterTypeResponse.getParameterTypeId());
                    mParameterTypeViewModel.parameterTypeName.set(parameterTypeResponse.getParamTypeName());
                }
                if(parameterTypeResponse.getIsLeaf().equals("y") && parameterTypeActivity.equals("mobile-brand"))
                {
                    if(parameterTypeOpenBy !=null && parameterTypeOpenBy.equals("show"))
                    {
                        HashMap<String,String> params= new HashMap<>();
                        params.put(AppConstants.REQ_KEY_PARAMETER_TYPE_SEARCH, parameterTypeResponse.getParameterTypeId());
                        openAdvertisingActivity(params);
                    }

                    if(parameterTypeOpenBy !=null && parameterTypeOpenBy.equals("create"))
                    {
                        AdvertisingCreateActivity.createInterface.onItemMobileTypeClickListener(parameterTypeResponse.getParamTypeName()
                                , parameterTypeResponse.getParameterTypeId());
                        closeParameterTypeActivity();
                    }

                }else
                {
                    getParameterTypeList(parameterTypeResponse.getParameterTypeId());
                    mParameterTypeViewModel.isFirstLevel.set(false);
                    mParameterTypeViewModel.isNotFirstLevel.set(true);
                    mParameterTypeViewModel.parameterTypeId.set(parameterTypeResponse.getParameterTypeId());
                    mParameterTypeViewModel.parameterTypeName.set(parameterTypeResponse.getParamTypeName());
                }

            }
        });

        mActivityParameterTypeBinding.etCarTypeSearchStatic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mParameterTypeAdapter.filter(s.toString());
            }
        });
    }

    public void getParameterTypeList(String parentId)
    {
        HashMap<String,String> map = new HashMap<>();

        map.put(AppConstants.REQ_KEY_PARENT_ID,parentId);
        map.put(AppConstants.REQ_KEY_PARAMS_NAME,parameterTypeActivity);
        mParameterTypeViewModel.callParameterTypeList(this,iApiCall,map);
    }

    public void getParentParameterType(String parentId)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put(AppConstants.REQ_KEY_PARAMETER_TYPE_SEARCH,parentId);
        mParameterTypeViewModel.callParentParameterType(this,iApiCall,map);
    }

    public void updateParameterType(List<ParameterTypeResponse> news) {
        mParameterTypeAdapter.addItems(news);
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_parameter_type;
    }

    @Override
    public ParameterTypeViewModel getViewModel() {
        return mParameterTypeViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
}
