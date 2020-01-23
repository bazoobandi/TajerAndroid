package com.zbam.tajer.ui.dialog.contactInfo;

import com.zbam.tajer.data.pref.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 8/17/2018.
 */

@Module
public class ContactInfoDialogModule {

    @Provides
    ContactInfoViewModel porvideContactInfoViewModel(DataManager dataManager){
        return new ContactInfoViewModel(dataManager);
    }
}

