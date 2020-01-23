package com.zbam.tajer.crashreporter;

import com.zbam.tajer.data.pref.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 9/30/2018.
 */

@Module
public class CrashReporterActivityModule {
    @Provides
    CrashReporterViewModel provideCrashReporterViewModel(DataManager dataManager)
    {
        return new CrashReporterViewModel(dataManager);
    }

}
