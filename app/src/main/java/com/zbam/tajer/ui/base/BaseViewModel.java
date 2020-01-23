package com.zbam.tajer.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.zbam.tajer.api.IAPIResponse;
import com.zbam.tajer.data.model.api.base.BaseResponse;
import com.zbam.tajer.data.pref.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

/**
 * Created by z.bazoubandi on 7/17/2018.
 */

public abstract class BaseViewModel<A> extends ViewModel implements IAPIResponse{



    private CompositeDisposable mCompositeDisposable;

    private A mActivity ;
    private final DataManager mDataManager;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    public BaseViewModel(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public void onViewCreated() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public void onDestroyView() {
        mCompositeDisposable.dispose();
    }

    public void setActivity(A activity) {this.mActivity = activity ;}

    public A getActivity(){return mActivity;}

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

}
