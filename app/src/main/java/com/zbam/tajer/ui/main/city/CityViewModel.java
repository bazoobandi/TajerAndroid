package com.zbam.tajer.ui.main.city;

import android.databinding.ObservableField;

import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.CityResponse;
import com.zbam.tajer.data.model.api.response.ParentCityResponse;
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
 * Created by z.bazoubandi on 9/4/2018.
 */

public class CityViewModel extends BaseViewModel<CityActivity> {

    public ObservableField<Boolean > isFirstLevel = new ObservableField<>(true);
    public ObservableField<Boolean > isNotFirstLevel = new ObservableField<>(true);
    public ObservableField<String > CityName = new ObservableField<>("");
    public ObservableField<String > CityId = new ObservableField<>("");

    public CityViewModel(DataManager dataManager) {
        super(dataManager);
    }

    List<CityResponse> CityResponses = new ArrayList<>();

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }

    public void onClickPriviosLevel()
    {
        getActivity().getParentCity(CityId.get().toString());
    }
    public void setParentCity(ParentCityResponse parentCityResponse)
    {
        getActivity().getCityList(parentCityResponse.getCityParentId());
        CityName.set(parentCityResponse.getCityParentName());
        CityId.set(parentCityResponse.getCityParentId());
        if(parentCityResponse.getCityParentId().toString().equals("0"))
        {
            isFirstLevel.set(true);
            isNotFirstLevel.set(false);
        }else
        {
            isFirstLevel.set(false);
            isNotFirstLevel.set(true);
        }
    }

    public void callCityList(BaseActivity activity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_City,this);

        iApiCall.callCityList(map).enqueue(baseCallback);

    }

    public void callParentCity(BaseActivity activity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_PARENT_City,this);

        iApiCall.callParentCity(map).enqueue(baseCallback);

    }



    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_City:
                        CityResponses = response.body().getData();
                        getActivity().updateCity(CityResponses);
                        break;

                    case AppConstants.API_CODE_PARENT_City:
                        List<ParentCityResponse> parentCityResponses = response.body().getData();
                        if(parentCityResponses!=null)
                        {
                            ParentCityResponse parentCityResponse = parentCityResponses.get(0);
                            setParentCity(parentCityResponse);
                        }

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

    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {

    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {

    }
}

