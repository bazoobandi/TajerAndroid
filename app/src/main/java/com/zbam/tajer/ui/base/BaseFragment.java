/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.zbam.tajer.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbam.tajer.api.IApiCall;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by z.bazoubandi on 7/18/2018.
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {


    @Inject
    protected IApiCall iApiCall ;

    private BaseActivity mBaseActivity;
    private T mViewDataBinding;
    private V mViewModel;
    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getViewModel();
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
        mViewModel.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        mViewModel.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.mBaseActivity = baseActivity;
            baseActivity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        mBaseActivity = null;
        super.onDetach();
    }


    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    public T getViewDataBinding(){
        return mViewDataBinding;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean isNetworkConnected() {
        return mBaseActivity != null && mBaseActivity.isNetworkConnected();
    }

    public void hideKeyboard() {
        if (mBaseActivity != null) {
            mBaseActivity.hideKeyboard();
        }
    }


    private void performDependencyInjection() {
       AndroidSupportInjection.inject(this);
    }

 public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
    /**
     * Override for set view model
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * Override for set binding variable
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();
}
