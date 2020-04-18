package com.zbam.tajer.ui.splash;

import android.Manifest;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import com.google.android.gms.analytics.Tracker;
import com.jaredrummler.android.device.DeviceName;
import com.zbam.tajer.BR;
import com.zbam.tajer.BuildConfig;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivitySplashBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.AppLogger;
import com.zbam.tajer.utils.CommonUtils;
import com.zbam.tajer.utils.NetworkUtils;

import java.util.HashMap;

import javax.inject.Inject;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by z.bazoubandi on 7/22/2018.
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel>{

    @Inject
    SplashViewModel mSplashViewModel;


    ActivitySplashBinding mActivitySplashBinding;

    private String androidVersion = "";
    private String appVersion = "";
    private String deviceCopmany = "";
    private String deviceModel = "";

    Handler handler = new Handler();
    Runnable runApp = new Runnable() {
        @Override
        public void run() {
            startApp();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashBinding = getViewDataBinding();
        mSplashViewModel.setActivity(this);
        init();

    }

    public void init()
    {
        DeviceName.with(getApplicationContext()).request(new DeviceName.Callback() {

            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
                deviceCopmany = info.manufacturer;  // "Samsung"
                deviceModel = info.marketName;            // "Galaxy S8+"
                androidVersion += Build.VERSION.SDK_INT + "/";
                appVersion = "" + BuildConfig.VERSION_CODE;
            }
        });
    }



    public void startApp()
    {
        try{
            if(NetworkUtils.isNetworkConnected(this))
            {
                mSplashViewModel.setNetworkStatus(true);
                getLastVersion();
            }else {
                mSplashViewModel.setNetworkStatus(false);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void getNewAPK(final String apkURL)
    {
//        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) && hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)){
//            openBazaarIntent(apkURL);
//        } else {
//            requestPermissionsSafely(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE} , AppConstants.PERMISSION_CODE_STORAGE);
//        }
        openBazaarIntent(apkURL);
        //openUrlAppIntent(apkURL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try {
            switch (requestCode) {
                case AppConstants.PERMISSION_CODE_LOCATION:
                    for (int i = 0; i < permissions.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            CommonUtils.showAlert(this, getString(R.string.no_permission_title), getString(R.string.get_permission_failed) + "\n\n" + permissions[i], new CommonUtils.IL() {
                                @Override
                                public void onSuccess() {
                                    AppLogger.d("PERMISSION DENIED");
                                    finish();
                                }

                                @Override
                                public void onCancel() {
                                }
                            });
                        } else {
                            if (i == 0)
                                startApp();
                        }
                    }
                    break;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void getLastVersion()
    {
        HashMap<String,String> map = new HashMap<>();

        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        map.put(AppConstants.REQUEST_ANDROID_UUID, android_id);
        map.put(AppConstants.REQUEST_ANDROID_VERSION, androidVersion);
        map.put(AppConstants.REQUEST_APP_VERSION, appVersion);
        map.put(AppConstants.REQUEST_DEVICE_COMPANY, deviceCopmany);
        map.put(AppConstants.REQUEST_DEVICE_MODEL, deviceModel);

        mSplashViewModel.callGetLastVersion(this,iApiCall,map);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }



    @Override
    protected void onStart() {
        super.onStart();
        handler.removeCallbacks(runApp);
        handler.postDelayed(runApp , 5000);
    }



    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
}
