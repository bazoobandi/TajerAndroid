package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingApplianceResponse {

    @SerializedName("barndList")
    @Expose
    private List<ParametersListResponse> applianceBrandList ;

    @SerializedName("typeList")
    @Expose
    private List<ParametersListResponse> applianceTypeList ;

    @SerializedName("colorList")
    @Expose
    private List<ParametersListResponse> applianceColorList ;

    public List<ParametersListResponse> getApplianceBrandList() {
        return applianceBrandList;
    }

    public List<ParametersListResponse> getApplianceTypeList() {
        return applianceTypeList;
    }

    public List<ParametersListResponse> getApplianceColorList() {
        return applianceColorList;
    }
}
