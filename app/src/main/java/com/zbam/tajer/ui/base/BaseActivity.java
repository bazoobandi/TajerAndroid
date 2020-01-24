package com.zbam.tajer.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.zbam.tajer.App;
import com.zbam.tajer.R;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.ChannelResponse;
import com.zbam.tajer.ui.main.advertising.AdvertisingActivity;
import com.zbam.tajer.ui.main.advertisingDetails.AdvertisingDetailsActivity;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateActivity;
import com.zbam.tajer.ui.main.advertisingsearch.AdvertisingSearchActivity;
import com.zbam.tajer.ui.main.channel.ChannelActivity;
import com.zbam.tajer.ui.main.channelDetails.ChannelDetailsActivity;
import com.zbam.tajer.ui.main.parametertype.ParameterTypeActivity;
import com.zbam.tajer.ui.main.category.CategoryActivity;
import com.zbam.tajer.ui.main.city.CityActivity;
import com.zbam.tajer.ui.option.aboutus.AboutUsActivity;
import com.zbam.tajer.ui.option.contactus.ContactUsActivity;
import com.zbam.tajer.ui.option.createchannel.CreateChannelActivity;
import com.zbam.tajer.ui.option.rules.RulesActivity;
import com.zbam.tajer.ui.option.support.SupportActivity;
import com.zbam.tajer.ui.option.usernamechannel.UserNameChannelActivity;
import com.zbam.tajer.ui.register.forgotpassword.ForgotPasswordActivity;
import com.zbam.tajer.ui.register.login.LoginActivity;
import com.zbam.tajer.ui.register.signup.SignUpActivity;
import com.zbam.tajer.ui.splash.SplashActivity;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.CommonUtils;
import com.zbam.tajer.utils.NetworkUtils;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by z.bazoubandi on 7/17/2018.
 */

public abstract class BaseActivity<T extends ViewDataBinding,V extends BaseViewModel>
        extends AppCompatActivity
        implements BaseFragment.Callback{



    @Inject
    protected IApiCall iApiCall ;

    private ProgressDialog mProgressDialog;

    private T mViewDataBinding;
    private V mViewModel;
    private Tracker mTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDependencyInjection();
        performDataBinding();
        setConfiguration();

        // Obtain the shared Tracker instance.
        App application = (App) getApplication();
        mTracker = application.getDefaultTracker();

        String name = this.getClass().getName();
        mTracker.setScreenName("" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    private void setConfiguration()
    {
        try
        {
            String languageToLoad = mViewModel.getDataManager().getDefaultLanguage();
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration
                    ,getBaseContext().getResources().getDisplayMetrics());
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void makePhoneCallIntent(String number){
        Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + number));
        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CommonUtils.showToast(BaseActivity.this , getString(R.string.msg_dial_intent_not_found));
                }
            });
        }
    }

//    @TargetApi(Build.VERSION_CODES.M)
//    public boolean hasPermission(String permission) {
//        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
//                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    @TargetApi(Build.VERSION_CODES.M)
//    public void requestPermissionsSafely(String[] permissions, int requestCode) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(permissions, requestCode);
//        }
//    }

    public void openBazaarIntent(String apkURL){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(apkURL));
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openUrlAppIntent(String apkURL){
        try {



            //get destination to update file and set Uri
            //TODO: First I wanted to store my update .apk file on internal storage for my app but apparently android does not allow you to open and install
            //aplication with existing package from there. So for me, alternative solution is Download directory in external storage. If there is better
            //solution, please inform us in comment
            String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
            String fileName = "tajer.apk";
            destination += fileName;
            final Uri uri = Uri.parse("file://" + destination);

            //Delete update file if exists
            File file = new File(destination);
            if (file.exists())
                //file.delete() - test this, I think sometimes it doesnt work
                file.delete();

            //get url of app on server
           // String url = Main.this.getString(R.string.update_app_url);

            //set downloadmanager
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkURL));
//            request.setDescription(Main.this.getString(R.string.notification_description));
//            request.setTitle(Main.this.getString(R.string.app_name));

            //set destination
            request.setDestinationUri(uri);

            // get download service and enqueue file
            final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            final long downloadId = manager.enqueue(request);

            //set BroadcastReceiver to install app when .apk is downloaded
            BroadcastReceiver onComplete = new BroadcastReceiver() {
                public void onReceive(Context ctxt, Intent intent) {
                    Intent install = new Intent(Intent.ACTION_VIEW);
                    install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    install.setDataAndType(uri,
                            manager.getMimeTypeForDownloadedFile(downloadId));
                    startActivity(install);

                    unregisterReceiver(this);
                    finish();
                }
            };
            //register receiver for when .apk download is compete
            registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openSplashActivity() {
        try {
            if (this instanceof SplashActivity)
                return;
            startActivity(new Intent(this, SplashActivity.class));
            this.finish();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openAdvertisingActivity(HashMap<String,String> openParams)
    {
        try {
            if (this instanceof AdvertisingActivity)
                return;
            Intent intent = new Intent(this, AdvertisingActivity.class);
            if(openParams.size() != 0 )
                intent.putExtra(AppConstants.REQ_KEY_PARAMETR_SEARCH,openParams);
            startActivity(intent);
            this.finish();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openChannelActivity(HashMap<String,String> openParams)
    {
        try {
            if (this instanceof ChannelActivity)
                return;
            Intent intent = new Intent(this, ChannelActivity.class);
            if(openParams.size() != 0 )
                intent.putExtra(AppConstants.REQ_KEY_PARAMETR_SEARCH,openParams);
            startActivity(intent);
            this.finish();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openAdvertisingSearchActivity(HashMap<String,String> searchParams) {
        try {
            if (this instanceof AdvertisingSearchActivity)
                return;
            Intent intent = new Intent(this, AdvertisingSearchActivity.class);
            if(searchParams.size() != 0 )
                intent.putExtra(AppConstants.REQ_KEY_PARAMETR_ITEMS_SEARCH,searchParams);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openCategoryActivity(String categoryOpenBy) {
        try {
            if (this instanceof CategoryActivity)
                return;
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(AppConstants.REQ_KEY_INTENT_CATEGORY_OPEN_BY,categoryOpenBy);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void openCityActivity(String cityOpenBy) {
        try {
            if (this instanceof CityActivity)
                return;
            Intent intent = new Intent(this, CityActivity.class);
            intent.putExtra(AppConstants.REQ_KEY_INTENT_City_OPEN_BY,cityOpenBy);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openUserNameChannelActivity(String channenToken) {
        try {
            if (this instanceof CityActivity)
                return;
            Intent intent = new Intent(this, UserNameChannelActivity.class);
            intent.putExtra(AppConstants.REQ_KEY_CHANNEL_TOKEN,channenToken);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openParameterTypeActivity(String parameterTypeOpenBy,String parameterTypeActivity) {
        try {
            if (this instanceof ParameterTypeActivity)
                return;
            Intent intent = new Intent(this, ParameterTypeActivity.class);
            intent.putExtra(AppConstants.REQ_KEY_INTENT_PARAMETER_TYPE_OPEN_BY,parameterTypeOpenBy);
            intent.putExtra(AppConstants.REQ_KEY_INTENT_PARAMETER_TYPE_ACTIVITY,parameterTypeActivity);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openLogingActivity() {
        try {
            if (this instanceof LoginActivity)
                return;
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openCreateChannelActivity() {
        try {
            if (this instanceof CreateChannelActivity)
                return;
            Intent intent = new Intent(this, CreateChannelActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openRulesActivity() {
        try {
            if (this instanceof RulesActivity)
                return;
            Intent intent = new Intent(this, RulesActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openSignupActivity() {
        try {
            if (this instanceof SignUpActivity)
                return;
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openForgotPasswordActivity() {
        try {
            if (this instanceof ForgotPasswordActivity)
                return;
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openSupportActivity() {
        try {
            if (this instanceof SupportActivity)
                return;
            Intent intent = new Intent(this, SupportActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openContactUsActivity() {
        try {
            if (this instanceof ContactUsActivity)
                return;
            Intent intent = new Intent(this, ContactUsActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openAboutUsActivity() {
        try {
            if (this instanceof AboutUsActivity)
                return;
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openAdvertisingDetailsActivity(String token)
    {
        try {
            if (this instanceof AdvertisingDetailsActivity)
                return;
            Intent intent = new Intent(this, AdvertisingDetailsActivity.class);
            intent.putExtra(AppConstants.REQ_KEY_TOKEN, token);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openChannelDetailsActivity(String token)
    {
        try {
            if (this instanceof ChannelDetailsActivity)
                return;
            Intent intent = new Intent(this, ChannelDetailsActivity.class);
            intent.putExtra(AppConstants.REQ_KEY_CHANNEL_TOKEN, token);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openAdvertisingCreateActivity()
    {
        try {
            if (this instanceof AdvertisingCreateActivity)
                return;
            Intent intent = new Intent(this, AdvertisingCreateActivity.class);
            startActivity(intent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void closeCategoryActivity()
    {
        try {
            this.finish();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void closeCityActivity()
    {
        try {
            this.finish();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void closeParameterTypeActivity()
    {
        try {
            this.finish();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void performDataBinding()
    {
        try {
            mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
            this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
            mViewDataBinding.executePendingBindings();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void hideKeyboard() {
        try {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        //Log.i(TAG, "Setting screen name: " + name);
        //mTracker.setScreenName("Image~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        super.onResume();
    }



    @Subscribe
    public abstract void onNotificationReceived(NotificationData notificationData);

    public void onNotificationReceived(final NotificationData notificationData , final Activity activity ){

    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    public void performDependencyInjection() {
        try {
            AndroidInjection.inject(this);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
