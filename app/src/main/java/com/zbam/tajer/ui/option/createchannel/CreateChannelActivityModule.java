package com.zbam.tajer.ui.option.createchannel;

import com.zbam.tajer.data.pref.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 11/12/2018.
 */

@Module
public class CreateChannelActivityModule {

    @Provides
    CreateChannelViewModel providesCreateChannelViewModel(DataManager dataManager)
    {
        return new CreateChannelViewModel(dataManager);
    }
}
