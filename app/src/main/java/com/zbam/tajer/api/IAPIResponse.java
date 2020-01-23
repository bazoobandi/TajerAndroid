package com.zbam.tajer.api;

import com.zbam.tajer.data.model.api.base.BaseResponse;

import retrofit2.Response;


/**
 * Created by z.bazoubandi on 7/18/2018.
 */

public interface IAPIResponse {

    void onResponseSuccess(Response<BaseResponse> response, int requestCode);
    void onResponseFailed(String errorMsg,int responseCode , int requestCode);
    void onFailedAuth(String errorMsg , int requestCode );
}
