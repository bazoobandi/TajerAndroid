package com.zbam.tajer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by z.bazoubandi on 7/21/2018.
 */

public final class NetworkUtils {

    private NetworkUtils()
    {

    }

    public static boolean isNetworkConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetWork = cm.getActiveNetworkInfo();
        return activeNetWork != null && activeNetWork.isConnectedOrConnecting();
    }
}
