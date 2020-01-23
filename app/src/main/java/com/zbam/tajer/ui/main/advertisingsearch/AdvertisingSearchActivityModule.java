package com.zbam.tajer.ui.main.advertisingsearch;

import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.register.login.LoginViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 9/27/2018.
 */

@Module
public class AdvertisingSearchActivityModule {

    @Provides
    AdvertisingSearchViewModel provideAdvertisingSearchViewModel(DataManager dataManager)
    {
        return new AdvertisingSearchViewModel(dataManager);
    }
}
