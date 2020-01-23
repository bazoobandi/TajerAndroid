package com.zbam.tajer.crashreporter;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.jaredrummler.android.device.DeviceName;
import com.zbam.tajer.BR;
import com.zbam.tajer.BuildConfig;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivityCrashReporterBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.utils.AppConstants;

import java.util.HashMap;

import javax.inject.Inject;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by z.bazoubandi on 9/30/2018.
 */

public class CrashReporterActivity extends BaseActivity<ActivityCrashReporterBinding,CrashReporterViewModel> {

    @Inject
    CrashReporterViewModel mCrashReporterViewModel;

    ActivityCrashReporterBinding mActivityCrashReporterBinding;

    private String androidVersion = "";
    private String appVersion = "";
    private String deviceCopmany = "";
    private String deviceModel = "";
    private String screenSize = "";
    private String screenRezolution = "";
    private String ramTotal = "";
    private String ramFree = "";
    private String crashMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCrashReporterBinding = getViewDataBinding();
        mCrashReporterViewModel.setActivity(this);
        init();

    }



    public void init()
    {
        DeviceName.with(getApplicationContext()).request(new DeviceName.Callback() {

            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
                deviceCopmany = info.manufacturer;  // "Samsung"
                deviceModel = info.marketName;            // "Galaxy S8+"
                androidVersion += Build.VERSION.SDK_INT + "/";

                ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                activityManager.getMemoryInfo(memoryInfo);
                ramTotal = "" + memoryInfo.totalMem / 0x100000L;
                ramFree = "" +  memoryInfo.availMem / 0x100000L;
                //Percentage can be calculated for API 16+
                //double percentAvail = memoryInfo.availMem / (double)memoryInfo.totalMem * 100.0;

                appVersion = "" + BuildConfig.VERSION_CODE;

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                double x = Math.pow(displayMetrics.widthPixels/displayMetrics.xdpi,2);
                double y = Math.pow(displayMetrics.heightPixels/displayMetrics.ydpi,2);
                screenSize = "" + Math.sqrt(x+y);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;
                screenRezolution = height + " * " + width;


                crashMessage = "" + CustomActivityOnCrash.getStackTraceFromIntent(getIntent());
                callSendAppCrashInfo();
            }
        });
    }


    public void callSendAppCrashInfo()
    {
        try {
            HashMap<String, String> mParams = new HashMap<String, String>();
            mParams.put(AppConstants.REQUEST_ANDROID_VERSION, androidVersion);
            mParams.put(AppConstants.REQUEST_APP_VERSION, appVersion);
            mParams.put(AppConstants.REQUEST_DEVICE_COMPANY, deviceCopmany);
            mParams.put(AppConstants.REQUEST_DEVICE_MODEL, deviceModel);
            mParams.put(AppConstants.REQUEST_SCREEN_SIZE, screenSize);
            mParams.put(AppConstants.REQUEST_SCREEN_REZOLUTION, screenRezolution);
            mParams.put(AppConstants.REQUEST_RAM_TOTAL, ramTotal);
            mParams.put(AppConstants.REQUEST_RAM_FREE, ramFree);
            mParams.put(AppConstants.REQUEST_CRASH_MESSAGE, crashMessage);
            mCrashReporterViewModel.callSendAppCrashInfo(this, iApiCall, mParams);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public CrashReporterViewModel getViewModel() {
        return mCrashReporterViewModel;
    }

    @Override
    public int getBindingVariable() {
        return (BR.viewModel);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crash_reporter;
    }
}

