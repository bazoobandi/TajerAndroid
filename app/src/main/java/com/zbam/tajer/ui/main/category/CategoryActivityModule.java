package com.zbam.tajer.ui.main.category;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.zbam.tajer.costum.adapter.CategoryAdapter;
import com.zbam.tajer.costum.adapter.CategoryAdapter;
import com.zbam.tajer.data.model.api.response.CategoryResponse;
import com.zbam.tajer.data.pref.DataManager;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.http.Query;

/**
 * Created by z.bazoubandi on 8/25/2018.
 */

@Module
public class CategoryActivityModule {

    @Provides
    CategoryViewModel providerCategoryViewModel(DataManager dataManager)
    {
        return new CategoryViewModel(dataManager);
    }

    @Provides
    CategoryAdapter providesCategoryAdapter(Context mContext, List<CategoryResponse> items,List<CategoryResponse> itemsCopy)
    {
        return new CategoryAdapter(mContext,items,itemsCopy);
    }

    @Provides
    List<CategoryResponse> provideCategoryEmptyList(){
        List<CategoryResponse> items = new ArrayList<>();
        return items ;
    }

    @Provides
    LinearLayoutManager providesLinearLayoutManager(Context context)
    {
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }
}
