package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 7/31/2018.
 */

public class LastVersionResponse {

    @SerializedName("versionNumber")
    @Expose
    private String versionNumber;

    @SerializedName("versionName")
    @Expose
    private String versionName;

    @SerializedName("versionUrl")
    @Expose
    private String versionUrl;

    public String getVersionNumber() {
        return versionNumber;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getVersionUrl() {
        return versionUrl;
    }
}
