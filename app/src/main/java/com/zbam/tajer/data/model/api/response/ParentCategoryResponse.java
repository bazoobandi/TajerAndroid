package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 8/26/2018.
 */

public class ParentCategoryResponse {

    @SerializedName("id")
    @Expose
    private String categoryParentId;

    @SerializedName("name")
    @Expose
    private String categoryParentName;

    public String getCategoryParentId() {
        return categoryParentId;
    }

    public String getCategoryParentName() {
        return categoryParentName;
    }
}
