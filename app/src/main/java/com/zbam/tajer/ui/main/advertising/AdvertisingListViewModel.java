package com.zbam.tajer.ui.main.advertising;

import android.databinding.ObservableField;

import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
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
 * Created by z.bazoubandi on 7/21/2018.
 */

public class AdvertisingListViewModel extends BaseViewModel<AdvertisingActivity>{


    public AdvertisingListViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public boolean hasNextPage ;
    public String cityOpenBy ;

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> SearchTitle = new ObservableField<>("");
    public ObservableField<String> searchCityName = new ObservableField<>("");
    public ObservableField<Boolean> searchCityNameTitle = new ObservableField<>(true);
    public ObservableField<Boolean> ShowSearchTxt = new ObservableField<>(true);
    private AdvertisingResponse advertisingResponse;

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }
    public void onClickSearch()
    {
        getActivity().mAdvertisingResponseList = new ArrayList<>();
        HashMap<String,String> params = new HashMap<>();
        getActivity().callAdvertisingList(1,params);
    }

    public void onClickCitySearch()
    {
        cityOpenBy = "search";
        getActivity().openCityActivity(cityOpenBy);
    }

    public void toggleMainMenu(){
        getActivity().toggleMainMenu();
    }

    public void callAdvertisingList(BaseActivity activity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_ADVERTISING,this);

        iApiCall.callAdvertisingList(map).enqueue(baseCallback);

    }

    public void callSearchAdvertising(BaseActivity activity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_SEARCH_ADVERTISING,this);

        iApiCall.callAdvertisingList(map).enqueue(baseCallback);

    }


    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {

        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_ADVERTISING:
                        List<AdvertisingResponse> advertisingResponse = response.body().getData();
                        searchCityName.set(getDataManager().getCityName());
                        getActivity().updateAdvertising(advertisingResponse);
                        break;
                    case AppConstants.API_CODE_SEARCH_ADVERTISING:
                        List<AdvertisingResponse> searchAdvertisingResponse = response.body().getData();
                        getActivity().updateSearchAdvertising(searchAdvertisingResponse);
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