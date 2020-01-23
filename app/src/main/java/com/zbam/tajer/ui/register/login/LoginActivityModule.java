package com.zbam.tajer.ui.register.login;

import com.zbam.tajer.data.pref.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 7/23/2018.
 */

@Module
public class LoginActivityModule {

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager)
    {
        return new LoginViewModel(dataManager);
    }
}
