package com.zbam.tajer.ui.main.advertising;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.zbam.tajer.BR;
import com.zbam.tajer.BuildConfig;
import com.zbam.tajer.R;
import com.zbam.tajer.costum.adapter.AdvertisingAdapter;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
import com.zbam.tajer.databinding.ActivityAdvertisingBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateInterface;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.CommonUtils;
import com.zbam.tajer.utils.EndlessRecyclerViewScrollListener;
import com.zbam.tajer.utils.NumberFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 7/21/2018.
 */

public class AdvertisingActivity extends BaseActivity<ActivityAdvertisingBinding,AdvertisingListViewModel>
{


    ActivityAdvertisingBinding mAdvertisingBinding;

    @Inject
    AdvertisingListViewModel mAdvertisingListViewModel;

    @Inject
    AdvertisingAdapter mAdvertisingAdapter;

    @Inject
     LinearLayoutManager llm;


    private int pageIndex = 1;
    private boolean loading = true;
    private String searchTxt="";
    private String searchCityTxt="";
    private int firstVisiblePosition;
    private int findFirstCompletelyVisibleItemPosition;
    private int findLastVisibleItemPosition;
    private int findLastCompletelyVisibleItemPosition;
    HashMap<String,String> params = new HashMap<>();
    private Menu mMainMenu;
    private String categoryOpenBy;

    public List<AdvertisingResponse> mAdvertisingResponseList = new ArrayList<>();
    private List<AdvertisingResponse> mAdvertisingSearchResponseList = new ArrayList<>();

    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdvertisingBinding = getViewDataBinding();
        mAdvertisingListViewModel.setActivity(this);
        if(getIntent().hasExtra(AppConstants.REQ_KEY_PARAMETR_SEARCH)){
            params = (HashMap<String, String>) getIntent().getSerializableExtra(AppConstants.REQ_KEY_PARAMETR_SEARCH);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        init();
        initDrawerNavigation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        mMainMenu = mAdvertisingBinding.navigationView.getMenu();
        updateMenuItemTitles();
        return true;
    }



    public void init()
    {
        try {
            mAdvertisingBinding.menuButtom.category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryOpenBy = "show";
                    openCategoryActivity(categoryOpenBy);
                }
            });

            mAdvertisingBinding.menuButtom.btnMainAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAdvertisingCreateActivity();
                }
            });
            mAdvertisingBinding.menuButtom.search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAdvertisingSearchActivity(params);
                }
            });
            mAdvertisingBinding.menuButtom.setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openChannelActivity(params);
                }
            });
            mAdvertisingBinding.menuButtom.home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        params.clear();
                        mAdvertisingResponseList = new ArrayList<>();
                        callAdvertisingList(1, params);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            mAdvertisingBinding.rvMainBookList.setAdapter(mAdvertisingAdapter);
            mAdvertisingBinding.rvMainBookList.setItemAnimator(new DefaultItemAnimator());
            mAdvertisingBinding.rvMainBookList.setLayoutManager(llm);

            mAdvertisingAdapter.setListener(new AdvertisingAdapter.OnAdvertisingClickListener() {
                @Override
                public void onItemClickListener(AdvertisingResponse advertisingResponse) {
                    openAdvertisingDetailsActivity(advertisingResponse.getToken());
                }
            });

            scrollListener = new EndlessRecyclerViewScrollListener(llm) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    firstVisiblePosition = llm.findFirstVisibleItemPosition();
                    findFirstCompletelyVisibleItemPosition = llm.findFirstCompletelyVisibleItemPosition();
                    findLastVisibleItemPosition = llm.findLastVisibleItemPosition();
                    findLastCompletelyVisibleItemPosition = llm.findLastCompletelyVisibleItemPosition();


                    callAdvertisingList(page + 1, params);
                }
            };
            mAdvertisingBinding.rvMainBookList.addOnScrollListener(scrollListener);

            mAdvertisingBinding.etAdvertisingSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        mAdvertisingResponseList = new ArrayList<>();
                        searchTxt = mAdvertisingBinding.etAdvertisingSearch.getText().toString();
                        params.put(AppConstants.REQ_KEY_TITLE_SEARCH,searchTxt);
                        callAdvertisingList(1, params);
                        return true;
                    }
                    return false;
                }
            });

//        scrollListenerSearch = new EndlessRecyclerViewScrollListener(llm) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                firstVisiblePosition = llm.findFirstVisibleItemPosition();
//                findFirstCompletelyVisibleItemPosition = llm.findFirstCompletelyVisibleItemPosition();
//                findLastVisibleItemPosition = llm.findLastVisibleItemPosition();
//                findLastCompletelyVisibleItemPosition = llm.findLastCompletelyVisibleItemPosition();
//                onSearchAdvertising(page+1);
//            }
//        };
//        mAdvertisingBinding.rvMainBookList.addOnScrollListener(scrollListenerSearch);


            callAdvertisingList(1, params);


//        mAdvertisingBinding.rvMainBookList.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (loading) {
//                    if (dy > 0) {
//                        int visibleItemCount = llm.getChildCount();
//                        int totalItemCount = llm.getItemCount();
//                        int pastVisibleItems = llm.findFirstVisibleItemPosition();
//
////                    if(visibleItemCount > 0
////                            && pastVisibleItems + visibleItemCount == totalItemCount
////                            && !executed)
////                    {
////                        executed = true;
////                    }
//
//                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
//                            loading = false;
//                            onLoadMore();
//                        }
//                    }
//                }
//            }
//        });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void onLoadMore() {
        try{
            if(mAdvertisingListViewModel.hasNextPage)
            {
                pageIndex ++;
                loading = true;
               // callAdvertisingList();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void updateMenuItemTitles()
    {
        try
        {
            for (int i=0;i<mMainMenu.size();i++)
            {
                MenuItem menuItem = mMainMenu.getItem(i);
                switch (menuItem.getItemId()) {
                    case R.id.about_us:
                        mMainMenu.getItem(i).setTitle(R.string.about_us);
                        break;
                    case R.id.contact_us:
                        mMainMenu.getItem(i).setTitle(R.string.contact_us);
                        break;
                    case R.id.login:
                        mMainMenu.getItem(i).setTitle(R.string.login);
                        break;
                    case R.id.support:
                        mMainMenu.getItem(i).setTitle(R.string.support);
                        break;
                    case R.id.rules:
                        mMainMenu.getItem(i).setTitle(R.string.rule);
                        break;
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initDrawerNavigation() {
        try {
            setSupportActionBar(mAdvertisingBinding.toolbarAdvertising);
            mAdvertisingBinding.includeNavigation.tvNavigationHeaderAppVersion.setText(NumberFormatter.convertDigits(BuildConfig.VERSION_NAME));
            mAdvertisingBinding.drawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mAdvertisingBinding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    mAdvertisingBinding.drawerMenu.closeDrawers();
                    switch (item.getItemId()) {
                        case R.id.about_us:
                            openAboutUsActivity();
                            return true;
                        case R.id.contact_us:
                            openContactUsActivity();
                            return true;
                        case R.id.support:
                            openSupportActivity();
                            return true;
                        case R.id.login:
                            openLogingActivity();
                            return true;
                        case R.id.rules:
                            openRulesActivity();
                            return true;
                    }
                    return true;
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void toggleMainMenu() {
        try {
            if (mAdvertisingBinding.drawerMenu.isDrawerOpen(Gravity.START))
                mAdvertisingBinding.drawerMenu.closeDrawers();
            else {
                mAdvertisingBinding.drawerMenu.openDrawer(Gravity.START);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        try{
            if (mAdvertisingBinding.drawerMenu.isDrawerOpen(Gravity.RIGHT)) {
                mAdvertisingBinding.drawerMenu.closeDrawer(Gravity.RIGHT);
                return;
            }
            super.onBackPressed();
        }catch (Exception e)
        {

        }

    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }


    public void callAdvertisingList(int pageIndex,HashMap<String,String> params)
    {
        params.put(AppConstants.REQ_KEY_CITY_GLOBAL_SEARCH, mAdvertisingListViewModel.getDataManager().getCityId());
        params.put(AppConstants.REQ_KEY_PAGE_INDEX,String.valueOf(pageIndex));
        mAdvertisingListViewModel.callAdvertisingList(this,iApiCall,params);
    }

    public void updateAdvertising(List<AdvertisingResponse> advertisingResponses) {
       // mAdvertisingAdapter.addItems(advertisingResponses);

        if (advertisingResponses.size() > 0) {
            pageIndex++;
        }
        for (int i = 0; i < advertisingResponses.size(); i++) {
            AdvertisingResponse advertisingResponse = advertisingResponses.get(i);
            mAdvertisingResponseList.add(advertisingResponse);
        }
        mAdvertisingAdapter.addItems(mAdvertisingResponseList);
        mAdvertisingBinding.rvMainBookList.setAdapter(mAdvertisingAdapter);
        // Adds the scroll listener to RecyclerView
        mAdvertisingBinding.rvMainBookList.addOnScrollListener(scrollListener);
        llm.scrollToPositionWithOffset(firstVisiblePosition, pageIndex);
    }

    public void updateSearchAdvertising(List<AdvertisingResponse> advertisingResponses) {
        if (advertisingResponses.size() > 0) {
            pageIndex++;
        }
        for (int i = 0; i < advertisingResponses.size(); i++) {
            AdvertisingResponse advertisingResponse = advertisingResponses.get(i);
            mAdvertisingSearchResponseList.add(advertisingResponse);
        }
        mAdvertisingAdapter.addItems(mAdvertisingSearchResponseList);
        mAdvertisingBinding.rvMainBookList.setAdapter(mAdvertisingAdapter);
        // Adds the scroll listener to RecyclerView
       // mAdvertisingBinding.rvMainBookList.addOnScrollListener(scrollListenerSearch);
        llm.scrollToPositionWithOffset(firstVisiblePosition, pageIndex);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_advertising;
    }

    @Override
    public AdvertisingListViewModel getViewModel() {
        return mAdvertisingListViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

}
