package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingBicycleResponse {

    @SerializedName("producYearList")
    @Expose
    private List<ParametersListResponse> bicycleProducYearList;

    @SerializedName("brandList")
    @Expose
    private List<ParametersListResponse> bicycleBrandList;

    @SerializedName("typeList")
    @Expose
    private List<ParametersListResponse> bicycleTypeList;

    @SerializedName("colorList")
    @Expose
    private List<ParametersListResponse> bicycleColorList;

    @SerializedName("bySexList")
    @Expose
    private List<ParametersListResponse> bicycleBySexList;

    @SerializedName("materialList")
    @Expose
    private List<ParametersListResponse> bicycleMaterialList;

    @SerializedName("stopTypeList")
    @Expose
    private List<ParametersListResponse> bicycleStopTypeList;

    @SerializedName("bySizeList")
    @Expose
    private List<ParametersListResponse> bicycleBySizeList;


    public List<ParametersListResponse> getBicycleProducYearList() {
        return bicycleProducYearList;
    }

    public List<ParametersListResponse> getBicycleBrandList() {
        return bicycleBrandList;
    }

    public List<ParametersListResponse> getBicycleTypeList() {
        return bicycleTypeList;
    }

    public List<ParametersListResponse> getBicycleColorList() {
        return bicycleColorList;
    }

    public List<ParametersListResponse> getBicycleBySexList() {
        return bicycleBySexList;
    }

    public List<ParametersListResponse> getBicycleMaterialList() {
        return bicycleMaterialList;
    }

    public List<ParametersListResponse> getBicycleStopTypeList() {
        return bicycleStopTypeList;
    }

    public List<ParametersListResponse> getBicycleBySizeList() {
        return bicycleBySizeList;
    }
}
