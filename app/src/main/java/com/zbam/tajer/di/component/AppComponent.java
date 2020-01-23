package com.zbam.tajer.di.component;

import android.app.Application;

import com.zbam.tajer.App;
import com.zbam.tajer.di.builder.ActivityBuilder;
import com.zbam.tajer.di.module.AppModule;
import com.zbam.tajer.di.module.NetworkModule;
import com.zbam.tajer.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by z.bazoubandi on 7/16/2018.
 */



@Singleton
@Component(modules = {AndroidInjectionModule.class , AppModule.class, ActivityBuilder.class ,  NetworkModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}