package com.zbam.tajer.ui.main.city;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.zbam.tajer.costum.adapter.CityAdapter;
import com.zbam.tajer.data.model.api.response.CityResponse;
import com.zbam.tajer.data.pref.DataManager;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 9/4/2018.
 */

@Module
public class CityActivityModule {

    @Provides
    CityViewModel providerCityViewModel(DataManager dataManager)
    {
        return new CityViewModel(dataManager);
    }

    @Provides
    CityAdapter providesCityAdapter(Context mContext, List<CityResponse> items, List<CityResponse> itemsCopy)
    {
        return new CityAdapter(mContext,items,itemsCopy);
    }

    @Provides
    List<CityResponse> provideCityEmptyList(){
        List<CityResponse> items = new ArrayList<>();
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
