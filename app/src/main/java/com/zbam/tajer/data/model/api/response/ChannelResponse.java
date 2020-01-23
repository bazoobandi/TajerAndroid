package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 10/28/2018.
 */

public class ChannelResponse {

    @SerializedName("channelName")
    @Expose
    private String channelName;

    @SerializedName("id")
    @Expose
    private int channelId;

    @SerializedName("channelDesc")
    @Expose
    private String channelDesc; // توضیحات کانال

    @SerializedName("channelLogo")
    @Expose
    private String channelLogo; // لوگوی کانال

    @SerializedName("channelCreateDateFa")
    @Expose
    private String channelCreateDateFa; // تاریخ ایجاد

    @SerializedName("channelMembers")
    @Expose
    private Integer channelMembers; // تعداد اعضا

    @SerializedName("channelUserName")
    @Expose
    private String channelUserName;// نام کاربری

    @SerializedName("channelTel")
    @Expose
    private String channelTel;// تلفن

    @SerializedName("channelMobile")
    @Expose
    private String channelMobile;//موبایل

    @SerializedName("channelEmail")
    @Expose
    private String channelEmail;//ایمیل

    @SerializedName("channelToken")
    @Expose
    private String channelToken;//توکن

    @SerializedName("channelWebsite")
    @Expose
    private String channelWebsite;// وب سایت

    @SerializedName("channelAddress")
    @Expose
    private String channelAddress;// آدرس

    @SerializedName("channelType")
    @Expose
    private String channelType; // INKE CHANNEL TABLIGHATI AST YA EKHTESASI

    @SerializedName("salesType")
    @Expose
    private String salesType;   // omde forosh ya jozee

    @SerializedName("cityName")
    @Expose
    private String cityName; //شهر

    @SerializedName("stateName")
    @Expose
    private String stateName; // استان

    @SerializedName("localityName")
    @Expose
    private String localityName; // محله

    @SerializedName("categoryName")
    @Expose
    private String categoryName; //گروه

    @SerializedName("subCategoryName")
    @Expose
    private String subCategoryName;//زیرگروه

    @SerializedName("subSubCategoryName")
    @Expose
    private String subSubCategoryName;//دسته

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo;
    }

    public String getChannelCreateDateFa() {
        return channelCreateDateFa;
    }

    public void setChannelCreateDateFa(String channelCreateDateFa) {
        this.channelCreateDateFa = channelCreateDateFa;
    }

    public Integer getChannelMembers() {
        return channelMembers;
    }

    public void setChannelMembers(Integer channelMembers) {
        this.channelMembers = channelMembers;
    }

    public String getChannelUserName() {
        return channelUserName;
    }

    public void setChannelUserName(String channelUserName) {
        this.channelUserName = channelUserName;
    }

    public String getChannelTel() {
        return channelTel;
    }

    public void setChannelTel(String channelTel) {
        this.channelTel = channelTel;
    }

    public String getChannelMobile() {
        return channelMobile;
    }

    public void setChannelMobile(String channelMobile) {
        this.channelMobile = channelMobile;
    }

    public String getChannelEmail() {
        return channelEmail;
    }

    public void setChannelEmail(String channelEmail) {
        this.channelEmail = channelEmail;
    }

    public String getChannelToken() {
        return channelToken;
    }

    public void setChannelToken(String channelToken) {
        this.channelToken = channelToken;
    }

    public String getChannelWebsite() {
        return channelWebsite;
    }

    public void setChannelWebsite(String channelWebsite) {
        this.channelWebsite = channelWebsite;
    }

    public String getChannelAddress() {
        return channelAddress;
    }

    public void setChannelAddress(String channelAddress) {
        this.channelAddress = channelAddress;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubSubCategoryName() {
        return subSubCategoryName;
    }

    public void setSubSubCategoryName(String subSubCategoryName) {
        this.subSubCategoryName = subSubCategoryName;
    }
}
