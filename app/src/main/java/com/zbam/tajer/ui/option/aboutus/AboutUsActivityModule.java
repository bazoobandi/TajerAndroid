package com.zbam.tajer.ui.option.aboutus;

import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.option.contactus.ContactUsViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 9/17/2018.
 */

@Module
public class AboutUsActivityModule {
    @Provides
    AboutUsViewModel provideAboutUsViewModel(DataManager dataManager){
        return new AboutUsViewModel(dataManager);
    }
}
