package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.utils.NumberFormatter;

/**
 * Created by z.bazoubandi on 9/12/2018.
 */

public class ParametersListResponse {

    @SerializedName("id")
    @Expose
    private String parameterId;

    @SerializedName("paramName")
    @Expose
    private String parameterName;

    public String getParameterId() {
        return parameterId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public ParametersListResponse(String _id, String _name )
    {
        parameterId = _id;
        parameterName = _name;
    }


    public String toString()
    {
        return(  NumberFormatter.convertDigitsToPersion(parameterName)  );
    }


}
