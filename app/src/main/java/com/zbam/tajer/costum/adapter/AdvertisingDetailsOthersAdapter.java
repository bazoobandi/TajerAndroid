package com.zbam.tajer.costum.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zbam.tajer.data.model.api.response.advertisingDetails.AdvertisingOthersResponse;
import com.zbam.tajer.databinding.ItemAdvertisingDetailsOthersBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.main.advertisingDetails.AdvertisingDetailseOthersItemViewModel;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/4/2018.
 */

public class AdvertisingDetailsOthersAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<AdvertisingOthersResponse> items;
    private Context mContext;

    public static final int VIEW_TYPE_NORMAL = 1;

    public AdvertisingDetailsOthersAdapter(Context mContext, List<AdvertisingOthersResponse> items) {
        this.mContext = mContext;
        this.items = items;
    }
    

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAdvertisingDetailsOthersBinding itemAdvertisingBinding = ItemAdvertisingDetailsOthersBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new AdvertisingDetailsOthersAdapter.AdvertisingOthersViewHolder(itemAdvertisingBinding);
    }


    public class AdvertisingOthersViewHolder extends BaseViewHolder {

        ItemAdvertisingDetailsOthersBinding binding;
        private AdvertisingOthersResponse item;
        private AdvertisingDetailseOthersItemViewModel mAdvertisingItemViewModel;
        private LinearLayoutManager llm;

        int position;


        public AdvertisingOthersViewHolder(ItemAdvertisingDetailsOthersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            item = items.get(position);
            mAdvertisingItemViewModel = new AdvertisingDetailseOthersItemViewModel(mContext, item);
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


    public void addItems(List<AdvertisingOthersResponse> items) {
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
