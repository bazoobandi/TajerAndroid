package com.zbam.tajer.ui.main.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.costum.adapter.AdvertisingAdapter;
import com.zbam.tajer.costum.adapter.CategoryAdapter;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
import com.zbam.tajer.data.model.api.response.CategoryResponse;
import com.zbam.tajer.databinding.ActivityCategoryBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.main.advertisingDetails.AdvertisingDetailsActivity;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateActivity;
import com.zbam.tajer.ui.main.advertisingsearch.AdvertisingSearchActivity;
import com.zbam.tajer.ui.option.createchannel.CreateChannelActivity;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 8/25/2018.
 */

public class CategoryActivity extends BaseActivity<ActivityCategoryBinding,CategoryViewModel>{

    @Inject
    CategoryViewModel mCategoryViewModel;

    @Inject
    CategoryAdapter mCategoryAdapter;

    @Inject
    LinearLayoutManager llm;

    private ActivityCategoryBinding mActivityCategoryBinding;
    private String categoryOpenBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActivityCategoryBinding = getViewDataBinding();
        mCategoryViewModel.setActivity(this);
        if(getIntent().hasExtra(AppConstants.REQ_KEY_INTENT_CATEGORY_OPEN_BY))
        {
            categoryOpenBy = getIntent().getExtras().getString(AppConstants.REQ_KEY_INTENT_CATEGORY_OPEN_BY);
        }

        init();
        getCategoryList("0");
        mCategoryViewModel.isFirstLevel.set(true);
        mCategoryViewModel.isNotFirstLevel.set(false);
    }

    public void init()
    {

//        mActivityCategoryBinding.menuButtom.category.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getCategoryList("0");
//            }
//        });
//        mActivityCategoryBinding.menuButtom.search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap<String,String> params = new HashMap<>();
//                openAdvertisingSearchActivity(params);
//            }
//        });
//        mActivityCategoryBinding.menuButtom.home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try{
//                    HashMap<String, String> params = new HashMap<>();
//                    openAdvertisingActivity(params);
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//        });
        mActivityCategoryBinding.rvCategoryList.setLayoutManager(llm);
        mActivityCategoryBinding.rvCategoryList.setAdapter(mCategoryAdapter);
        mActivityCategoryBinding.rvCategoryList.setItemAnimator(new DefaultItemAnimator());

        mCategoryAdapter.setListener(new CategoryAdapter.OnCategoryClickListener() {

            @Override
            public void onItemClickListener(CategoryResponse categoryResponse) {
                if(categoryResponse.getIsLeaf().equals("y"))
                {
                    if(categoryOpenBy!=null && categoryOpenBy.equals("show"))
                    {
                        HashMap<String,String> params= new HashMap<>();
                        params.put(AppConstants.REQ_KEY_CATEGORY_SEARCH,categoryResponse.getCategoryId());
                        openAdvertisingActivity(params);
                    }

                    if(categoryOpenBy!=null && categoryOpenBy.equals("search"))
                    {
                        AdvertisingSearchActivity.searchAdvertisingInterface.onItemClickListener(categoryResponse.getCategoryName()
                                ,categoryResponse.getCategoryId(),categoryResponse.getCategorySubject(),categoryResponse.getCategoryType());
                        closeCategoryActivity();
                    }

                    if(categoryOpenBy!=null && categoryOpenBy.equals("create"))
                    {
                        AdvertisingCreateActivity.createInterface.onItemClickListener(categoryResponse.getCategoryName()
                                ,categoryResponse.getCategoryId(),categoryResponse.getCategorySubject(),categoryResponse.getCategoryType());
                        closeCategoryActivity();
                    }

                    if(categoryOpenBy!=null && categoryOpenBy.equals("channelCreate"))
                    {
                        CreateChannelActivity.createInterface.onItemClickListener(categoryResponse.getCategoryName()
                                ,categoryResponse.getCategoryId());
                        closeCategoryActivity();
                    }

                }else
                {
                    getCategoryList(categoryResponse.getCategoryId());
                    mCategoryViewModel.isFirstLevel.set(false);
                    mCategoryViewModel.isNotFirstLevel.set(true);
                    mCategoryViewModel.categoryId.set(categoryResponse.getCategoryId());
                    mCategoryViewModel.categoryName.set(categoryResponse.getCategoryName());
                }

            }
        });

        mActivityCategoryBinding.etCategorySearchStatic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCategoryAdapter.filter(s.toString());
            }
        });
    }

    public void getCategoryList(String parentId)
    {
        HashMap<String,String> map = new HashMap<>();

        map.put(AppConstants.REQ_KEY_PARENT_ID,parentId);
        mCategoryViewModel.callCategoryList(this,iApiCall,map);
    }

    public void getParentCategory(String parentId)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put(AppConstants.REQ_KEY_CATEGORY_SEARCH,parentId);
        mCategoryViewModel.callParentCategory(this,iApiCall,map);
    }

    public void updateCategory(List<CategoryResponse> news) {
        mCategoryAdapter.addItems(news);
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    public CategoryViewModel getViewModel() {
        return mCategoryViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
}
