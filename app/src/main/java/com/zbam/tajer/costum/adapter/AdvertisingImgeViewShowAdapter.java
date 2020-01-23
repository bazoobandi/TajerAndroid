package com.zbam.tajer.costum.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;
import com.zbam.tajer.databinding.ItemAdvertisingImgViewShowBinding;
import com.zbam.tajer.databinding.ItemAdvertisingImgViewShowBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.dialog.imageView.AdvertisingImgeViewShowItemViewModel;
import com.zbam.tajer.ui.dialog.imageView.ImageViewShowDialog;

import java.util.List;

/**
 * Created by z.bazoubandi on 10/17/2018.
 */

public class AdvertisingImgeViewShowAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<AdvertisingImageResponse> items;
    private Context mContext;
    private ImageViewShowDialog imageViewShowDialog;

    public static final int VIEW_TYPE_NORMAL = 1;
    private AdvertisingAdapter.OnAdvertisingClickListener mListener;

    public AdvertisingImgeViewShowAdapter(Context mContext, List<AdvertisingImageResponse> items) {
        this.mContext = mContext;
        this.items = items;
    }


    public void setListener(AdvertisingAdapter.OnAdvertisingClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAdvertisingImgViewShowBinding imgViewShowBinding = ItemAdvertisingImgViewShowBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new AdvertisingImgeViewShowAdapter.AdvertisingImgeViewHolder(imgViewShowBinding);
    }


    public class AdvertisingImgeViewHolder extends BaseViewHolder {

        ItemAdvertisingImgViewShowBinding binding;
        private AdvertisingImageResponse item;
        private AdvertisingImgeViewShowItemViewModel mAdvertisingItemViewModel;
        private LinearLayoutManager llm;

        int position;


        public AdvertisingImgeViewHolder(ItemAdvertisingImgViewShowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            item = items.get(position);
            mAdvertisingItemViewModel = new AdvertisingImgeViewShowItemViewModel(mContext, item);
            binding.setViewModel(mAdvertisingItemViewModel);

            binding.executePendingBindings();
        }
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }


    public void addItems(List<AdvertisingImageResponse> items) {
        clearItems();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}

