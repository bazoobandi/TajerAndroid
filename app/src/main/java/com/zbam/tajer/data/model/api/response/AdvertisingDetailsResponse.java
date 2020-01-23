package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingOthersResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/2/2018.
 */

public class AdvertisingDetailsResponse {

    @SerializedName("title")
    @Expose
    private String advertisingTitle;

    @SerializedName("price")
    @Expose
    private String advertisingPrice;

    @SerializedName("usedStatus")
    @Expose
    private String usedStatus;

    @SerializedName("prettyTime")
    @Expose
    private String prettyTime;

    @SerializedName("salesType")
    @Expose
    private String salesType;

    @SerializedName("userType")
    @Expose
    private String userType;

    @SerializedName("mobile")
    @Expose
    private String mobileAdvertiser;

    @SerializedName("tel")
    @Expose
    private String telAdvertiser;

    @SerializedName("email")
    @Expose
    private String emailAdvertiser;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    @SerializedName("subCategoryName")
    @Expose
    private String subCategoryName;

    @SerializedName("subSubCategoryName")
    @Expose
    private String subSubCategoryName;

    @SerializedName("adsImags")
    @Expose
    private List<String> adsImags;

    @SerializedName("cityName")
    @Expose
    private String cityName;

    @SerializedName("actType")
    @Expose
    private String actType;

    @SerializedName("content")
    @Expose
    private String actContent;

    @SerializedName("priceType")
    @Expose
    private String priceType;

    @SerializedName("channelName")
    @Expose
    private String channelName;

    @SerializedName("channelLogo")
    @Expose
    private String channelLogo;

    @SerializedName("channelToken")
    @Expose
    private String channelToken;//توکن

    @SerializedName("channelWebsite")
    @Expose
    private String channelWebsite;// وب سایت

    @SerializedName("channelAddress")
    @Expose
    private String channelAddress;


    @SerializedName("advertisingFieldsHelper")
    @Expose
    private List<AdvertisingOthersResponse> advertisingOthersFields;


    public String getChannelName() {
        return channelName;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public String getChannelToken() {
        return channelToken;
    }

    public String getChannelWebsite() {
        return channelWebsite;
    }

    public String getChannelAddress() {
        return channelAddress;
    }

    public String getAdvertisingTitle() {
        return advertisingTitle;
    }

    public String getAdvertisingPrice() {
        return advertisingPrice;
    }

    public String getUsedStatus() {
        return usedStatus;
    }

    public String getPrettyTime() {
        return prettyTime;
    }

    public String getSalesType() {
        return salesType;
    }

    public String getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public String getSubSubCategoryName() {
        return subSubCategoryName;
    }

    public List<String> getAdsImags() {
        return adsImags;
    }

    public String getCityName() {
        return cityName;
    }

    public String getActType() {
        return actType;
    }

    public String getActContent() {
        return actContent;
    }

    public String getPriceType() {
        return priceType;
    }

    public String getEmailAdvertiser() {
        return emailAdvertiser;
    }

    public String getMobileAdvertiser() {
        return mobileAdvertiser;
    }

    public String getTelAdvertiser() {
        return telAdvertiser;
    }

    public List<AdvertisingOthersResponse> getAdvertisingOthersFields() {
        return advertisingOthersFields;
    }
}
