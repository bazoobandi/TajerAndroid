package com.zbam.tajer.ui.main.category;

import android.databinding.ObservableField;

import com.zbam.tajer.R;
import com.zbam.tajer.api.BaseCallback;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
import com.zbam.tajer.data.model.api.response.CategoryResponse;
import com.zbam.tajer.data.model.api.response.ParentCategoryResponse;
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
 * Created by z.bazoubandi on 8/25/2018.
 */

public class CategoryViewModel extends BaseViewModel<CategoryActivity>{

    public ObservableField<Boolean > isFirstLevel = new ObservableField<>(true);
    public ObservableField<Boolean > isNotFirstLevel = new ObservableField<>(true);
    public ObservableField<String > categoryName = new ObservableField<>("");
    public ObservableField<String > categoryId = new ObservableField<>("");

    public CategoryViewModel(DataManager dataManager) {
        super(dataManager);
    }

    List<CategoryResponse> categoryResponses = new ArrayList<>();

    public void onClickBack()
    {
        getActivity().onBackPressed();
    }

    public void onClickPriviosLevel()
    {
        getActivity().getParentCategory(categoryId.get().toString());
    }
    public void setParentCategory(ParentCategoryResponse parentCategoryResponse)
    {
        getActivity().getCategoryList(parentCategoryResponse.getCategoryParentId());
        categoryName.set(parentCategoryResponse.getCategoryParentName());
        categoryId.set(parentCategoryResponse.getCategoryParentId());
        if(parentCategoryResponse.getCategoryParentId().toString().equals("0"))
        {
            isFirstLevel.set(true);
            isNotFirstLevel.set(false);
        }else
        {
            isFirstLevel.set(false);
            isNotFirstLevel.set(true);
        }
    }

    public void callCategoryList(BaseActivity activity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_CATEGORY,this);

        iApiCall.callCategoryList(map).enqueue(baseCallback);

    }

    public void callParentCategory(BaseActivity activity, IApiCall iApiCall, HashMap<String,String> map)
    {
        BaseCallback baseCallback = new BaseCallback(activity,iApiCall,true,getDataManager()
                , AppConstants.API_CODE_PARENT_CATEGORY,this);

        iApiCall.callParentCategory(map).enqueue(baseCallback);

    }



    @Override
    public void onResponseSuccess(Response<BaseResponse> response, int requestCode) {
        try {
            if (response.body().getSettings().issuccess()) {
                switch (requestCode) {
                    case AppConstants.API_CODE_CATEGORY:
                        categoryResponses = response.body().getData();
                        getActivity().updateCategory(categoryResponses);
                        break;

                        case AppConstants.API_CODE_PARENT_CATEGORY:
                        List<ParentCategoryResponse> parentCategoryResponses = response.body().getData();
                        if(parentCategoryResponses!=null)
                        {
                            ParentCategoryResponse parentCategoryResponse = parentCategoryResponses.get(0);
                            setParentCategory(parentCategoryResponse);
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
