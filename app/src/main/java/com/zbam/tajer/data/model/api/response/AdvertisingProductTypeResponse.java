package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by z.bazoubandi on 9/20/2018.
 */

public class AdvertisingProductTypeResponse {

    @SerializedName("productTypeList")
    @Expose
    private List<ParametersListResponse> productTypeList ;

    public List<ParametersListResponse> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ParametersListResponse> productTypeList) {
        this.productTypeList = productTypeList;
    }
}
