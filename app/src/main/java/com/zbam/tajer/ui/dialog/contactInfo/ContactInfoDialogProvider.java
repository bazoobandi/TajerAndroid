package com.zbam.tajer.ui.dialog.contactInfo;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by z.bazoubandi on 8/17/2018.
 */

@Module
public abstract class ContactInfoDialogProvider {

    @ContributesAndroidInjector(modules = ContactInfoDialogModule.class)
    abstract ContactInfoDialog provideContactInfoDialog();
}
