package com.zbam.tajer.ui.main.advertisingsearch;

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

import java.util.HashMap;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 9/27/2018.
 */

public class AdvertisingSearchViewModel extends BaseViewModel<AdvertisingSearchActivity> {


    public AdvertisingSearchViewModel(DataManager dataManager) {
        super(dataManager);
    }
    public ObservableField<String> categoryName = new ObservableField<>("");
    public ObservableField<String> categoryId = new ObservableField<>("");
    public ObservableField<Boolean> fieldShowBycategory = new ObservableField<>(true);
    public ObservableField<Boolean> actPriceShow = new ObservableField<>(true);
    public String categoryOpenBy;

    private AdvertisingResponse advertisingSearchResponse;

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }

    public void openCategoryList(){
        categoryOpenBy = "search";
        getActivity().openCategoryActivity(categoryOpenBy);
    }

    public void onClearCategory(){
        categoryName.set("");
        categoryId.set("");
    }

    public void onClickSend(){
        getActivity().searchAdvertising();
    }

    public void advertisingSearch(BaseActivity baseActivity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(baseActivity,iApiCall
                ,true,getDataManager(), AppConstants.API_CODE_LOGIN,this);
        iApiCall.callAdvertisingList(map).enqueue(baseCallback);
    }

    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {

        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_LOGIN:
                        advertisingSearchResponse = (AdvertisingResponse) response.body().getData().get(0);
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

