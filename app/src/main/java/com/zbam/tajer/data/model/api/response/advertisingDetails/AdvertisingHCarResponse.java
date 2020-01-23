package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingHCarResponse {

    @SerializedName("producYearList")
    @Expose
    private List<ParametersListResponse> hCarproducYearList;

    @SerializedName("typeList")
    @Expose
    private List<ParametersListResponse> hCartypeList;

    @SerializedName("brandList")
    @Expose
    private List<ParametersListResponse> hBrandList;

    @SerializedName("kmsDriven")
    @Expose
    private String hCarkmsDriven  ;

    public List<ParametersListResponse> gethCarproducYearList() {
        return hCarproducYearList;
    }

    public List<ParametersListResponse> gethCartypeList() {
        return hCartypeList;
    }

    public String gethCarkmsDriven() {
        return hCarkmsDriven;
    }

    public List<ParametersListResponse> gethBrandList() {
        return hBrandList;
    }
}
