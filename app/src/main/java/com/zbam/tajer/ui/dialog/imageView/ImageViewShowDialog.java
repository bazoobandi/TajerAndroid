package com.zbam.tajer.ui.dialog.imageView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.zbam.tajer.R;
import com.zbam.tajer.costum.adapter.AdvertisingDetailsImgeAdapter;
import com.zbam.tajer.costum.adapter.AdvertisingImgeViewShowAdapter;
import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;
import com.zbam.tajer.databinding.DialogImageViewShowBinding;
import com.zbam.tajer.ui.base.BaseDialog;
import com.zbam.tajer.utils.LinePagerIndicatorDecoration;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by z.bazoubandi on 10/17/2018.
 */

@SuppressLint("ValidFragment")
public class ImageViewShowDialog extends BaseDialog {

    @Inject
    ImageViewShowViewModel mImageViewShowViewModel;

    @Inject
    AdvertisingImgeViewShowAdapter mAdvertisingImgeViewShowAdapter;

    @Inject
    LinearLayoutManager llm;

    ImageViewShowDialog.OnCancelListener mCancelListener;

    DialogImageViewShowBinding binding;
    List<AdvertisingImageResponse> imageResponses;



    private static final String TAG = "ImageViewShowDialog";

    public void setOnCancelListener(ImageViewShowDialog.OnCancelListener mCancelListener){
        this.mCancelListener = mCancelListener;
    }

    public void dismissDialog() {
        dismissDialog(TAG);
    }


    public ImageViewShowDialog(List<AdvertisingImageResponse> imageResponses){
        super();
        this.imageResponses = imageResponses;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.dialog_image_view_show, container, false);
        View view = binding.getRoot();
        AndroidSupportInjection.inject(this);
        binding.setViewModel(mImageViewShowViewModel);
        mImageViewShowViewModel.setActivity(this);
        init();

        mImageViewShowViewModel.imageResponses.set(imageResponses);
        return view;
    }

    public  void init()
    {
        mAdvertisingImgeViewShowAdapter.addItems(imageResponses);
        binding.rvDialogImageViewShow.setAdapter(mAdvertisingImgeViewShowAdapter);
        binding.rvDialogImageViewShow.setLayoutManager(llm);
        binding.rvDialogImageViewShow.addItemDecoration(new LinePagerIndicatorDecoration());
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView( binding.rvDialogImageViewShow);

        binding.rvDialogImageViewShow.setItemAnimator(new DefaultItemAnimator());
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
