package com.zbam.tajer.ui.option.usernamechannel;

import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.option.createchannel.CreateChannelViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 11/13/2018.
 */
@Module
public class UserNameChannelActivityModule {

    @Provides
    UserNameChannelViewModel providesUserNameChannelViewModel(DataManager dataManager)
    {
        return new UserNameChannelViewModel(dataManager);
    }
}
