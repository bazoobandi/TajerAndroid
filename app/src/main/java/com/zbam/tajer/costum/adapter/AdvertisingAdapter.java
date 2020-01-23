package com.zbam.tajer.costum.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
import com.zbam.tajer.databinding.ItemAdvertisingBinding;
import com.zbam.tajer.databinding.ItemAdvertisingBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.main.advertising.AdvertisingItemViewModel;

import java.util.List;

/**
 * Created by z.bazoubandi on 7/24/2018.
 */

public class AdvertisingAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<AdvertisingResponse> items;
    private Context mContext;

    public static final int VIEW_TYPE_NORMAL = 1;
    private OnAdvertisingClickListener mListener;

    public AdvertisingAdapter(Context mContext, List<AdvertisingResponse> items)
    {
        this.mContext = mContext;
        this.items = items;
    }


    public void setListener( AdvertisingAdapter.OnAdvertisingClickListener mListener){
        this.mListener = mListener ;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAdvertisingBinding itemAdvertisingBinding = ItemAdvertisingBinding.
                inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new AdvertisingViewHolder(itemAdvertisingBinding);
    }





    public class AdvertisingViewHolder extends BaseViewHolder{

        ItemAdvertisingBinding binding;
        private AdvertisingResponse item;
        private AdvertisingItemViewModel mAdvertisingItemViewModel;
        private LinearLayoutManager llm;

        int position;


        public AdvertisingViewHolder(ItemAdvertisingBinding binding) {
            super(binding.getRoot());
           this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            item = items.get(position);
            mAdvertisingItemViewModel = new AdvertisingItemViewModel(mContext,item);
            binding.setViewModel(mAdvertisingItemViewModel);
            try {
                binding.cardItemAdvertising.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClickListener(item);
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }

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


    public void addItems(List<AdvertisingResponse> items) {
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

    public interface OnAdvertisingClickListener {
        void onItemClickListener(AdvertisingResponse advertisingResponse);
    }
}
