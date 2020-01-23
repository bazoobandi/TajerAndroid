package com.zbam.tajer.ui.main.parametertype;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.zbam.tajer.costum.adapter.ParameterTypeAdapter;
import com.zbam.tajer.data.model.api.response.ParameterTypeResponse;
import com.zbam.tajer.data.pref.DataManager;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 9/10/2018.
 */

@Module
public class ParameterTypeActivityModule {

    @Provides
    ParameterTypeViewModel providerCarTypeViewModel(DataManager dataManager)
    {
        return new ParameterTypeViewModel(dataManager);
    }

    @Provides
    ParameterTypeAdapter providesCarTypeAdapter(Context mContext, List<ParameterTypeResponse> items, List<ParameterTypeResponse> itemsCopy)
    {
        return new ParameterTypeAdapter(mContext,items,itemsCopy);
    }

    @Provides
    List<ParameterTypeResponse> provideCarTypeEmptyList(){
        List<ParameterTypeResponse> items = new ArrayList<>();
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
