package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 9/4/2018.
 */

public class CityResponse {

    @SerializedName("id")
    @Expose
    private String cityId;

    @SerializedName("name")
    @Expose
    private String cityName;

    @SerializedName("haveLocality")
    @Expose
    private String haveLocality;

    @SerializedName("parentId")
    @Expose
    private String parentCityId;

    @SerializedName("locationType")
    @Expose
    private String cityLocationType;

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getHaveLocality() {
        return haveLocality;
    }

    public String getParentCityId() {
        return parentCityId;
    }

    public String getCityLocationType() {
        return cityLocationType;
    }
}
