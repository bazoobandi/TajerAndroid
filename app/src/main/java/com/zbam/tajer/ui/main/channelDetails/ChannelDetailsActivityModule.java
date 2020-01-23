package com.zbam.tajer.ui.main.channelDetails;

import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.main.advertisingDetails.AdvertisingDetailsViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 10/28/2018.
 */

@Module
public class ChannelDetailsActivityModule {

    @Provides
    ChannelDetailsViewModel providesChannelDetailsViewModel(DataManager dataManager)
    {
        return new ChannelDetailsViewModel(dataManager);
    }
}
