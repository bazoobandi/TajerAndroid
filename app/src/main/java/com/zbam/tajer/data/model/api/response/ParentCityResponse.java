package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 9/4/2018.
 */

public class ParentCityResponse {

    @SerializedName("id")
    @Expose
    private String cityParentId;

    @SerializedName("name")
    @Expose
    private String cityParentName;

    public String getCityParentId() {
        return cityParentId;
    }

    public String getCityParentName() {
        return cityParentName;
    }
}
