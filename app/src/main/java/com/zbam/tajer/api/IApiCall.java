package com.zbam.tajer.api;

import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingDetailsResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingProductTypeResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
import com.zbam.tajer.data.model.api.response.ChannelResponse;
import com.zbam.tajer.data.model.api.response.ChannelShowResponse;
import com.zbam.tajer.data.model.api.response.CrashReporterResponse;
import com.zbam.tajer.data.model.api.response.ParameterTypeResponse;
import com.zbam.tajer.data.model.api.response.CategoryResponse;
import com.zbam.tajer.data.model.api.response.CityResponse;
import com.zbam.tajer.data.model.api.response.LastVersionResponse;
import com.zbam.tajer.data.model.api.response.LoginResponse;
import com.zbam.tajer.data.model.api.response.ParentCarTypeResponse;
import com.zbam.tajer.data.model.api.response.ParentCategoryResponse;
import com.zbam.tajer.data.model.api.response.ParentCityResponse;
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

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by z.bazoubandi on 7/18/2018.
 */

public interface IApiCall {

    @POST("/bookCore/oauth/token?grant_type=password&username=bill&password=abc123")
    Call<BaseResponse<LoginResponse>> callLogin(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/adsList")
    Call<BaseResponse<AdvertisingResponse>> callAdvertisingList(@Body HashMap<String, String> map);

    @POST("/webservice/channel/channelList")
    Call<BaseResponse<ChannelResponse>> callChannel(@Body HashMap<String, String> map);

    @POST("/webservice/channel/createChannel")
    Call<BaseResponse<ChannelResponse>> callChannelCreate(@Body HashMap<String, String> map);

    @POST("/webservice/channel/channelShow")
    Call<BaseResponse<ChannelShowResponse>> callChannelDetails(@Body HashMap<String, String> map);

    @POST("/webservice/version")
    Call<BaseResponse<LastVersionResponse>> getLastVersion(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/adsShow")
    Call<BaseResponse<AdvertisingDetailsResponse>> callAdvertisingDetails(@Body HashMap<String, String> map);

    @POST("/webservice/category/list")
    Call<BaseResponse<CategoryResponse>> callCategoryList(@Body HashMap<String, String> map);

    @POST("/webservice/category/getParentCategory")
    Call<BaseResponse<ParentCategoryResponse>> callParentCategory(@Body HashMap<String, String> map);

    @POST("/webservice/locations/list")
    Call<BaseResponse<CityResponse>> callCityList(@Body HashMap<String, String> map);

    @POST("/webservice/locations/getParentLocations")
    Call<BaseResponse<ParentCityResponse>> callParentCity(@Body HashMap<String, String> map);

    @POST("/webservice/parameters/list")
    Call<BaseResponse<ParameterTypeResponse>> callParameterTypeList(@Body HashMap<String, String> map);

    @POST("/webservice/parameters/getParentParameter")
    Call<BaseResponse<ParentCarTypeResponse>> callParentParameterType(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/carFetch")
    Call<BaseResponse<AdvertisingCarResponse>> callCarFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/hcarFetch")
    Call<BaseResponse<AdvertisingHCarResponse>> callHCarFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/bicycleFetch")
    Call<BaseResponse<AdvertisingBicycleResponse>> callBicycleFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/motorFetch")
    Call<BaseResponse<AdvertisingMotorResponse>> callMotorFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/mobileFetch")
    Call<BaseResponse<AdvertisingMobileResponse>> callMobileFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/accessoriesFetch")
    Call<BaseResponse<AdvertisingAccessoriesResponse>> callAccessoriesFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/productFetch")
    Call<BaseResponse<AdvertisingProductTypeResponse>> callProductTypeFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/furnitureFetch")
    Call<BaseResponse<AdvertisingFurnitureResponse>> callFurnitureFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/applianceFetch")
    Call<BaseResponse<AdvertisingApplianceResponse>> callApplianceFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/tripFetch")
    Call<BaseResponse<AdvertisingTripResponse>> callTrippFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/estateFetch")
    Call<BaseResponse<AdvertisingEstateResponse>> callEstateFetch(@Body HashMap<String, String> map);

    @POST("/webservice/advertising/createAdvertisingApp")
    Call<BaseResponse<BaseResponse>> callAdvertisingCreate(@Body HashMap<String , String> map);

    @POST("/webservice/crashReport")
    Call<BaseResponse<CrashReporterResponse>> callCrashData(@Body HashMap<String,String>map);

    @Multipart
    @POST("/webservice/advertising/addImage")
    Call<BaseResponse<UploadImageResponse>> uploadImage(@Part MultipartBody.Part file, @Part("name") RequestBody name,@Query("imageNumber") String imageNumber);


    @Multipart
    @POST("/webservice/channel/addImage")
    Call<BaseResponse<UploadImageResponse>> uploadChannelImage(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}
