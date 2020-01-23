package com.zbam.tajer.ui.main.advertisingcreate;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.zbam.tajer.data.model.api.AdvertisingRequest;
import com.zbam.tajer.data.pref.DataManager;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 9/3/2018.
 */

@Module
public class AdvertisingCreateActivityModule {

    @Provides
    AdvertisingCreateViewModel providesAdvertisingCreateViewModel(DataManager dataManager)
    {
        return new AdvertisingCreateViewModel(dataManager);
    }

    @Provides
    AdvertisingRequest provideAdvertisingRequest(){
        return new AdvertisingRequest();
    }

}
