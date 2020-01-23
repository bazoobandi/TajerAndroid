package com.zbam.tajer.ui.register.forgotpassword;

import com.zbam.tajer.data.pref.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 7/23/2018.
 */

@Module
public class ForgotPasswordActivityModule {

    @Provides
    ForgotPasswordViewModel provideForgotPasswordViewModel(DataManager dataManager)
    {
        return new ForgotPasswordViewModel(dataManager);
    }
}
