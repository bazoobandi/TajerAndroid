package com.zbam.tajer.di.module;

import android.app.Application;
import android.content.Context;

import com.zbam.tajer.data.pref.AppDataManager;
import com.zbam.tajer.data.pref.AppPreferencesHelper;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.data.pref.PreferencesHelper;
import com.zbam.tajer.di.PreferenceInfo;
import com.zbam.tajer.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 7/16/2018.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {return AppConstants.PREF_NAME;}

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager){return appDataManager;}

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {return appPreferencesHelper;}
}
