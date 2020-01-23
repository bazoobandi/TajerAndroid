package com.zbam.tajer.ui.base;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.zbam.tajer.R;
import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.utils.ScreenUtils;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 8/17/2018.
 */

public abstract class BaseDialog extends DialogFragment{

    private BaseActivity mActivity;

    protected boolean isAllowedTouchOutside = true ;

    @Inject
    public IApiCall iApiCall ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity mActivity = (BaseActivity) context;
            this.mActivity = mActivity;
            mActivity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getContext(),getTheme()){
            @Override
            public void onBackPressed(){
                if (isAllowedTouchOutside)
                    super.onBackPressed();
            }
        };
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(
                    (int) (ScreenUtils.getScreenWidth(getContext()) * 0.9),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.setCanceledOnTouchOutside(isAllowedTouchOutside);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void showLoading() {
//        if (mActivity != null) {
//            mActivity.showLoading();
//        }
    }

    public void hideLoading() {
//        if (mActivity != null) {
//            mActivity.hideLoading();
//        }
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public void openActivityOnTokenExpire() {
//        if (mActivity != null) {
//            mActivity.openActivityOnTokenExpire();
//        }
    }

    public void show(FragmentManager fragmentManager, String tag) {
        try{
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.dialog_enter , R.anim.dialog_exit);
            Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
            if (prevFragment != null) {
                transaction.remove(prevFragment);
            }
            transaction.addToBackStack(null);
            //transaction.commitAllowingStateLoss();
            show(transaction,tag);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void dismissDialog(String tag) {
        dismiss();
        getBaseActivity().onFragmentDetached(tag);
    }


}
