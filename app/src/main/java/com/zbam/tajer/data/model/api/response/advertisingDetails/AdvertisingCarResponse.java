package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/12/2018.
 */

public class AdvertisingCarResponse {

    @SerializedName("colorList")
    @Expose
    private List<ParametersListResponse> colorList;

    @SerializedName("fualTypeList")
    @Expose
    private List<ParametersListResponse> fualTypeList;

    @SerializedName("bodyStatusList")
    @Expose
    private List<ParametersListResponse> bodyStatusListResponses;

    @SerializedName("transmissionList")
    @Expose
    private List<ParametersListResponse> transmissionListResponses;

    @SerializedName("plaqueList")
    @Expose
    private List<ParametersListResponse> plaqueListResponses;

    @SerializedName("producYearList")
    @Expose
    private List<ParametersListResponse> producYearListResponses;

    @SerializedName("insideColorList")
    @Expose
    private List<ParametersListResponse> insideColorListResponses;

    @SerializedName("producYearFa")
    @Expose
    private String producYearFa;

    @SerializedName("kmsDriven")
    @Expose
    private String kmsDriven;

    @SerializedName("insuranceValidTill")
    @Expose
    private String insuranceValidTill;

    @SerializedName("insuranceDiscount")
    @Expose
    private String insuranceDiscount;

    @SerializedName("brandName")
    @Expose
    private String brandName;

    @SerializedName("modelName")
    @Expose
    private String modelName;

    @SerializedName("subModelName")
    @Expose
    private String subModelName;



    public String getProducYearFa() {
        return producYearFa;
    }

    public String getKmsDriven() {
        return kmsDriven;
    }

    public String getInsuranceValidTill() {
        return insuranceValidTill;
    }

    public String getInsuranceDiscount() {
        return insuranceDiscount;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getSubModelName() {
        return subModelName;
    }

    public List<ParametersListResponse> getColorList() {
        return colorList;
    }

    public List<ParametersListResponse> getFualTypeList() {
        return fualTypeList;
    }

    public List<ParametersListResponse> getBodyStatusListResponses() {
        return bodyStatusListResponses;
    }

    public List<ParametersListResponse> getTransmissionListResponses() {
        return transmissionListResponses;
    }

    public List<ParametersListResponse> getPlaqueListResponses() {
        return plaqueListResponses;
    }

    public List<ParametersListResponse> getProducYearListResponses() {
        return producYearListResponses;
    }

    public List<ParametersListResponse> getInsideColorListResponses() {
        return insideColorListResponses;
    }
}
