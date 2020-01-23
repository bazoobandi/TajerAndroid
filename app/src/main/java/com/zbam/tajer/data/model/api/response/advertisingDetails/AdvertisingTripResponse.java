package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingTripResponse {

    @SerializedName("startDate")
    @Expose
    private String tripStartDate ;

    @SerializedName("endDate")
    @Expose
    private String tripEndDate ;

    @SerializedName("destination")
    @Expose
    private String tripeDestination ;

    @SerializedName("stayTime")
    @Expose
    private String tripeStayTime ;

    @SerializedName("requiredDocuments")
    @Expose
    private String tripeDocuments ;

    @SerializedName("tripTypeList")
    @Expose
    private List<ParametersListResponse> tripTypeList ;

    @SerializedName("transportTypeList")
    @Expose
    private List<ParametersListResponse> tripTransportTypeList ;

    @SerializedName("subjectTypeList")
    @Expose
    private List<ParametersListResponse> tripSubjectTypeList ;

    @SerializedName("city")
    @Expose
    private String tripCity ;

    @SerializedName("state")
    @Expose
    private String tripState ;

    public String getTripStartDate() {
        return tripStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public String getTripeDestination() {
        return tripeDestination;
    }

    public String getTripeStayTime() {
        return tripeStayTime;
    }

    public String getTripeDocuments() {
        return tripeDocuments;
    }

    public List<ParametersListResponse> getTripTypeList() {
        return tripTypeList;
    }

    public List<ParametersListResponse> getTripTransportTypeList() {
        return tripTransportTypeList;
    }

    public List<ParametersListResponse> getTripSubjectTypeList() {
        return tripSubjectTypeList;
    }

    public String getTripCity() {
        return tripCity;
    }

    public String getTripState() {
        return tripState;
    }
}
