package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 9/24/2018.
 */

public class AdvertisingFurnitureResponse {

    @SerializedName("conditionList")
    @Expose
    private List<ParametersListResponse> furnitureConditionList ;

    @SerializedName("ageList")
    @Expose
    private List<ParametersListResponse> furnitureAgeList ;

    public List<ParametersListResponse> getFurnitureConditionList() {
        return furnitureConditionList;
    }

    public List<ParametersListResponse> getFurnitureAgeList() {
        return furnitureAgeList;
    }
}
