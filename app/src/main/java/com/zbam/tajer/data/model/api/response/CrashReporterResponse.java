package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 9/30/2018.
 */

public class CrashReporterResponse {
    @SerializedName("ePersonType")
    @Expose
    private String personType;

    @SerializedName("vMessage")
    @Expose
    private String message;

    @SerializedName("vAppVersion")
    @Expose
    private String appVersion;

    @SerializedName("iPersonId")
    @Expose
    private String personId;

    @SerializedName("vAndroidVersion")
    @Expose
    private String androidVersion;

    @SerializedName("vDeviceCompany")
    @Expose
    private String deviceCompany;

    @SerializedName("vDeviceModel")
    @Expose
    private String deviceModel;

    @SerializedName("vScreenSize")
    @Expose
    private String screenSize;

    @SerializedName("vScreenRezolution")
    @Expose
    private String screenRezolution;

    @SerializedName("vRamTotal")
    @Expose
    private String ramTotal;

    @SerializedName("vRamFree")
    @Expose
    private String ramFree;


    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getDeviceCompany() {
        return deviceCompany;
    }

    public void setDeviceCompany(String deviceCompany) {
        this.deviceCompany = deviceCompany;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenRezolution() {
        return screenRezolution;
    }

    public void setScreenRezolution(String screenRezolution) {
        this.screenRezolution = screenRezolution;
    }

    public String getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(String ramTotal) {
        this.ramTotal = ramTotal;
    }

    public String getRamFree() {
        return ramFree;
    }

    public void setRamFree(String ramFree) {
        this.ramFree = ramFree;
    }
}
