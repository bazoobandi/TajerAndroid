package com.zbam.tajer.costum.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
import com.zbam.tajer.databinding.ItemAdvertisingDetailsImgBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.dialog.imageView.ImageViewShowDialog;
import com.zbam.tajer.ui.main.advertisingDetails.AdvertisingDetailseItemViewModel;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/4/2018.
 */

public class AdvertisingDetailsImgeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<AdvertisingImageResponse> items;
    private Context mContext;
    private OnAdvertisingClickListener mListener;

    public static final int VIEW_TYPE_NORMAL = 1;
    //private AdvertisingAdapter.OnAdvertisingClickListener mListener;

    public AdvertisingDetailsImgeAdapter(Context mContext, List<AdvertisingImageResponse> items) {
        this.mContext = mContext;
        this.items = items;
    }


    public void setListener(OnAdvertisingClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAdvertisingDetailsImgBinding itemAdvertisingBinding = ItemAdvertisingDetailsImgBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new AdvertisingDetailsImgeAdapter.AdvertisingImgeViewHolder(itemAdvertisingBinding);
    }


    public class AdvertisingImgeViewHolder extends BaseViewHolder {

        ItemAdvertisingDetailsImgBinding binding;
        private AdvertisingImageResponse item;
        private AdvertisingDetailseItemViewModel mAdvertisingItemViewModel;
        private LinearLayoutManager llm;

        int position;


        public AdvertisingImgeViewHolder(ItemAdvertisingDetailsImgBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            item = items.get(position);
            mAdvertisingItemViewModel = new AdvertisingDetailseItemViewModel(mContext, item);
            binding.setViewModel(mAdvertisingItemViewModel);

            binding.cardItemAdvertisingDetailsImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClickListener(items);
                }
            });

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

    public interface OnAdvertisingClickListener
    {
        void onItemClickListener(List<AdvertisingImageResponse> advertisingImageResponse);
    }
    
}
