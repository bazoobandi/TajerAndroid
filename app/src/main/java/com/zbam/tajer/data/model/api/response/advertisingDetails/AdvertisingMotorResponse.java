package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingMotorResponse {

    @SerializedName("producYearList")
    @Expose
    private List<ParametersListResponse> motorProductYearList ;

    @SerializedName("brandList")
    @Expose
    private List<ParametersListResponse> motorBrandList ;

    @SerializedName("kmsDriven")
    @Expose
    private String motorKmsDriven ;

    @SerializedName("insuranceValidTill")
    @Expose
    private String motorInsuranceValidTill;

    @SerializedName("insuranceDiscount")
    @Expose
    private String motorInsuranceDiscount;

    public List<ParametersListResponse> getMotorProductYearList() {
        return motorProductYearList;
    }

    public List<ParametersListResponse> getMotorBrandList() {
        return motorBrandList;
    }

    public String getMotorKmsDriven() {
        return motorKmsDriven;
    }

    public String getMotorInsuranceValidTill() {
        return motorInsuranceValidTill;
    }

    public String getMotorInsuranceDiscount() {
        return motorInsuranceDiscount;
    }
}
