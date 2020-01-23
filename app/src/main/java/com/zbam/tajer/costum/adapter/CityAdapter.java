package com.zbam.tajer.costum.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.data.DataHolder;
import com.zbam.tajer.data.model.api.response.CityResponse;
import com.zbam.tajer.databinding.ItemCityBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.main.city.CityItemViewModel;

import java.util.List;

/**
 * Created by z.bazoubandi on 9/4/2018.
 */

public class CityAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<CityResponse> items;
    private List<CityResponse> itemsCopy;
    private Context mContext;

    public static final int VIEW_TYPE_NORMAL = 1;
    private CityAdapter.OnCityClickListener mListener;

    public CityAdapter(Context mContext, List<CityResponse> items,List<CityResponse> itemsCopy)
    {
        this.mContext = mContext;
        this.items = items;
        this.itemsCopy = itemsCopy;
    }


    public void setListener( CityAdapter.OnCityClickListener mListener){
        this.mListener = mListener ;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCityBinding itemCityBinding = ItemCityBinding.
                inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new CityAdapter.CityViewHolder(itemCityBinding);
    }





    public class CityViewHolder extends BaseViewHolder{

        ItemCityBinding binding;
        private CityResponse item;
        private CityItemViewModel mCityItemViewModel;
        private LinearLayoutManager llm;

        int position;


        public CityViewHolder(ItemCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            item = items.get(position);
            mCityItemViewModel = new CityItemViewModel(mContext,item);
            binding.setViewModel(mCityItemViewModel);
            try {
                binding.cardItemCity.setOnClickListener(new View.OnClickListener() {
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


    public void filter(String text) {
        items.clear();
        if(text.isEmpty()){
            items.addAll(itemsCopy);
        } else{
            text = text.toLowerCase();
            for(CityResponse item: itemsCopy){
                if(item.getCityName().toLowerCase().contains(text)){
                    items.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    public void addItems(List<CityResponse> items) {
        clearItems();
        clearItemsCopy();
        this.items.addAll(items);
        itemsCopy.addAll(items);
        notifyDataSetChanged();
    }

    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    public void clearItemsCopy() {
        itemsCopy.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnCityClickListener {
        void onItemClickListener(CityResponse CityResponse);
    }
}

