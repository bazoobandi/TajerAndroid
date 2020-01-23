package com.zbam.tajer.ui.dialog.imageView;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by z.bazoubandi on 10/17/2018.
 */

@Module
public abstract class ImageViewShowDialogProvider {

    @ContributesAndroidInjector(modules = ImageViewShowDialogModule.class)
    abstract ImageViewShowDialog provideImageViewShowDialog();
}
