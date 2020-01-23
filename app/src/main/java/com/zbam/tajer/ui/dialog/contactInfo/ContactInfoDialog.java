package com.zbam.tajer.ui.dialog.contactInfo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbam.tajer.R;
import com.zbam.tajer.databinding.DialogContactInfoBinding;
import com.zbam.tajer.ui.base.BaseDialog;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by z.bazoubandi on 8/17/2018.
 */

@SuppressLint("ValidFragment")
public class ContactInfoDialog extends BaseDialog {

    @Inject
    ContactInfoViewModel mContactInfoViewModel;

    OnCancelListener mCancelListener;

    DialogContactInfoBinding binding;
    String tel;
    String mobile;
    String email;


    private static final String TAG = "ContactInfoDialog";

    public void setOnCancelListener(OnCancelListener mCancelListener){
        this.mCancelListener = mCancelListener;
    }

    public void dismissDialog() {
        dismissDialog(TAG);
    }

    public void callAdvertiser(String AdvertisingMobile) {
        AdvertisingMobile = "+98" + AdvertisingMobile;
        getBaseActivity().makePhoneCallIntent(AdvertisingMobile);
    }


    public ContactInfoDialog(String tel, String mobile, String email){
        super();
        this.tel = tel;
        this.mobile = mobile;
        this.email = email;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.dialog_contact_info, container, false);
        View view = binding.getRoot();
        AndroidSupportInjection.inject(this);
        binding.setViewModel(mContactInfoViewModel);
        mContactInfoViewModel.setActivity(this);
        mContactInfoViewModel.advertisingTel.set(tel);
        mContactInfoViewModel.advertisingEmail.set(email);
        mContactInfoViewModel.advertisingMobile.set(mobile);
        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCancel(DialogInterface dialog){
        mCancelListener.onCancel();
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        mCancelListener.onCancel();
    }


    public interface OnCancelListener{
        void onCancel();
    }
}
