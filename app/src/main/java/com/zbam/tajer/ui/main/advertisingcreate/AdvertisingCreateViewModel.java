package com.zbam.tajer.ui.main.advertisingcreate;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingDetailsResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingProductTypeResponse;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;
import com.zbam.tajer.data.model.api.response.UploadImageResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingAccessoriesResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingApplianceResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingBicycleResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingCarResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingEstateResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingFurnitureResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingHCarResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingMobileResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingMotorResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingTripResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by z.bazoubandi on 9/3/2018.
 */


public class AdvertisingCreateViewModel extends BaseViewModel<AdvertisingCreateActivity> {


    public AdvertisingCreateViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public ObservableField<Boolean> fieldShowBycategory= new ObservableField<>(true);
    public ObservableField<Boolean> productTypeShow= new ObservableField<>(false);
    //images
    public ObservableField<Boolean> imge1 = new ObservableField<>(false);
    public ObservableField<Boolean> imgeIcon1 = new ObservableField<>(true);
    public ObservableField<Bitmap> imgeUrl1 = new ObservableField<>();
    public ObservableField<String> imgeUrlStr1 = new ObservableField<>("");

    public ObservableField<Boolean> imge2 = new ObservableField<>(false);
    public ObservableField<Boolean> imgeIcon2 = new ObservableField<>(true);
    public ObservableField<Bitmap> imgeUrl2 = new ObservableField<>();
    public ObservableField<String> imgeUrlStr2 = new ObservableField<>("");

    public ObservableField<Boolean> imge3 = new ObservableField<>(false);
    public ObservableField<Boolean> imgeIcon3 = new ObservableField<>(true);
    public ObservableField<Bitmap> imgeUrl3 = new ObservableField<>();
    public ObservableField<String> imgeUrlStr3 = new ObservableField<>("");

    public ObservableField<Boolean> imge4 = new ObservableField<>(false);
    public ObservableField<Boolean> imgeIcon4 = new ObservableField<>(true);
    public ObservableField<Bitmap> imgeUrl4 = new ObservableField<>();
    public ObservableField<String> imgeUrlStr4 = new ObservableField<>("");


    public ObservableField<String> advertisingTitle = new ObservableField<>("");
    public ObservableField<String> advertisingTime = new ObservableField<>("");
    public ObservableField<String> categoryName = new ObservableField<>("");
    public ObservableField<String> categoryId = new ObservableField<>("");
    public ObservableField<String> cityName = new ObservableField<>("");
    public ObservableField<String> cityId = new ObservableField<>("");
    public ObservableField<String> actType = new ObservableField<>("");
    public ObservableField<String> actPrice = new ObservableField<>("");
    public ObservableField<Boolean> actPriceShow = new ObservableField<>(true);
    public ObservableField<String> actContent = new ObservableField<>("");
    public ObservableField<String> telAdvertising = new ObservableField<>("");
    public ObservableField<String> mobileAdvertising = new ObservableField<>("");
    public ObservableField<String> emailAdvertising = new ObservableField<>("");
    public ObservableField<String> priceType = new ObservableField<>("");
    public ObservableField<Boolean> advertisingShowPriceType = new ObservableField<>(true);
    public ObservableField<Boolean> advertisingShowPrice = new ObservableField<>(true);
    public ObservableField<Boolean> showDetails = new ObservableField<>(true);
    //CAR
    public ObservableField<Boolean> carDeatailsShow = new ObservableField<>(false);
    public ObservableField<String> producYear = new ObservableField<>("");
    public ObservableField<String> kmsDriven = new ObservableField<>("");
    public ObservableField<String> insuranceValidTill = new ObservableField<>("");
    public ObservableField<String> insuranceDiscount = new ObservableField<>("");
    public ObservableField<String> brandName = new ObservableField<>("");
    public ObservableField<String> color = new ObservableField<>("");
    public ObservableField<String> fualType = new ObservableField<>("");
    public ObservableField<String> bodyStatus = new ObservableField<>("");
    public ObservableField<String> insideColor = new ObservableField<>("");
    public ObservableField<String> transmission = new ObservableField<>("");
    public ObservableField<String> plaque = new ObservableField<>("");
    public ObservableField<String> carModelName = new ObservableField<>("");
    public ObservableField<String> carModelId = new ObservableField<>("");
    //ACCESSORIES
    public ObservableField<String> accessoriesTypeName = new ObservableField<>("");
    public ObservableField<Boolean> accessoriesShow = new ObservableField<>(false);
    //AGENCY
    public ObservableField<Boolean> agencyShow = new ObservableField<>(false);
    public ObservableField<String> agencyManager = new ObservableField<>("");
    public ObservableField<String> agencyAddress = new ObservableField<>("");
    public ObservableField<String> agencyFax = new ObservableField<>("");
    public ObservableField<String> agencyLicenseNumber = new ObservableField<>("");
    public ObservableField<String> agencyWebsite = new ObservableField<>("");
    //appliance
    public ObservableField<Boolean> applianceShow = new ObservableField<>(false);
    public ObservableField<String> applianceBrandName = new ObservableField<>("");
    public ObservableField<String> applianceColor = new ObservableField<>("");
    public ObservableField<String> applianceTypeName = new ObservableField<>("");
    //bicycle
    public ObservableField<Boolean> bicycleShow = new ObservableField<>(false);
    public ObservableField<String> bicycleProducYear = new ObservableField<>("");
    public ObservableField<String> bicycleBrandName = new ObservableField<>("");
    public ObservableField<String> bicycleColor = new ObservableField<>("");
    public ObservableField<String> bicycleBySexName = new ObservableField<>("");
    public ObservableField<String> bicycleBySizeName = new ObservableField<>("");
    public ObservableField<String> bicycleMaterialName = new ObservableField<>("");
    public ObservableField<String> bicycleStopTypeName = new ObservableField<>("");
    //estate
    public ObservableField<Boolean> estateShow = new ObservableField<>(false);
    public ObservableField<String> estateAlleyWidth = new ObservableField<>("");
    public ObservableField<String> estateFloor = new ObservableField<>("");
    public ObservableField<String> estateUnitInFloor = new ObservableField<>("");
    public ObservableField<String> estateArea = new ObservableField<>("");
    public ObservableField<Boolean> estateBalcony = new ObservableField<>(false);
    public ObservableField<Boolean> estatecCentralAntenna = new ObservableField<>(false);
    public ObservableField<Boolean> estateDoorRemote = new ObservableField<>(false);
    public ObservableField<Boolean> estateHomeDepot = new ObservableField<>(false);
    public ObservableField<Boolean> estateParking = new ObservableField<>(false);
    public ObservableField<Boolean> estatelLift = new ObservableField<>(false);
    public ObservableField<Boolean> estateTel = new ObservableField<>(false);
    public ObservableField<Boolean> estateVedioIPhone = new ObservableField<>(false);
    public ObservableField<String> estateBuildingFloor = new ObservableField<>("");
    public ObservableField<String> estateLandArea = new ObservableField<>("");
    public ObservableField<String> estateLandLength = new ObservableField<>("");
    public ObservableField<String> estateShopHeight = new ObservableField<>("");
    public ObservableField<String> estateCabinetName = new ObservableField<>("");
    public ObservableField<String> estateCoolingName = new ObservableField<>("");
    public ObservableField<String> estateFacingName = new ObservableField<>("");
    public ObservableField<String> estateRoomNumberName = new ObservableField<>("");
    public ObservableField<String> estateFloorCoveringName = new ObservableField<>("");
    public ObservableField<String> estateHeatingName = new ObservableField<>("");
    public ObservableField<String> estateUseTypeName = new ObservableField<>("");
    public ObservableField<String> estateViewName = new ObservableField<>("");
    public ObservableField<String> estateWcName = new ObservableField<>("");
    public ObservableField<String> estateAgeName = new ObservableField<>("");

    //trip
    public ObservableField<Boolean> tripShow = new ObservableField<>(false);
    public ObservableField<String> tripStartDate = new ObservableField<>("");
    public ObservableField<String> tripEndDate = new ObservableField<>("");
    public ObservableField<String> tripeDestination = new ObservableField<>("");
    public ObservableField<String> tripeStayTime = new ObservableField<>("");
    public ObservableField<String> tripeDocuments = new ObservableField<>("");
    public ObservableField<String> tripTypeName = new ObservableField<>("");
    public ObservableField<String> transportTypeName = new ObservableField<>("");
    public ObservableField<String> tripSubjectTypeName = new ObservableField<>("");
    public ObservableField<String> cityNameByTrip = new ObservableField<>("");
    public ObservableField<String> cityIdByTrip = new ObservableField<>("");


    //motor
    public ObservableField<Boolean> motorShow = new ObservableField<>(false);
    public ObservableField<String> motorProducYear = new ObservableField<>("");
    public ObservableField<String> motorKmsDriven = new ObservableField<>("");
    public ObservableField<String> motorBrandName = new ObservableField<>("");
    public ObservableField<String> motorModelName = new ObservableField<>("");
    public ObservableField<String> motorInsuranceValidTill = new ObservableField<>("");
    public ObservableField<String> motorInsuranceDiscount = new ObservableField<>("");

    //mobile
    public ObservableField<Boolean> mobileShow = new ObservableField<>(false);
    public ObservableField<Boolean> mobileBrandShow = new ObservableField<>(false);
    public ObservableField<Boolean> tabletBrandShow = new ObservableField<>(false);
    public ObservableField<String> mobileBrandName = new ObservableField<>("");
    public ObservableField<String> mobileBrandId = new ObservableField<>("");
    public ObservableField<String> mobileModel = new ObservableField<>("");
    public ObservableField<String> mobileOsName = new ObservableField<>("");
    public ObservableField<String> mobileSimsNumber = new ObservableField<>("");
    public ObservableField<String> mobileAccessories = new ObservableField<>("");

    //job
    public ObservableField<Boolean> jobShow = new ObservableField<>(false);
    public ObservableField<String> jobMinSalary = new ObservableField<>("");
    public ObservableField<String> jobMaxSalary = new ObservableField<>("");
    public ObservableField<String> jobTypeName = new ObservableField<>("");
    public ObservableField<String> cityNameByJob = new ObservableField<>("");
    public ObservableField<String> cityIdByJob = new ObservableField<>("");

    //hotel
    public ObservableField<Boolean> hotelShow = new ObservableField<>(false);
    public ObservableField<String> hotelFacility = new ObservableField<>("");
    public ObservableField<String> hotelAddress = new ObservableField<>("");
    public ObservableField<String> hotelFax = new ObservableField<>("");
    public ObservableField<String> hotelTel = new ObservableField<>("");
    public ObservableField<String> hotelRank = new ObservableField<>("");
    public ObservableField<String> hotelWebsite = new ObservableField<>("");

    //hCAR
    public ObservableField<Boolean> hCarShow = new ObservableField<>(false);
    public ObservableField<String> hcarProducYear = new ObservableField<>("");
    public ObservableField<String> hcarKmsDriven = new ObservableField<>("");
    public ObservableField<String> hcarBrandName = new ObservableField<>("");
    public ObservableField<String> hcarTypeName = new ObservableField<>("");

    //furniture
    public ObservableField<Boolean> furnitureShow = new ObservableField<>(false);




    private Context mContext;
    public String categoryOpenBy;
    public String cityOpenBy;
    public String parameterTypeOpenBy;
    public String parameterTypeActivity;
    Bitmap bitmap;
    ArrayList<String> advertisingImages = new ArrayList<>();

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }

    public void openCategoryList()
    {
        categoryOpenBy = "create";
        getActivity().openCategoryActivity(categoryOpenBy);
    }

    public void openCityList()
    {
        cityOpenBy = "create";
        getActivity().openCityActivity(cityOpenBy);
    }
    public void openCityListByJob()
    {
        cityOpenBy = "jobCreate";
        getActivity().openCityActivity(cityOpenBy);
    }

    public void openCityListByTrip()
    {
        cityOpenBy = "tripCreate";
        getActivity().openCityActivity(cityOpenBy);
    }

    public void openCarTypeList()
    {
        parameterTypeOpenBy = "create";
        parameterTypeActivity = "car-brand";
        getActivity().openParameterTypeActivity(parameterTypeOpenBy,parameterTypeActivity);
    }

    public void openMobileBrandList()
    {
        parameterTypeOpenBy = "create";
        parameterTypeActivity = "mobile-brand";
        getActivity().openParameterTypeActivity(parameterTypeOpenBy,parameterTypeActivity);
    }

    public void onClearMobileBrand()
    {
        categoryName.set("");
        categoryId.set("");
    }
    public void onClearCategory()
    {
        categoryName.set("");
        categoryId.set("");
    }
    public void onClearCity()
    {
        cityName.set("");
        cityId.set("");
    }
    public void onClearCityByJob()
    {
        cityNameByJob.set("");
        cityIdByJob.set("");
    }
    public void onClearCityByTrip()
    {
        cityNameByTrip.set("");
        cityIdByTrip.set("");
    }
    public void onClearCarType()
    {
        carModelName.set("");
        carModelId.set("");
    }

    public void onAddImage(int imageNumber)
    {
        if(imageNumber==1)
        {
            getActivity().openImageIntent(1);
        }
        if(imageNumber==2)
        {
            getActivity().openImageIntent(2);
        }
        if(imageNumber==3)
        {
            getActivity().openImageIntent(3);
        }
        if(imageNumber==4)
        {
            getActivity().openImageIntent(4);
        }

    }


    public void onClickSend()
    {
        if(getActivity().mActivityAdvertisingCreateBinding.swAdvertisingCreateAccept.isChecked())
        {
            if(getActivity().isValidInputs())
            {
                getActivity().callAdvertisingCreate();
            }
        }else
        {
            CommonUtils.showAlert(getActivity(),getActivity().getString(R.string.title_warning),getActivity().getString(R.string.not_accept),null);
        }
    }

    public void onDeleteImage1()
    {
        imgeIcon1.set(true);
        imge1.set(false);
        imgeUrlStr1.set("");
        getActivity().mActivityAdvertisingCreateBinding.imgAdvertisingNumber1.setImageBitmap(bitmap);
    }

    public void onDeleteImage2()
    {
        imgeIcon2.set(true);
        imge2.set(false);
        imgeUrlStr2.set("");
        getActivity().mActivityAdvertisingCreateBinding.imgAdvertisingNumber2.setImageBitmap(bitmap);
    }

    public void onDeleteImage3()
    {
        imgeIcon3.set(true);
        imge3.set(false);
        imgeUrlStr3.set("");
        getActivity().mActivityAdvertisingCreateBinding.imgAdvertisingNumber3.setImageBitmap(bitmap);
    }

    public void onDeleteImage4()
    {
        imgeIcon4.set(true);
        imge4.set(false);
        imgeUrlStr4.set("");
        getActivity().mActivityAdvertisingCreateBinding.imgAdvertisingNumber4.setImageBitmap(bitmap);
    }


    public void callAdvertisingCreate(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
    {
        BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_ADVERTISING_CREATE,this);
        iApiCall.callAdvertisingCreate(map).enqueue(baseCallback);
    }

    public void callUploadImage(BaseActivity activity, MultipartBody.Part fileToUpload, RequestBody filename, IApiCall iApiCall,String imageNumber)
    {
        BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_UPLOAD_IMAGE,this);
        iApiCall.uploadImage(fileToUpload,filename,imageNumber).enqueue(baseCallback);
    }
    public void callCarFetch(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
        {
            BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                    , AppConstants.API_CODE_CAR_FETCH,this);
            iApiCall.callCarFetch(map).enqueue(baseCallback);
        }
    public void callHCarFetch(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
        {
            BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                    , AppConstants.API_CODE_HCAR_FETCH,this);
            iApiCall.callHCarFetch(map).enqueue(baseCallback);
        }
    public void callBicycle(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
        {
            BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                    , AppConstants.API_CODE_BICYCLE_FETCH,this);
            iApiCall.callBicycleFetch(map).enqueue(baseCallback);
        }
    public void callMotor(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
        {
            BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                    , AppConstants.API_CODE_MOTOR_FETCH,this);
            iApiCall.callMotorFetch(map).enqueue(baseCallback);
        }
    public void callMobile(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
        {
            BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                    , AppConstants.API_CODE_MOBILE_FETCH,this);
            iApiCall.callMobileFetch(map).enqueue(baseCallback);
        }
    public void callAccessories(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
        {
            BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                    , AppConstants.API_CODE_ACCESSORIS_FETCH,this);
            iApiCall.callAccessoriesFetch(map).enqueue(baseCallback);
        }
    public void callProductType(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
            {
                BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                        , AppConstants.API_CODE_PRODUCT_TYPE_FETCH,this);
                iApiCall.callProductTypeFetch(map).enqueue(baseCallback);
            }
    public void callFurniture(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
            {
                BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                        , AppConstants.API_CODE_FURNITURE_FETCH,this);
                iApiCall.callFurnitureFetch(map).enqueue(baseCallback);
            }
    public void callAppliance(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
            {
                BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                        , AppConstants.API_CODE_APPLIANCE_FETCH,this);
                iApiCall.callApplianceFetch(map).enqueue(baseCallback);
            }
    public void callTripp(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
            {
                BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                        , AppConstants.API_CODE_TRIP_FETCH,this);
                iApiCall.callTrippFetch(map).enqueue(baseCallback);
            }
    public void callEstate(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
            {
                BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                        , AppConstants.API_CODE_ESTATE_FETCH,this);
                iApiCall.callEstateFetch(map).enqueue(baseCallback);
            }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {

        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_ADVERTISING_CREATE:
                        CommonUtils.showAlert(getActivity(), getActivity().getString(R.string.title_warning), response.body().getSettings().getMessage(), null);
                        HashMap<String,String> openParams = new HashMap<>();
                        getActivity().openAdvertisingActivity(openParams);
                        break;
                    case AppConstants.API_CODE_UPLOAD_IMAGE:
                        getActivity().progressDialog.dismiss();
                        UploadImageResponse uploadImageResponse = (UploadImageResponse) response.body().getData().get(0);
                        uploadImage(uploadImageResponse);
                        break;
                    case AppConstants.API_CODE_CAR_FETCH:
                        AdvertisingCarResponse carFetchResponse = new AdvertisingCarResponse();
                        List<ParametersListResponse> colorListResponses = new ArrayList<>();
                        List<ParametersListResponse> fualTypeListResponses = new ArrayList<>();
                        List<ParametersListResponse> bodyStatusListResponses = new ArrayList<>();
                        List<ParametersListResponse> transmissionListResponses = new ArrayList<>();
                        List<ParametersListResponse> plaqueListResponses = new ArrayList<>();
                        List<ParametersListResponse> producYearListResponses = new ArrayList<>();
                        List<ParametersListResponse> insideColorListResponses = new ArrayList<>();
                        carDeatailsShow.set(true);
                        carFetchResponse = (AdvertisingCarResponse) response.body().getData().get(0);
                        colorListResponses = carFetchResponse.getColorList();
                        fualTypeListResponses = carFetchResponse.getFualTypeList();
                        bodyStatusListResponses = carFetchResponse.getBodyStatusListResponses();
                        transmissionListResponses = carFetchResponse.getTransmissionListResponses();
                        plaqueListResponses = carFetchResponse.getPlaqueListResponses();
                        producYearListResponses = carFetchResponse.getProducYearListResponses();
                        insideColorListResponses = carFetchResponse.getInsideColorListResponses();
                        getActivity().fillColorCar(colorListResponses);
                        getActivity().fillFualTypeCar(fualTypeListResponses);
                        getActivity().fillBodyStatusCar(bodyStatusListResponses);
                        getActivity().fillTransmissionCar(transmissionListResponses);
                        getActivity().fillPlaqueList(plaqueListResponses);
                        getActivity().fillProducYear(producYearListResponses);
                        //getActivity().fillInsideColor(insideColorListResponses);
                        break;
                    case AppConstants.API_CODE_HCAR_FETCH:
                        AdvertisingHCarResponse hCarResponse = new AdvertisingHCarResponse();
                        List<ParametersListResponse> hCarproducYearList = new ArrayList<>();
                        List<ParametersListResponse> hBrandList = new ArrayList<>();
                        List<ParametersListResponse> hCartypeList = new ArrayList<>();
                        hCarShow.set(true);
                        hCarResponse = (AdvertisingHCarResponse) response.body().getData().get(0);
                        hCarproducYearList = hCarResponse.gethCarproducYearList();
                        hBrandList = hCarResponse.gethBrandList();
                        hCartypeList = hCarResponse.gethCartypeList();
                        getActivity().fillhCarproducYear(hCarproducYearList);
                        getActivity().fillhBrandList(hBrandList);
                        getActivity().fillhCartypeList(hCartypeList);
                        break;
                        case AppConstants.API_CODE_BICYCLE_FETCH:
                        AdvertisingBicycleResponse bicycleResponse = new AdvertisingBicycleResponse();
                        List<ParametersListResponse> bicycleProducYearList = new ArrayList<>();
                        List<ParametersListResponse> bicycleBrandList = new ArrayList<>();
                        List<ParametersListResponse> bicycleTypeList = new ArrayList<>();
                        List<ParametersListResponse> bicycleColorList = new ArrayList<>();
                        List<ParametersListResponse> bicycleBySexList = new ArrayList<>();
                        List<ParametersListResponse> bicycleMaterialList = new ArrayList<>();
                        List<ParametersListResponse> bicycleStopTypeList = new ArrayList<>();
                        List<ParametersListResponse> bicycleBySizeList = new ArrayList<>();
                        bicycleShow.set(true);
                        bicycleResponse = (AdvertisingBicycleResponse) response.body().getData().get(0);
                        bicycleProducYearList = bicycleResponse.getBicycleProducYearList();
                        bicycleBrandList = bicycleResponse.getBicycleBrandList();
                        bicycleTypeList = bicycleResponse.getBicycleTypeList();
                        bicycleColorList = bicycleResponse.getBicycleColorList();
                        bicycleBySexList = bicycleResponse.getBicycleBySexList();
                        bicycleMaterialList = bicycleResponse.getBicycleMaterialList();
                        bicycleStopTypeList = bicycleResponse.getBicycleStopTypeList();
                        bicycleBySizeList = bicycleResponse.getBicycleBySizeList();
                        //getActivity().fillBicycleProducYear(bicycleProducYearList);
                        getActivity().fillBicycleBrandList(bicycleBrandList);
                        getActivity().fillBicycleTypeList(bicycleTypeList);
                        getActivity().fillBicycleColorList(bicycleColorList);
                        getActivity().fillBicycleBySexList(bicycleBySexList);
                        getActivity().fillBicycleMaterialList(bicycleMaterialList);
                        getActivity().fillBicycleStopTypeList(bicycleStopTypeList);
                        getActivity().fillBicycleBySizeList(bicycleBySizeList);
                        break;
                    case AppConstants.API_CODE_MOTOR_FETCH:
                        AdvertisingMotorResponse motorResponse = new AdvertisingMotorResponse();
                        motorResponse = (AdvertisingMotorResponse) response.body().getData().get(0);
                        List<ParametersListResponse> motorProductYearList = new ArrayList<>();
                        List<ParametersListResponse> motorBrandList = new ArrayList<>();
                        motorProductYearList = motorResponse.getMotorProductYearList();
                        motorBrandList = motorResponse.getMotorBrandList();
                        motorShow.set(true);
                        getActivity().fillMotorProductYearList(motorProductYearList);
                        getActivity().fillMotorBrandList(motorBrandList);
                        break;
                    case AppConstants.API_CODE_ACCESSORIS_FETCH:
                        AdvertisingAccessoriesResponse accessoriesResponse = new AdvertisingAccessoriesResponse();
                        accessoriesResponse = (AdvertisingAccessoriesResponse) response.body().getData().get(0);
                        accessoriesShow.set(true);
                        List<ParametersListResponse> accessoriesTypeList = new ArrayList<>();
                        accessoriesTypeList = accessoriesResponse.getAccessoriesTypeList();
                        getActivity().fillAccessoriesTypeList(accessoriesTypeList);
                        break;
                    case AppConstants.API_CODE_PRODUCT_TYPE_FETCH:
                        AdvertisingProductTypeResponse productTypeResponse = new AdvertisingProductTypeResponse();
                        productTypeResponse = (AdvertisingProductTypeResponse) response.body().getData().get(0);
                        productTypeShow.set(true);
                        List<ParametersListResponse> productTypeList = new ArrayList<>();
                        productTypeList = productTypeResponse.getProductTypeList();
                        getActivity().fillProductTypeList(productTypeList);
                        break;
                    case AppConstants.API_CODE_MOBILE_FETCH:
                        AdvertisingMobileResponse mobileResponse = new AdvertisingMobileResponse();
                        mobileResponse = (AdvertisingMobileResponse) response.body().getData().get(0);
                        mobileShow.set(true);
                        List<ParametersListResponse> osNameList = new ArrayList<>();
                        List<ParametersListResponse> tabletBrandList = new ArrayList<>();
                        osNameList = mobileResponse.getOsNameList();
                        tabletBrandList = mobileResponse.getTabletBrandList();
                        getActivity().fillOsNameList(osNameList);
                        getActivity().fillTabletBarndList(tabletBrandList);
                        getActivity().fillSimsNumber();
                        break;
                    case AppConstants.API_CODE_FURNITURE_FETCH:
                        AdvertisingFurnitureResponse furnitureResponse = new AdvertisingFurnitureResponse();
                        furnitureResponse = (AdvertisingFurnitureResponse) response.body().getData().get(0);
                        furnitureShow.set(true);
                        List<ParametersListResponse> furnitureConditionList = new ArrayList<>();
                        List<ParametersListResponse> furnitureAgeList = new ArrayList<>();
                        furnitureConditionList = furnitureResponse.getFurnitureConditionList();
                        furnitureAgeList = furnitureResponse.getFurnitureAgeList();
                        getActivity().fillFurnitureConditionList(furnitureConditionList);
                        getActivity().fillFurnitureAgeList(furnitureAgeList);
                        break;
                    case AppConstants.API_CODE_APPLIANCE_FETCH:
                        AdvertisingApplianceResponse applianceResponse = new AdvertisingApplianceResponse();
                        applianceResponse = (AdvertisingApplianceResponse) response.body().getData().get(0);
                        applianceShow.set(true);
                        List<ParametersListResponse> applianceBrandList = new ArrayList<>();
                        List<ParametersListResponse> applianceTypeList = new ArrayList<>();
                        List<ParametersListResponse> applianceColorList = new ArrayList<>();
                        applianceBrandList = applianceResponse.getApplianceBrandList();
                        applianceTypeList = applianceResponse.getApplianceTypeList();
                        applianceColorList = applianceResponse.getApplianceColorList();
                        getActivity().fillApplianceBrandList(applianceBrandList);
                        getActivity().fillApplianceTypeList(applianceTypeList);
                        getActivity().fillApplianceColorList(applianceColorList);
                        break;
                    case AppConstants.API_CODE_TRIP_FETCH:
                        AdvertisingTripResponse tripResponse = new AdvertisingTripResponse();
                        tripResponse = (AdvertisingTripResponse) response.body().getData().get(0);
                        tripShow.set(true);
                        List<ParametersListResponse> tripTypeList = new ArrayList<>();
                        List<ParametersListResponse> tripTransportTypeList = new ArrayList<>();
                        List<ParametersListResponse> tripSubjectTypeList = new ArrayList<>();
                        tripTypeList = tripResponse.getTripTypeList();
                        tripTransportTypeList = tripResponse.getTripTransportTypeList();
                        tripSubjectTypeList = tripResponse.getTripSubjectTypeList();
                        getActivity().fillTripTypeList(tripTypeList);
                        getActivity().fillTripTransportTypeList(tripTransportTypeList);
                        getActivity().fillTripSubjectTypeList(tripSubjectTypeList);
                        break;
                    case AppConstants.API_CODE_ESTATE_FETCH:
                        AdvertisingEstateResponse estateResponse = new AdvertisingEstateResponse();
                        estateResponse = (AdvertisingEstateResponse) response.body().getData().get(0);
                        List<ParametersListResponse> roomNumberList = new ArrayList<>();
                        List<ParametersListResponse> facingList = new ArrayList<>();
                        List<ParametersListResponse> floorCoveringList = new ArrayList<>();
                        List<ParametersListResponse> cabinetList = new ArrayList<>();
                        List<ParametersListResponse> wcList = new ArrayList<>();
                        List<ParametersListResponse> heatingList = new ArrayList<>();
                        List<ParametersListResponse> coolingList = new ArrayList<>();
                        List<ParametersListResponse> viewList = new ArrayList<>();
                        List<ParametersListResponse> ageList = new ArrayList<>();
                        roomNumberList = estateResponse.getRoomNumberList();
                        facingList = estateResponse.getFacingList();
                        floorCoveringList = estateResponse.getFloorCoveringList();
                        cabinetList = estateResponse.getCabinetList();
                        wcList = estateResponse.getWcList();
                        heatingList = estateResponse.getHeatingList();
                        coolingList = estateResponse.getCoolingList();
                        viewList = estateResponse.getViewList();
                        ageList = estateResponse.getAgeList();
                        getActivity().fillEstateRoomNumberList(roomNumberList);
                        getActivity().fillEstatefacingList(facingList);
                        getActivity().fillEstateFloorCoveringList(floorCoveringList);
                        getActivity().fillEstateCabinetList(cabinetList);
                        getActivity().fillEstateWcList(wcList);
                        getActivity().fillEstatHeatingList(heatingList);
                        getActivity().fillEstatCoolingList(coolingList);
                        getActivity().fillEstatViewList(viewList);
                        getActivity().fillEstatAgeList(ageList);
                        break;
                }

            } else {
                CommonUtils.showAlert(getActivity(), getActivity().getString(R.string.title_warning), response.body().getSettings().getMessage(), null);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void uploadImage(UploadImageResponse uploadImageResponse)
    {
        try{
            if(uploadImageResponse.getImageNumber().equals("1"))
            {
                imgeIcon1.set(false);
                imge1.set(true);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                    }
                };
                new Thread(runnable).start();
                Glide.with(getActivity())
                        .load(uploadImageResponse.getImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.bg_placeholder)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                imgeUrl1.set(resource);
                            }
                        });
                imgeUrlStr1.set(uploadImageResponse.getImageUrl());
            }

            if(uploadImageResponse.getImageNumber().equals("2"))
            {
                imgeIcon2.set(false);
                imge2.set(true);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                    }
                };
                new Thread(runnable).start();
                Glide.with(getActivity())
                        .load(uploadImageResponse.getImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.bg_placeholder)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                imgeUrl2.set(resource);
                            }
                        });
                imgeUrlStr2.set(uploadImageResponse.getImageUrl());
            }

            if(uploadImageResponse.getImageNumber().equals("3"))
            {
                imgeIcon3.set(false);
                imge3.set(true);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                    }
                };
                new Thread(runnable).start();
                Glide.with(getActivity())
                        .load(uploadImageResponse.getImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.bg_placeholder)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                imgeUrl3.set(resource);
                            }
                        });
                imgeUrlStr3.set(uploadImageResponse.getImageUrl());
            }

            if(uploadImageResponse.getImageNumber().equals("4"))
            {
                imgeIcon4.set(false);
                imge4.set(true);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                    }
                };
                new Thread(runnable).start();
                Glide.with(getActivity())
                        .load(uploadImageResponse.getImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.drawable.bg_placeholder)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                imgeUrl4.set(resource);
                            }
                        });
                imgeUrlStr4.set(uploadImageResponse.getImageUrl());
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateData(AdvertisingDetailsResponse mDetailsResponse)
    {
        try{
            advertisingTitle.set(mDetailsResponse.getAdvertisingTitle());
            advertisingTime.set(mDetailsResponse.getPrettyTime());
            categoryName.set(mDetailsResponse.getSubSubCategoryName());
            cityName.set(mDetailsResponse.getCityName());
            actType.set(mDetailsResponse.getActType());
            actPrice.set(mDetailsResponse.getAdvertisingPrice());
            actContent.set(mDetailsResponse.getActContent());
            priceType.set(mDetailsResponse.getPriceType());
            telAdvertising.set(mDetailsResponse.getTelAdvertiser());
            mobileAdvertising.set(mDetailsResponse.getMobileAdvertiser());
            emailAdvertising.set(mDetailsResponse.getEmailAdvertiser());

            //showDetails
            if(mDetailsResponse.getAdvertisingOthersFields()!=null)
            {
                showDetails.set(true);
            }else
            {
                showDetails.set(false);
            }


//            //car
//            if(mDetailsResponse.getAdvertisingCar()!=null)
//            {
//                carDeatailsShow.set(true);
//                producYear.set(mDetailsResponse.getAdvertisingCar().get(0).getProducYear());
//                kmsDriven.set(mDetailsResponse.getAdvertisingCar().get(0).getKmsDriven());
//                insuranceValidTill.set(mDetailsResponse.getAdvertisingCar().get(0).getInsuranceValidTill());
//                insuranceDiscount.set(mDetailsResponse.getAdvertisingCar().get(0).getInsuranceDiscount());
//                color.set(mDetailsResponse.getAdvertisingCar().get(0).getColor());
//                fualType.set(mDetailsResponse.getAdvertisingCar().get(0).getFualType());
//                bodyStatus.set(mDetailsResponse.getAdvertisingCar().get(0).getBodyStatus());
//                insideColor.set(mDetailsResponse.getAdvertisingCar().get(0).getInsideColor());
//                transmission.set(mDetailsResponse.getAdvertisingCar().get(0).getTransmission());
//                plaque.set(mDetailsResponse.getAdvertisingCar().get(0).getPlaque());
//                subModelName.set(mDetailsResponse.getAdvertisingCar().get(0).getSubModelName());
//                brandName.set(mDetailsResponse.getAdvertisingCar().get(0).getBrandName() + " " + mDetailsResponse.getAdvertisingCar().get(0).getModelName());
//            }else
//            {
//                carDeatailsShow.set(false);
//            }
//
//            //accessories
//            if(mDetailsResponse.getAdvertisingAccessories()!=null)
//            {
//                accessoriesTypeName.set(mDetailsResponse.getAdvertisingAccessories().get(0).getAccessoriesTypeName());
//                accessoriesShow.set(true);
//            }else
//            {
//                accessoriesShow.set(false);
//            }

//            //agency
//            if(mDetailsResponse.getAdvertisingAgency()!=null)
//            {
//                agencyAddress.set(mDetailsResponse.getAdvertisingAgency().get(0).getAgencyAddress());
//                agencyFax.set(mDetailsResponse.getAdvertisingAgency().get(0).getAgencyFax());
//                agencyLicenseNumber.set(mDetailsResponse.getAdvertisingAgency().get(0).getAgencyLicenseNumber());
//                agencyManager.set(mDetailsResponse.getAdvertisingAgency().get(0).getAgencyManager());
//                agencyWebsite.set(mDetailsResponse.getAdvertisingAgency().get(0).getAgencyWebsite());
//                agencyShow.set(true);
//            }else
//            {
//                agencyShow.set(false);
//            }

//            //Estate
//            if(mDetailsResponse.getAdvertisingEstate()!=null)
//            {
//                estateAlleyWidth.set(mDetailsResponse.getAdvertisingEstate().get(0).getEstateAlleyWidth());
//                estateFloor.set(mDetailsResponse.getAdvertisingEstate().get(0).getEstateFloor());
//                estateUnitInFloor.set(mDetailsResponse.getAdvertisingEstate().get(0).getEstateUnitInFloor());
//                estateArea.set(mDetailsResponse.getAdvertisingEstate().get(0).getEstateArea());
//                estateBalcony.set(mDetailsResponse.getAdvertisingEstate().get(0).getEstateBalcony());
//                estatecCentralAntenna.set(mDetailsResponse.getAdvertisingEstate().get(0).getEstatecCentralAntenna());
//                estateDoorRemote.set(mDetailsResponse.getAdvertisingEstate().get(0).getEstateDoorRemote());
//                estateShow.set(true);
//            }else
//            {
//                estateShow.set(false);
//            }

//            //appliance
//            if(mDetailsResponse.getAdvertisingAppliance()!=null)
//            {
//                applianceBrandName.set(mDetailsResponse.getAdvertisingAppliance().get(0).getApplianceBrandName());
//                applianceColor.set(mDetailsResponse.getAdvertisingAppliance().get(0).getApplianceColor());
//                applianceTypeName.set(mDetailsResponse.getAdvertisingAppliance().get(0).getApplianceTypeName());
//                applianceShow.set(true);
//            }else
//            {
//                applianceShow.set(false);
//            }

//            //bicycle
//            if(mDetailsResponse.getAdvertisingBicycle()!=null)
//            {
//                bicycleProducYear.set(mDetailsResponse.getAdvertisingBicycle().get(0).getBicycleProducYear());
//                bicycleBrandName.set(mDetailsResponse.getAdvertisingBicycle().get(0).getBicycleBrandName());
//                bicycleColor.set(mDetailsResponse.getAdvertisingBicycle().get(0).getBicycleColor());
//                bicycleBySexName.set(mDetailsResponse.getAdvertisingBicycle().get(0).getBicycleBySexName());
//                bicycleBySizeName.set(mDetailsResponse.getAdvertisingBicycle().get(0).getBicycleBySizeName());
//                bicycleMaterialName.set(mDetailsResponse.getAdvertisingBicycle().get(0).getBicycleMaterialName());
//                bicycleStopTypeName.set(mDetailsResponse.getAdvertisingBicycle().get(0).getBicycleTypeName());
//                bicycleShow.set(true);
//            }else
//            {
//                bicycleShow.set(false);
//            }

//            if(mDetailsResponse.getAdvertisingPrice()!=null && Integer.parseInt(mDetailsResponse.getAdvertisingPrice())>0)
//            {
//                advertisingShowPrice.set(true);
//                advertisingShowPriceType.set(false);
//            }else
//            {
//                advertisingShowPrice.set(false);
//                advertisingShowPriceType.set(true);
//            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {

    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {

    }

}
