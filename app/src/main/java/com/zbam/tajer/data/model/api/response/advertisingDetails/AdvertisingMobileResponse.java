package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingMobileResponse {

    @SerializedName("osList")
    @Expose
    private List<ParametersListResponse> osNameList ;

    @SerializedName("brandList")
    @Expose
    private List<ParametersListResponse> tabletBrandList ;

    @SerializedName("simsNumber")
    @Expose
    private String mobileSimsNumber    ;

    @SerializedName("accessories")
    @Expose
    private String mobileAccessories    ;


    public List<ParametersListResponse> getOsNameList() {
        return osNameList;
    }

    public String getMobileSimsNumber() {
        return mobileSimsNumber;
    }

    public String getMobileAccessories() {
        return mobileAccessories;
    }

    public List<ParametersListResponse> getTabletBrandList() {
        return tabletBrandList;
    }
}
