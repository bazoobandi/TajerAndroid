package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/15/2018.
 */

public class AdvertisingOthersResponse {

    @SerializedName("fieldTitle")
    @Expose
    private String fieldsTitle;

    @SerializedName("fieldValue")
    @Expose
    private String fieldsValue;

    public String getFieldsTitle() {
        return fieldsTitle;
    }

    public String getFieldsValue() {
        return fieldsValue;
    }
}
