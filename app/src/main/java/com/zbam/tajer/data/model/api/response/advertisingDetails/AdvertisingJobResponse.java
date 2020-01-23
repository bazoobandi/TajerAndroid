package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingJobResponse {

    @SerializedName("minSalary")
    @Expose
    private String jobMinSalary;

    @SerializedName("maxSalary")
    @Expose
    private String jobMaxSalary;

    @SerializedName("jobTypeName")
    @Expose
    private String jobTypeName ;

    @SerializedName("state")
    @Expose
    private String jobState ;

    @SerializedName("city")
    @Expose
    private String jobCity  ;

    public String getJobMinSalary() {
        return jobMinSalary;
    }

    public String getJobMaxSalary() {
        return jobMaxSalary;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public String getJobState() {
        return jobState;
    }

    public String getJobCity() {
        return jobCity;
    }
}
