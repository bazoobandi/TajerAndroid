package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 9/10/2018.
 */

public class ParameterTypeResponse {

    @SerializedName("id")
    @Expose
    private String parameterTypeId;

    @SerializedName("paramValue")
    @Expose
    private String paramTypeName;

    @SerializedName("parentId")
    @Expose
    private String parentParameterTypeId;

    @SerializedName("isLeaf")
    @Expose
    private String isLeaf;

    public String getIsLeaf() {
        return isLeaf;
    }

    public String getParameterTypeId() {
        return parameterTypeId;
    }

    public String getParamTypeName() {
        return paramTypeName;
    }

    public String getParentParameterTypeId() {
        return parentParameterTypeId;
    }

}
