package com.zbam.tajer.costum.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbam.tajer.data.model.api.response.ChannelResponse;
import com.zbam.tajer.databinding.ItemChannelBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.main.channel.ChannelItemViewModel;

import java.util.List;

/**
 * Created by z.bazoubandi on 10/28/2018.
 */

public class ChannelAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<ChannelResponse> items;
    private Context mContext;

    public static final int VIEW_TYPE_NORMAL = 1;
    private ChannelAdapter.OnChannelClickListener mListener;

    public ChannelAdapter(Context mContext, List<ChannelResponse> items)
    {
        this.mContext = mContext;
        this.items = items;
    }


    public void setListener( ChannelAdapter.OnChannelClickListener mListener){
        this.mListener = mListener ;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemChannelBinding itemChannelBinding = ItemChannelBinding.
                inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ChannelAdapter.ChannelViewHolder(itemChannelBinding);
    }





    public class ChannelViewHolder extends BaseViewHolder{

        ItemChannelBinding binding;
        private ChannelResponse item;
        private ChannelItemViewModel mChannelItemViewModel;
        private LinearLayoutManager llm;

        int position;


        public ChannelViewHolder(ItemChannelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            item = items.get(position);
            mChannelItemViewModel = new ChannelItemViewModel(mContext,item);
            binding.setViewModel(mChannelItemViewModel);
            try {
                binding.cardItemChannel.setOnClickListener(new View.OnClickListener() {
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


    public void addItems(List<ChannelResponse> items) {
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

    public interface OnChannelClickListener {
        void onItemClickListener(ChannelResponse advertisingResponse);
    }
}

