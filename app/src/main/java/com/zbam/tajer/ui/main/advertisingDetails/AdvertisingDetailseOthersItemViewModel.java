package com.zbam.tajer.ui.main.advertisingDetails;

import android.content.Context;
import android.databinding.ObservableField;

import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingOthersResponse;

/**
 * Created by z.bazoubandi on 8/15/2018.
 */

public class AdvertisingDetailseOthersItemViewModel {

    public ObservableField<String> advertisingValue = new ObservableField<>("");
    public ObservableField<String> advertisingTitle = new ObservableField<>("");

    public AdvertisingDetailseOthersItemViewModel(Context mContext, AdvertisingOthersResponse item)
    {
        advertisingValue.set(item.getFieldsValue());
        advertisingTitle.set(item.getFieldsTitle());
    }
}
