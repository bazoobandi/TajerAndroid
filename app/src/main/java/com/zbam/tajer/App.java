package com.zbam.tajer;

import android.app.Activity;
import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.zbam.tajer.crashreporter.CrashReporterActivity;
import com.zbam.tajer.crashreporter.CrashReporterListener;
import com.zbam.tajer.di.component.AppComponent;
import com.zbam.tajer.di.component.DaggerAppComponent;
import com.zbam.tajer.di.module.AppModule;
import com.zbam.tajer.di.module.NetworkModule;
import com.zbam.tajer.ui.splash.SplashActivity;
import com.zbam.tajer.utils.AppLogger;

import javax.inject.Inject;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by z.bazoubandi on 7/16/2018.
 */

public class App extends Application implements HasActivityInjector{

    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector ;

    @Override
    public void onCreate() {
        super.onCreate();

        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .enabled(true) //default: true
                .showErrorDetails(true) //default: true
                .showRestartButton(true) //default: true
                .logErrorOnRestart(true) //default: true
                .trackActivities(true) //default: false
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .errorDrawable(R.mipmap.ic_bug) //default: bug image
                .restartActivity(SplashActivity.class) //default: null (your app's launch activity)
                .errorActivity(CrashReporterActivity.class) //default: null (default error activity)
                .eventListener(new CrashReporterListener()) //default: null
                .apply();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        AppLogger.init();

        sAnalytics = GoogleAnalytics.getInstance(this);
    }

//    public AppComponent getNetComponent() {
//        return mApiComponent;
//    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }



    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }
}
