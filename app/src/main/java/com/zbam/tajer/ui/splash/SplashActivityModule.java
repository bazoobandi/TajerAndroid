package com.zbam.tajer.ui.splash;

import com.zbam.tajer.data.pref.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 7/23/2018.
 */

@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager)
    {
        return new SplashViewModel(dataManager);
    }
}
