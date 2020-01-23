package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 9/10/2018.
 */

public class ParentCarTypeResponse {

    @SerializedName("id")
    @Expose
    private String carTypeParentId;

    @SerializedName("name")
    @Expose
    private String carTypeParentName;

    public String getCarTypeParentId() {
        return carTypeParentId;
    }

    public String getCarTypeParentName() {
        return carTypeParentName;
    }
}
