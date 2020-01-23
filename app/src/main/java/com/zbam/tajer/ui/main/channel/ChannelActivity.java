package com.zbam.tajer.ui.main.channel;

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

import com.zbam.tajer.BR;
import com.zbam.tajer.BuildConfig;
import com.zbam.tajer.R;
import com.zbam.tajer.costum.adapter.ChannelAdapter;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.ChannelResponse;
import com.zbam.tajer.databinding.ActivityChannelBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.EndlessRecyclerViewScrollListener;
import com.zbam.tajer.utils.NumberFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 10/28/2018.
 */

public class ChannelActivity extends BaseActivity<ActivityChannelBinding,ChannelViewModel>
{


    ActivityChannelBinding mChannelBinding;

    @Inject
    ChannelViewModel mChannelViewModel;

    @Inject
    ChannelAdapter mChannelAdapter;

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

    public List<ChannelResponse> mChannelResponseList = new ArrayList<>();
    private List<ChannelResponse> mChannelSearchResponseList = new ArrayList<>();

    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChannelBinding = getViewDataBinding();
        mChannelViewModel.setActivity(this);
        if(getIntent().hasExtra(AppConstants.REQ_KEY_PARAMETR_SEARCH)){
            params = (HashMap<String, String>) getIntent().getSerializableExtra(AppConstants.REQ_KEY_PARAMETR_SEARCH);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        init();
    }
    



    public void init()
    {
        try {
            mChannelBinding.menuButtom.category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryOpenBy = "show";
                    openCategoryActivity(categoryOpenBy);
                }
            });

            mChannelBinding.menuButtom.btnMainAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAdvertisingCreateActivity();
                }
            });
            mChannelBinding.menuButtom.search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAdvertisingSearchActivity(params);
                }
            });
            mChannelBinding.menuButtom.setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAboutUsActivity();
                }
            });
            mChannelBinding.menuButtom.home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        params.clear();
                        mChannelResponseList = new ArrayList<>();
                        callChannelList(1, params);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            mChannelBinding.rvChannelList.setAdapter(mChannelAdapter);
            mChannelBinding.rvChannelList.setItemAnimator(new DefaultItemAnimator());
            mChannelBinding.rvChannelList.setLayoutManager(llm);

            mChannelAdapter.setListener(new ChannelAdapter.OnChannelClickListener() {
                @Override
                public void onItemClickListener(ChannelResponse channelResponse) {
                   openChannelDetailsActivity(channelResponse.getChannelToken());
                }
            });

            scrollListener = new EndlessRecyclerViewScrollListener(llm) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    firstVisiblePosition = llm.findFirstVisibleItemPosition();
                    findFirstCompletelyVisibleItemPosition = llm.findFirstCompletelyVisibleItemPosition();
                    findLastVisibleItemPosition = llm.findLastVisibleItemPosition();
                    findLastCompletelyVisibleItemPosition = llm.findLastCompletelyVisibleItemPosition();


                    callChannelList(page + 1, params);
                }
            };
            mChannelBinding.rvChannelList.addOnScrollListener(scrollListener);

            mChannelBinding.etChannelSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        mChannelResponseList = new ArrayList<>();
                        searchTxt = mChannelBinding.etChannelSearch.getText().toString();
                        params.put(AppConstants.REQ_KEY_TITLE_SEARCH,searchTxt);
                        callChannelList(1, params);
                        return true;
                    }
                    return false;
                }
            });


            callChannelList(1, params);
            
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void onLoadMore() {
        try{
            if(mChannelViewModel.hasNextPage)
            {
                pageIndex ++;
                loading = true;
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }


    public void callChannelList(int pageIndex,HashMap<String,String> params)
    {
        params.put(AppConstants.REQ_KEY_CITY_GLOBAL_SEARCH, mChannelViewModel.getDataManager().getCityId());
        params.put(AppConstants.REQ_KEY_PAGE_INDEX,String.valueOf(pageIndex));
        mChannelViewModel.callChannel(this,iApiCall,params);
    }

    public void updateChannel(List<ChannelResponse> channelResponses) {
        // mChannelAdapter.addItems(channelResponses);

        if (channelResponses.size() > 0) {
            pageIndex++;
        }
        for (int i = 0; i < channelResponses.size(); i++) {
            ChannelResponse channelResponse = channelResponses.get(i);
            mChannelResponseList.add(channelResponse);
        }
        mChannelAdapter.addItems(mChannelResponseList);
        mChannelBinding.rvChannelList.setAdapter(mChannelAdapter);
        // Adds the scroll listener to RecyclerView
        mChannelBinding.rvChannelList.addOnScrollListener(scrollListener);
        llm.scrollToPositionWithOffset(firstVisiblePosition, pageIndex);
    }

    public void updateSearchChannel(List<ChannelResponse> channelResponses) {
        if (channelResponses.size() > 0) {
            pageIndex++;
        }
        for (int i = 0; i < channelResponses.size(); i++) {
            ChannelResponse channelResponse = channelResponses.get(i);
            mChannelSearchResponseList.add(channelResponse);
        }
        mChannelAdapter.addItems(mChannelSearchResponseList);
        mChannelBinding.rvChannelList.setAdapter(mChannelAdapter);
        // Adds the scroll listener to RecyclerView
        // mChannelBinding.rvChannelList.addOnScrollListener(scrollListenerSearch);
        llm.scrollToPositionWithOffset(firstVisiblePosition, pageIndex);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_channel;
    }

    @Override
    public ChannelViewModel getViewModel() {
        return mChannelViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

}
