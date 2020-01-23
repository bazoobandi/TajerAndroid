package com.zbam.tajer.costum.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbam.tajer.data.model.api.response.CategoryResponse;
import com.zbam.tajer.data.model.api.response.CityResponse;
import com.zbam.tajer.databinding.ItemCategoryBinding;
import com.zbam.tajer.ui.base.BaseViewHolder;
import com.zbam.tajer.ui.main.category.CategoryItemViewModel;

import java.util.List;

/**
 * Created by z.bazoubandi on 8/25/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<CategoryResponse> items;
    private List<CategoryResponse> itemsCopy;
    private Context mContext;

    public static final int VIEW_TYPE_NORMAL = 1;
    private CategoryAdapter.OnCategoryClickListener mListener;

    public CategoryAdapter(Context mContext, List<CategoryResponse> items,List<CategoryResponse> itemsCopy)
    {
        this.mContext = mContext;
        this.items = items;
        this.itemsCopy = itemsCopy;
    }


    public void setListener( CategoryAdapter.OnCategoryClickListener mListener){
        this.mListener = mListener ;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding = ItemCategoryBinding.
                inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new CategoryAdapter.CategoryViewHolder(itemCategoryBinding);
    }





    public class CategoryViewHolder extends BaseViewHolder{

        ItemCategoryBinding binding;
        private CategoryResponse item;
        private CategoryItemViewModel mCategoryItemViewModel;
        private LinearLayoutManager llm;

        int position;


        public CategoryViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            item = items.get(position);
            mCategoryItemViewModel = new CategoryItemViewModel(mContext,item);
            binding.setViewModel(mCategoryItemViewModel);
            try {
                binding.cardItemCategory.setOnClickListener(new View.OnClickListener() {
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
            for(CategoryResponse item: itemsCopy){
                if(item.getCategoryName().toLowerCase().contains(text)){
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


    public void addItems(List<CategoryResponse> items) {
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

    public interface OnCategoryClickListener {
        void onItemClickListener(CategoryResponse CategoryResponse);
    }
}
