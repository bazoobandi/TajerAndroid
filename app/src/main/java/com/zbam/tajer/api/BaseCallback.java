package com.zbam.tajer.api;

import android.app.ProgressDialog;
import android.text.TextUtils;

import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.utils.AppLogger;
import com.zbam.tajer.utils.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by z.bazoubandi on 7/22/2018.
 */

public class BaseCallback implements Callback {

    private ProgressDialog progressDialog;
    private BaseActivity baseActivity;
    private IAPIResponse mApiResponse;
    private int requestCode;
    private IApiCall iApiCall;
    private DataManager dataManager;
    private static final String TAG_AUTH_FAILED = "Authentication Failed" ;

    public BaseCallback (BaseActivity baseActivity , IApiCall iApiCall , boolean showProgress, DataManager dataManager
            , int requestCode , IAPIResponse mApiResponse){
        this.baseActivity = baseActivity ;
        this.mApiResponse = mApiResponse ;
        this.requestCode = requestCode ;
        this.iApiCall = iApiCall ;
        this.dataManager = dataManager ;

        if (showProgress) {
            progressDialog = new ProgressDialog(baseActivity);
            progressDialog.setMessage(baseActivity.getString(R.string.please_wait));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }


    @Override
    public void onResponse(Call call, Response response) {
        try {

            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();

            BaseResponse baseResponse;
            if (response.code() == 200 || response.code() == 201) {
                baseResponse = (BaseResponse) response.body();
                if (TextUtils.equals(baseResponse.getSettings().getSuccess().toString(), "2")) {
                    AppLogger.e(TAG_AUTH_FAILED, "server authentication failed");

                    //baseActivity.openSplashActivity();
                } else {
                    mApiResponse.onResponseSuccess(response, requestCode);
                }
            } else
                mApiResponse.onResponseFailed(response.errorBody().toString(), response.code(), requestCode);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Call call, Throwable t) {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
            AppLogger.d("Request Error : ", baseActivity.getString(R.string.unexpected_error_with_url) + "\n\n" + t.getMessage().toString());
            CommonUtils.showToast(baseActivity, baseActivity.getString(R.string.unexpected_error_with_url));
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
