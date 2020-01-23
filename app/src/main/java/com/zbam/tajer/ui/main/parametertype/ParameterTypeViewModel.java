package com.zbam.tajer.ui.main.parametertype;

import android.databinding.ObservableField;

import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.ParameterTypeResponse;
import com.zbam.tajer.data.model.api.response.ParentCarTypeResponse;
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
 * Created by z.bazoubandi on 9/10/2018.
 */

public class ParameterTypeViewModel extends BaseViewModel<ParameterTypeActivity> {

    public ObservableField<Boolean > isFirstLevel = new ObservableField<>(true);
    public ObservableField<Boolean > isNotFirstLevel = new ObservableField<>(true);
    public ObservableField<String > parameterTypeName = new ObservableField<>("");
    public ObservableField<String > parameterTypeId = new ObservableField<>("");

    public ParameterTypeViewModel(DataManager dataManager) {
        super(dataManager);
    }

    List<ParameterTypeResponse> parameterTypeRespons = new ArrayList<>();

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }

    public void onClickPriviosLevel()
    {
        getActivity().getParentParameterType(parameterTypeId.get().toString());
    }
    public void setParentCarType(ParentCarTypeResponse parentCarTypeResponse)
    {
        getActivity().getParameterTypeList(parentCarTypeResponse.getCarTypeParentId());
        parameterTypeName.set(parentCarTypeResponse.getCarTypeParentName());
        parameterTypeId.set(parentCarTypeResponse.getCarTypeParentId());
        if(parentCarTypeResponse.getCarTypeParentId().toString().equals("0"))
        {
            isFirstLevel.set(true);
            isNotFirstLevel.set(false);
        }else
        {
            isFirstLevel.set(false);
            isNotFirstLevel.set(true);
        }
    }

    public void callParameterTypeList(BaseActivity activity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_PARAMETER_TYPE,this);

        iApiCall.callParameterTypeList(map).enqueue(baseCallback);

    }

    public void callParentParameterType(BaseActivity activity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_PARENT_PARAMETER_TYPE,this);

        iApiCall.callParentParameterType(map).enqueue(baseCallback);

    }



    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_PARAMETER_TYPE:
                        parameterTypeRespons = response.body().getData();
                        getActivity().updateParameterType(parameterTypeRespons);
                        break;

                    case AppConstants.API_CODE_PARENT_PARAMETER_TYPE:
                        List<ParentCarTypeResponse> parentCarTypeResponses = response.body().getData();
                        if(parentCarTypeResponses!=null)
                        {
                            ParentCarTypeResponse parentCarTypeResponse = parentCarTypeResponses.get(0);
                            setParentCarType(parentCarTypeResponse);
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