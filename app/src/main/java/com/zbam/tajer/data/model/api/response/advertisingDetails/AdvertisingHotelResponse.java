package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingHotelResponse {

    @SerializedName("facility")
    @Expose
    private String hotelFacility ;

    @SerializedName("address")
    @Expose
    private String hotelAddress ;

    @SerializedName("fax")
    @Expose
    private String hotelFax ;

    @SerializedName("tel")
    @Expose
    private String hotelTel ;

    @SerializedName("rank")
    @Expose
    private String hotelRank ;

    @SerializedName("website")
    @Expose
    private String hotelWebsite  ;

    public String getHotelFacility() {
        return hotelFacility;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getHotelFax() {
        return hotelFax;
    }

    public String getHotelTel() {
        return hotelTel;
    }

    public String getHotelRank() {
        return hotelRank;
    }

    public String getHotelWebsite() {
        return hotelWebsite;
    }
}
