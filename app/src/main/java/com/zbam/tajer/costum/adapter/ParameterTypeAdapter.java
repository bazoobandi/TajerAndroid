package com.zbam.tajer.costum.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbam.tajer.data.model.api.response.ParameterTypeResponse;
import com.zbam.tajer.databinding.ItemParameterTypeBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.main.parametertype.ParameterTypeItemViewModel;

import java.util.List;

/**
 * Created by z.bazoubandi on 9/10/2018.
 */

public class ParameterTypeAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<ParameterTypeResponse> items;
    private List<ParameterTypeResponse> itemsCopy;
    private Context mContext;

    public static final int VIEW_TYPE_NORMAL = 1;
    private ParameterTypeAdapter.OnCarTypeClickListener mListener;

    public ParameterTypeAdapter(Context mContext, List<ParameterTypeResponse> items, List<ParameterTypeResponse> itemsCopy)
    {
        this.mContext = mContext;
        this.items = items;
        this.itemsCopy = itemsCopy;
    }


    public void setListener( ParameterTypeAdapter.OnCarTypeClickListener mListener){
        this.mListener = mListener ;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemParameterTypeBinding itemParameterTypeBinding = ItemParameterTypeBinding.
                inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ParameterTypeAdapter.CarTypeViewHolder(itemParameterTypeBinding);
    }





    public class CarTypeViewHolder extends BaseViewHolder{

        ItemParameterTypeBinding binding;
        private ParameterTypeResponse item;
        private ParameterTypeItemViewModel mParameterTypeItemViewModel;
        private LinearLayoutManager llm;

        int position;


        public CarTypeViewHolder(ItemParameterTypeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            item = items.get(position);
            mParameterTypeItemViewModel = new ParameterTypeItemViewModel(mContext,item);
            binding.setViewModel(mParameterTypeItemViewModel);
            try {
                binding.cardItemCarType.setOnClickListener(new View.OnClickListener() {
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

    public void filter(String text) {
        items.clear();
        if(text.isEmpty()){
            items.addAll(itemsCopy);
        } else{
            text = text.toLowerCase();
            for(ParameterTypeResponse item: itemsCopy){
                if(item.getParamTypeName().toLowerCase().contains(text)){
                    items.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }


    public void addItems(List<ParameterTypeResponse> items) {
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

    public interface OnCarTypeClickListener {
        void onItemClickListener(ParameterTypeResponse ParameterTypeResponse);
    }
}
