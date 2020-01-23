package com.zbam.tajer.ui.option.contactus;

import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.option.support.SupportViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 9/17/2018.
 */

@Module
public class ContactUsActivityModule {
    @Provides
    ContactUsViewModel provideContactUsViewModel(DataManager dataManager){
        return new ContactUsViewModel(dataManager);
    }
}
