package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingAccessoriesResponse {

    @SerializedName("typeList")
    @Expose
    private List<ParametersListResponse> accessoriesTypeList ;

    public List<ParametersListResponse> getAccessoriesTypeList() {
        return accessoriesTypeList;
    }
}
