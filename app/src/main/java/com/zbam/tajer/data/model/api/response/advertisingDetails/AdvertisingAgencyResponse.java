package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 8/15/2018.
 */

public class AdvertisingAgencyResponse {

    @SerializedName("manager")
    @Expose
    private String agencyManager ;

    @SerializedName("address")
    @Expose
    private String agencyAddress ;

    @SerializedName("fax")
    @Expose
    private String agencyFax ;

    @SerializedName("licenseNumber")
    @Expose
    private String agencyLicenseNumber ;

    @SerializedName("website ")
    @Expose
    private String agencyWebsite ;

    public String getAgencyManager() {
        return agencyManager;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public String getAgencyFax() {
        return agencyFax;
    }

    public String getAgencyLicenseNumber() {
        return agencyLicenseNumber;
    }

    public String getAgencyWebsite() {
        return agencyWebsite;
    }
}
