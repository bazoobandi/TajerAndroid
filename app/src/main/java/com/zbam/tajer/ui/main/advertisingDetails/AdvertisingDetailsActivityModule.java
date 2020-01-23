package com.zbam.tajer.ui.main.advertisingDetails;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.zbam.tajer.costum.adapter.AdvertisingDetailsImgeAdapter;
import com.zbam.tajer.costum.adapter.AdvertisingDetailsOthersAdapter;
import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingOthersResponse;
import com.zbam.tajer.data.pref.DataManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 8/2/2018.
 */

@Module
public class AdvertisingDetailsActivityModule {

    @Provides
    AdvertisingDetailsViewModel providesAdvertisingDetailsViewModel(DataManager dataManager)
    {
        return new AdvertisingDetailsViewModel(dataManager);
    }

    @Provides
    @Named("HORIZONTAL_LLM")
    LinearLayoutManager providesLinearLayoutManager(Context context)
    {
        LinearLayoutManager llm = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.canScrollHorizontally();
        return llm;
    }

    @Provides
    @Named("VERTICAL_LLM")
    LinearLayoutManager provideVerticalLinearLayoutManager(Context context)
    {
        LinearLayoutManager llm = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.canScrollVertically();
        return llm;
    }



    @Provides
    AdvertisingDetailsImgeAdapter providesAdvertisingDetailsImgeAdapter(Context mContext, List<AdvertisingImageResponse> items)
    {
        return new AdvertisingDetailsImgeAdapter(mContext,items);
    }

    @Provides
    AdvertisingDetailsOthersAdapter providesAdvertisingDetailsOthersAdapter(Context mContext, List<AdvertisingOthersResponse> items)
    {
        return new AdvertisingDetailsOthersAdapter(mContext,items);
    }

    @Provides
    List<AdvertisingImageResponse> provideAdvertisingImageEmptyList(){
        List<AdvertisingImageResponse> items = new ArrayList<>();
        return items ;
    }

    @Provides
    List<AdvertisingOthersResponse> provideAdvertisingOthersEmptyList(){
        List<AdvertisingOthersResponse> items = new ArrayList<>();
        return items ;
    }


}
