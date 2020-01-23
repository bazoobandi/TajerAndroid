package com.zbam.tajer.ui.main.channel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.zbam.tajer.costum.adapter.ChannelAdapter;
import com.zbam.tajer.data.model.api.response.ChannelResponse;
import com.zbam.tajer.data.pref.DataManager;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 10/28/2018.
 */

@Module
public class ChannelActivityModule {

    @Provides
    ChannelViewModel providesChannelViewModel(DataManager dataManager)
    {
        return new ChannelViewModel(dataManager);
    }

    @Provides
    ChannelAdapter providesChannelAdapter(Context mContext, List<ChannelResponse> items)
    {
        return new ChannelAdapter(mContext,items);
    }

    @Provides
    List<ChannelResponse> provideChannelEmptyList(){
        List<ChannelResponse> items = new ArrayList<>();
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
