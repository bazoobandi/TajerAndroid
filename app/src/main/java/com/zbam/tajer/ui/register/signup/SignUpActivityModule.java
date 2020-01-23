package com.zbam.tajer.ui.register.signup;

import com.zbam.tajer.data.pref.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 7/22/2018.
 */

@Module
public class SignUpActivityModule {

    @Provides
    SignUpViewModel provideSignUpViewModel(DataManager dataManager)
    {
        return new SignUpViewModel(dataManager);
    }

}
