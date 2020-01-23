package com.zbam.tajer.ui.dialog.imageView;

import android.databinding.ObservableField;

import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.base.BaseViewModel;
import com.zbam.tajer.ui.dialog.contactInfo.ContactInfoDialog;

import java.util.List;

import retrofit2.Response;

/**
 * Created by z.bazoubandi on 10/17/2018.
 */

public class ImageViewShowViewModel extends BaseViewModel<ImageViewShowDialog> {


    public ObservableField<List<AdvertisingImageResponse>> imageResponses = new ObservableField<>();


    public ImageViewShowViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onClickClose()
    {
        getActivity().dismissDialog();
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

