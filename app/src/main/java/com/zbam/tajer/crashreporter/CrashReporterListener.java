package com.zbam.tajer.crashreporter;

import android.util.Log;

import com.zbam.tajer.utils.AppConstants;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by z.bazoubandi on 9/30/2018.
 */

public class CrashReporterListener implements CustomActivityOnCrash.EventListener {
    @Override
    public void onLaunchErrorActivity() {
        Log.i(AppConstants.TAG, "onLaunchErrorActivity()");
    }

    @Override
    public void onRestartAppFromErrorActivity() {
        Log.i(AppConstants.TAG, "onRestartAppFromErrorActivity()");
    }

    @Override
    public void onCloseAppFromErrorActivity() {
        Log.i(AppConstants.TAG, "onCloseAppFromErrorActivity()");
    }
}