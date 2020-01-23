package com.zbam.tajer.data.model.api.response.advertisingDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/14/2018.
 */

public class AdvertisingEstateResponse {

    @SerializedName("roomNumberList")
    @Expose
    private List<ParametersListResponse> roomNumberList ;

    @SerializedName("facingList")
    @Expose
    private List<ParametersListResponse> facingList ;

    @SerializedName("floorCoveringList")
    @Expose
    private List<ParametersListResponse> floorCoveringList ;

    @SerializedName("cabinetList")
    @Expose
    private List<ParametersListResponse> cabinetList ;

    @SerializedName("wcList")
    @Expose
    private List<ParametersListResponse> wcList ;

    @SerializedName("heatingList")
    @Expose
    private List<ParametersListResponse> heatingList ;

    @SerializedName("coolingList")
    @Expose
    private List<ParametersListResponse> coolingList ;

    @SerializedName("viewList")
    @Expose
    private List<ParametersListResponse> viewList ;

    @SerializedName("ageList")
    @Expose
    private List<ParametersListResponse> ageList ;



    @SerializedName("alleyWidth")
    @Expose
    private String estateAlleyWidth ;

    @SerializedName("floor")
    @Expose
    private String estateFloor ;

    @SerializedName("unitInFloor")
    @Expose
    private String estateUnitInFloor ;

    @SerializedName("area")
    @Expose
    private String estateArea ;

    @SerializedName("balcony")
    @Expose
    private Boolean estateBalcony ;

    @SerializedName("centralAntenna")
    @Expose
    private Boolean estatecCentralAntenna ;

    @SerializedName("doorRemote")
    @Expose
    private Boolean estateDoorRemote ;

    @SerializedName("homeDepot")
    @Expose
    private Boolean estateHomeDepot ;

    @SerializedName("parking")
    @Expose
    private Boolean estateParking ;

    @SerializedName("lift")
    @Expose
    private Boolean estatelLift ;

    @SerializedName("tel")
    @Expose
    private Boolean estateTel ;

    @SerializedName("vedioIPhone")
    @Expose
    private Boolean estateVedioIPhone ;

    @SerializedName("buildingFloor")
    @Expose
    private String estateBuildingFloor ;

    @SerializedName("landArea")
    @Expose
    private String estateLandArea ;

    @SerializedName("landLength")
    @Expose
    private String estateLandLength ;

    @SerializedName("shopHeight")
    @Expose
    private String estateShopHeight ;

    @SerializedName("cabinetName")
    @Expose
    private String estateCabinetName ;

    @SerializedName("coolingName")
    @Expose
    private String estateCoolingName ;

    @SerializedName("facingName")
    @Expose
    private String estateFacingName ;

    @SerializedName("roomNumberName")
    @Expose
    private String estateRoomNumberName ;

    @SerializedName("floorCoveringName")
    @Expose
    private String estateFloorCoveringName ;

    @SerializedName("heatingName")
    @Expose
    private String estateHeatingName ;

    @SerializedName("useTypeName")
    @Expose
    private String estateUseTypeName ;

    @SerializedName("viewName")
    @Expose
    private String estateViewName ;

    @SerializedName("wcName")
    @Expose
    private String estateWcName ;

    @SerializedName("ageName")
    @Expose
    private String estateAgeName ;

    public String getEstateAlleyWidth() {
        return estateAlleyWidth;
    }

    public String getEstateFloor() {
        return estateFloor;
    }

    public String getEstateUnitInFloor() {
        return estateUnitInFloor;
    }

    public String getEstateArea() {
        return estateArea;
    }

    public Boolean getEstateBalcony() {
        return estateBalcony;
    }

    public Boolean getEstatecCentralAntenna() {
        return estatecCentralAntenna;
    }

    public Boolean getEstateDoorRemote() {
        return estateDoorRemote;
    }

    public Boolean getEstateHomeDepot() {
        return estateHomeDepot;
    }

    public Boolean getEstateParking() {
        return estateParking;
    }

    public Boolean getEstatelLift() {
        return estatelLift;
    }

    public Boolean getEstateTel() {
        return estateTel;
    }

    public Boolean getEstateVedioIPhone() {
        return estateVedioIPhone;
    }

    public String getEstateBuildingFloor() {
        return estateBuildingFloor;
    }

    public String getEstateLandArea() {
        return estateLandArea;
    }

    public String getEstateLandLength() {
        return estateLandLength;
    }

    public String getEstateShopHeight() {
        return estateShopHeight;
    }

    public String getEstateCabinetName() {
        return estateCabinetName;
    }

    public String getEstateCoolingName() {
        return estateCoolingName;
    }

    public String getEstateFacingName() {
        return estateFacingName;
    }

    public String getEstateRoomNumberName() {
        return estateRoomNumberName;
    }

    public String getEstateFloorCoveringName() {
        return estateFloorCoveringName;
    }

    public String getEstateHeatingName() {
        return estateHeatingName;
    }

    public String getEstateUseTypeName() {
        return estateUseTypeName;
    }

    public String getEstateViewName() {
        return estateViewName;
    }

    public String getEstateWcName() {
        return estateWcName;
    }

    public String getEstateAgeName() {
        return estateAgeName;
    }

    public List<ParametersListResponse> getRoomNumberList() {
        return roomNumberList;
    }

    public List<ParametersListResponse> getFacingList() {
        return facingList;
    }

    public List<ParametersListResponse> getFloorCoveringList() {
        return floorCoveringList;
    }

    public List<ParametersListResponse> getCabinetList() {
        return cabinetList;
    }

    public List<ParametersListResponse> getWcList() {
        return wcList;
    }

    public List<ParametersListResponse> getHeatingList() {
        return heatingList;
    }

    public List<ParametersListResponse> getCoolingList() {
        return coolingList;
    }

    public List<ParametersListResponse> getViewList() {
        return viewList;
    }

    public List<ParametersListResponse> getAgeList() {
        return ageList;
    }
}
