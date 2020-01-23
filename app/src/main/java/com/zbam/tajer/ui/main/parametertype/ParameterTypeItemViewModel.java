package com.zbam.tajer.ui.main.parametertype;

import android.content.Context;
import android.databinding.ObservableField;

import com.zbam.tajer.data.model.api.response.ParameterTypeResponse;

/**
 * Created by z.bazoubandi on 9/10/2018.
 */

public class ParameterTypeItemViewModel {
    public ObservableField<String > carTypeTitle = new ObservableField<>("");
    public ObservableField<Boolean > showIconSub = new ObservableField<>(true);


    public ParameterTypeItemViewModel(Context mContext, ParameterTypeResponse item) {
        try{
            carTypeTitle.set(item.getParamTypeName());

            if(item.getIsLeaf().equals("y"))
            {
                showIconSub.set(false);
            }else
            {
                showIconSub.set(true);
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
