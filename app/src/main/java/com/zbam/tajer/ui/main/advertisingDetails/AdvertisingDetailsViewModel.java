package com.zbam.tajer.ui.main.advertisingDetails;

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
import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;
import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingOthersResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 8/2/2018.
 */

public class AdvertisingDetailsViewModel extends BaseViewModel<AdvertisingDetailsActivity>{


    public AdvertisingDetailsViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public ObservableField<List<AdvertisingImageResponse>> imageResponses = new ObservableField<>();
    public ObservableField<String> advertisingTitle = new ObservableField<>("");
    public ObservableField<String> advertisingTime = new ObservableField<>("");
    public ObservableField<String> subSubCategoryName = new ObservableField<>("");
    public ObservableField<String> cityName = new ObservableField<>("");
    public ObservableField<String> actType = new ObservableField<>("");
    public ObservableField<String> actPrice = new ObservableField<>("");
    public ObservableField<String> actContent = new ObservableField<>("");
    public ObservableField<String> telAdvertising = new ObservableField<>("");
    public ObservableField<String> mobileAdvertising = new ObservableField<>("");
    public ObservableField<String> emailAdvertising = new ObservableField<>("");
    public ObservableField<String> priceType = new ObservableField<>("");
    public ObservableField<Boolean> advertisingShowPriceType = new ObservableField<>(true);
    public ObservableField<Boolean> advertisingShowPrice = new ObservableField<>(true);
    public ObservableField<Boolean> showDetails = new ObservableField<>(true);
    public ObservableField<Boolean> channelShow = new ObservableField<>(true);
    public ObservableField<String> channelName = new ObservableField<>("");
    public ObservableField<String> channelWebsite = new ObservableField<>("");
    public ObservableField<String> channelAddress = new ObservableField<>("");
    public ObservableField<Boolean> channelAddressShow = new ObservableField<>(true);
    public ObservableField<Boolean> channelWebsiteShow = new ObservableField<>(true);
    public ObservableField<Bitmap> channelUlr = new ObservableField<>();
    //CAR
    public ObservableField<Boolean> carDeatailsShow = new ObservableField<>(true);
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
    public ObservableField<String> subModelName = new ObservableField<>("");
    //ACCESSORIES
    public ObservableField<String> accessoriesTypeName = new ObservableField<>("");
    public ObservableField<Boolean> accessoriesShow = new ObservableField<>(true);
    //AGENCY
    public ObservableField<Boolean> agencyShow = new ObservableField<>(true);
    public ObservableField<String> agencyManager = new ObservableField<>("");
    public ObservableField<String> agencyAddress = new ObservableField<>("");
    public ObservableField<String> agencyFax = new ObservableField<>("");
    public ObservableField<String> agencyLicenseNumber = new ObservableField<>("");
    public ObservableField<String> agencyWebsite = new ObservableField<>("");
    //appliance
    public ObservableField<Boolean> applianceShow = new ObservableField<>(true);
    public ObservableField<String> applianceBrandName = new ObservableField<>("");
    public ObservableField<String> applianceColor = new ObservableField<>("");
    public ObservableField<String> applianceTypeName = new ObservableField<>("");
    //bicycle
    public ObservableField<Boolean> bicycleShow = new ObservableField<>(true);
    public ObservableField<String> bicycleProducYear = new ObservableField<>("");
    public ObservableField<String> bicycleBrandName = new ObservableField<>("");
    public ObservableField<String> bicycleColor = new ObservableField<>("");
    public ObservableField<String> bicycleBySexName = new ObservableField<>("");
    public ObservableField<String> bicycleBySizeName = new ObservableField<>("");
    public ObservableField<String> bicycleMaterialName = new ObservableField<>("");
    public ObservableField<String> bicycleStopTypeName = new ObservableField<>("");
    //estate
    public ObservableField<Boolean> estateShow = new ObservableField<>(true);
    public ObservableField<String> estateAlleyWidth = new ObservableField<>("");
    public ObservableField<String> estateFloor = new ObservableField<>("");
    public ObservableField<String> estateUnitInFloor = new ObservableField<>("");
    public ObservableField<String> estateArea = new ObservableField<>("");
    public ObservableField<Boolean> estateBalcony = new ObservableField<>(true);
    public ObservableField<Boolean> estatecCentralAntenna = new ObservableField<>(true);
    public ObservableField<Boolean> estateDoorRemote = new ObservableField<>(true);
    public ObservableField<Boolean> estateHomeDepot = new ObservableField<>(true);
    public ObservableField<Boolean> estateParking = new ObservableField<>(true);
    public ObservableField<Boolean> estatelLift = new ObservableField<>(true);
    public ObservableField<Boolean> estateTel = new ObservableField<>(true);
    public ObservableField<Boolean> estateVedioIPhone = new ObservableField<>(true);
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




    private AdvertisingDetailsResponse mDetailsResponse;


    private Context mContext;
    private String token;

    public void onClickBack()
    {
            getActivity().onBackPressed();
    }

    public void onClickContactInfo()
    {
        getActivity().openContactInfoDialog();
    }

    public void callAdvertisingDetails(BaseActivity activity, HashMap<String,String> map, IApiCall iApiCall)
    {
        BaseCallback baseCallback = new BaseCallback( activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_ADVERTISING_DETAILS,this);
        iApiCall.callAdvertisingDetails(map).enqueue(baseCallback);
    }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {

        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_ADVERTISING_DETAILS:
                        List<AdvertisingDetailsResponse> detailsResponses = response.body().getData();
                        List<AdvertisingImageResponse> imageResponses = new ArrayList<>();
                        List<AdvertisingOthersResponse> othersResponses = new ArrayList<>();
                        mDetailsResponse = detailsResponses.get(0);
                        othersResponses = detailsResponses.get(0).getAdvertisingOthersFields();
                        if(mDetailsResponse.getAdsImags()!=null)
                        for(int i =0 ; i < mDetailsResponse.getAdsImags().size();i++)
                        {
                            AdvertisingImageResponse mAdvertisingImageResponse = new AdvertisingImageResponse();
                            mAdvertisingImageResponse.setImgUrl(mDetailsResponse.getAdsImags().get(i));
                            imageResponses.add(mAdvertisingImageResponse);
                        }
                        getActivity().updateImage(imageResponses);
                        //imageResponses.set(0, (AdvertisingImageResponse) imageResponses);
                        if(othersResponses!=null)
                        {
                            getActivity().updateOthersFields(othersResponses);
                        }

                        updateData(mDetailsResponse);
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

    public void updateData(AdvertisingDetailsResponse mDetailsResponse)
    {
        try{
            advertisingTitle.set(mDetailsResponse.getAdvertisingTitle());
            advertisingTime.set(mDetailsResponse.getPrettyTime());
            subSubCategoryName.set(mDetailsResponse.getSubSubCategoryName());
            cityName.set(mDetailsResponse.getCityName());
            actType.set(mDetailsResponse.getActType());
            actPrice.set(mDetailsResponse.getAdvertisingPrice());
            actContent.set(mDetailsResponse.getActContent());
            priceType.set(mDetailsResponse.getPriceType());
            telAdvertising.set(mDetailsResponse.getTelAdvertiser());
            mobileAdvertising.set(mDetailsResponse.getMobileAdvertiser());
            emailAdvertising.set(mDetailsResponse.getEmailAdvertiser());
            channelName.set(mDetailsResponse.getChannelName());
            channelAddress.set(mDetailsResponse.getChannelAddress());
            channelWebsite.set(mDetailsResponse.getChannelWebsite());

            if(mDetailsResponse.getChannelToken()!=null)
            {
                channelShow.set(true);
            }else
                channelShow.set(false);

            if(mDetailsResponse.getChannelAddress()!=null)
            {
                channelAddressShow.set(true);
            }else
                channelAddressShow.set(false);

            if(mDetailsResponse.getChannelWebsite()!=null)
            {
                channelWebsiteShow.set(true);
            }else
                channelWebsiteShow.set(false);


            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            new Thread(runnable).start();
            Glide.with(getActivity())
                    .load(mDetailsResponse.getChannelLogo())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.bg_placeholder)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            channelUlr.set(resource);
                        }
                    });

            //showDetails
            if(mDetailsResponse.getAdvertisingOthersFields()!=null)
            {
                showDetails.set(true);
            }else
            {
                showDetails.set(false);
            }
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

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
