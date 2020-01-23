package com.zbam.tajer.ui.main.advertising;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.zbam.tajer.costum.adapter.AdvertisingAdapter;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
import com.zbam.tajer.data.pref.DataManager;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 7/21/2018.
 */

@Module
public class AdvertisingActivityModule {

    @Provides
    AdvertisingListViewModel providesMotorDetailsViewModel(DataManager dataManager)
    {
        return new AdvertisingListViewModel(dataManager);
    }

    @Provides
    AdvertisingAdapter providesAdvertisingAdapter(Context mContext, List<AdvertisingResponse> items)
    {
        return new AdvertisingAdapter(mContext,items);
    }

    @Provides
    List<AdvertisingResponse> provideAdvertisingEmptyList(){
        List<AdvertisingResponse> items = new ArrayList<>();
        return items ;
    }

    @Provides
    LinearLayoutManager providesLinearLayoutManager(Context context)
    {
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }

}
