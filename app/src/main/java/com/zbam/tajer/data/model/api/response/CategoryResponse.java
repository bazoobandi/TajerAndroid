package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 8/25/2018.
 */

public class CategoryResponse {

    @SerializedName("id")
    @Expose
    private String categoryId;

    @SerializedName("name")
    @Expose
    private String categoryName;

    @SerializedName("isLeaf")
    @Expose
    private String isLeaf;

    @SerializedName("catType")
    @Expose
    private String categoryType;

    @SerializedName("orderId")
    @Expose
    private String categoryOrder;

    @SerializedName("parentId")
    @Expose
    private String parentCategoryId;

    @SerializedName("logoPath")
    @Expose
    private String categoryIcon;

    @SerializedName("subject")
    @Expose
    private String categorySubject;

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public String getCategoryOrder() {
        return categoryOrder;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public String getCategorySubject() {
        return categorySubject;
    }
}
