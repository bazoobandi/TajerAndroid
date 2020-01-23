package com.zbam.tajer.costum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zbam.tajer.data.model.api.response.SupportTopicResponse;
import com.zbam.tajer.databinding.ItemSupportTopicViewBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.option.support.SupportItemViewModel;

import java.util.List;

/**
 * Created by shb on 12/25/2017.
 */

public class SupportAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<SupportTopicResponse> items ;
    private Context mContext ;
    private OnItemClickListener mListener ;

    public SupportAdapter(Context mContext , List<SupportTopicResponse> items){
        this.mContext = mContext ;
        this.items = items ;
    }

    public void setListener(OnItemClickListener mListener){
        this.mListener = mListener ;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSupportTopicViewBinding supportTopicViewBinding = ItemSupportTopicViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new SupportTopicViewHolder(supportTopicViewBinding);
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<SupportTopicResponse> items) {
        try {
            clearItems();
            this.items.addAll(items);
            notifyDataSetChanged();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    public class SupportTopicViewHolder extends BaseViewHolder {

        ItemSupportTopicViewBinding binding ;

        private SupportTopicResponse item ;
        private SupportItemViewModel mTransactionItemViewModel;

        int position ;

        public SupportTopicViewHolder(ItemSupportTopicViewBinding binding){
            super(binding.getRoot());
            this.binding = binding ;
        }

        @Override
        public void onBind(int position) {
            try {
                this.position = position;
                item = items.get(position);
                mTransactionItemViewModel = new SupportItemViewModel(item);
                binding.setViewModel(mTransactionItemViewModel);

                binding.tvItemSupportTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(item);
                    }
                });

                binding.executePendingBindings();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(SupportTopicResponse topic);
    }

}
