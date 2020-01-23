package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 7/22/2018.
 */

public class AdvertisingResponse {

    @SerializedName("title")
    @Expose
    private String advertisingTitle;

    @SerializedName("price")
    @Expose
    private String advertisingPrice;

    @SerializedName("urlThumb")
    @Expose
    private String advertisingUlr;

    @SerializedName("prettyTime")
    @Expose
    private String advertisingPrettyTime;

    @SerializedName("type")
    @Expose
    private String advertisingType;

    @SerializedName("priceType")
    @Expose
    private String advertisingPriceType;

    @SerializedName("cityName")
    @Expose
    private String advertisingCityName;

    @SerializedName("token")
    @Expose
    private String token;


    public String getAdvertisingTitle() {
        return advertisingTitle;
    }

    public String getAdvertisingPrice() {
        return advertisingPrice;
    }

    public String getAdvertisingUlr() {
        return advertisingUlr;
    }

    public String getAdvertisingPrettyTime() {
        return advertisingPrettyTime;
    }

    public String getAdvertisingType() {
        return advertisingType;
    }

    public String getAdvertisingPriceType() {
        return advertisingPriceType;
    }

    public String getAdvertisingCityName() {
        return advertisingCityName;
    }

    public String getToken() {
        return token;
    }
}
