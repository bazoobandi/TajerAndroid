package com.zbam.tajer.ui.dialog.contactInfo;

import android.databinding.ObservableField;

import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseViewModel;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 8/17/2018.
 */

public class ContactInfoViewModel extends BaseViewModel<ContactInfoDialog>{


    public ObservableField<String> advertisingTel = new ObservableField<>("");
    public ObservableField<String> advertisingEmail = new ObservableField<>("");
    public ObservableField<String> advertisingMobile = new ObservableField<>("");


    public ContactInfoViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickClose()
    {
        getActivity().dismissDialog();
    }

    public void onClickCallMobile()
    {
        getActivity().callAdvertiser(advertisingMobile.get().toString());
    }

    public void onClickCallTel()
    {
        getActivity().callAdvertiser(advertisingTel.get().toString());
    }



    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {

    }

    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {

    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {

    }
}
