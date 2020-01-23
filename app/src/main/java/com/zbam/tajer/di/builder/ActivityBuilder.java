package com.zbam.tajer.di.builder;


import com.zbam.tajer.crashreporter.CrashReporterActivity;
import com.zbam.tajer.crashreporter.CrashReporterActivityModule;
import com.zbam.tajer.ui.dialog.contactInfo.ContactInfoDialogProvider;
import com.zbam.tajer.ui.dialog.imageView.ImageViewShowDialogProvider;
import com.zbam.tajer.ui.main.advertising.AdvertisingActivity;
import com.zbam.tajer.ui.main.advertising.AdvertisingActivityModule;
import com.zbam.tajer.ui.main.advertisingDetails.AdvertisingDetailsActivity;
import com.zbam.tajer.ui.main.advertisingDetails.AdvertisingDetailsActivityModule;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateActivity;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateActivityModule;
import com.zbam.tajer.ui.main.advertisingsearch.AdvertisingSearchActivity;
import com.zbam.tajer.ui.main.advertisingsearch.AdvertisingSearchActivityModule;
import com.zbam.tajer.ui.main.channel.ChannelActivity;
import com.zbam.tajer.ui.main.channel.ChannelActivityModule;
import com.zbam.tajer.ui.main.channelDetails.ChannelDetailsActivity;
import com.zbam.tajer.ui.main.channelDetails.ChannelDetailsActivityModule;
import com.zbam.tajer.ui.main.parametertype.ParameterTypeActivity;
import com.zbam.tajer.ui.main.parametertype.ParameterTypeActivityModule;
import com.zbam.tajer.ui.main.category.CategoryActivity;
import com.zbam.tajer.ui.main.category.CategoryActivityModule;
import com.zbam.tajer.ui.main.city.CityActivity;
import com.zbam.tajer.ui.main.city.CityActivityModule;
import com.zbam.tajer.ui.option.aboutus.AboutUsActivity;
import com.zbam.tajer.ui.option.aboutus.AboutUsActivityModule;
import com.zbam.tajer.ui.option.contactus.ContactUsActivity;
import com.zbam.tajer.ui.option.contactus.ContactUsActivityModule;
import com.zbam.tajer.ui.option.createchannel.CreateChannelActivity;
import com.zbam.tajer.ui.option.createchannel.CreateChannelActivityModule;
import com.zbam.tajer.ui.option.rules.RulesActivity;
import com.zbam.tajer.ui.option.rules.RulesActivityModule;
import com.zbam.tajer.ui.option.support.SupportActivity;
import com.zbam.tajer.ui.option.support.SupportActivityModule;
import com.zbam.tajer.ui.option.usernamechannel.UserNameChannelActivity;
import com.zbam.tajer.ui.option.usernamechannel.UserNameChannelActivityModule;
import com.zbam.tajer.ui.register.forgotpassword.ForgotPasswordActivity;
import com.zbam.tajer.ui.register.forgotpassword.ForgotPasswordActivityModule;
import com.zbam.tajer.ui.register.login.LoginActivity;
import com.zbam.tajer.ui.register.login.LoginActivityModule;
import com.zbam.tajer.ui.register.signup.SignUpActivity;
import com.zbam.tajer.ui.register.signup.SignUpActivityModule;
import com.zbam.tajer.ui.splash.SplashActivity;
import com.zbam.tajer.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by z.bazoubandi on 7/21/2018.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules =AdvertisingActivityModule.class)
    abstract AdvertisingActivity bindMainBookListActivity();

    @ContributesAndroidInjector(modules =ChannelActivityModule.class)
    abstract ChannelActivity bindChannelActivity();

    @ContributesAndroidInjector(modules =ChannelDetailsActivityModule.class)
    abstract ChannelDetailsActivity bindChannelDetailsActivity();

    @ContributesAndroidInjector(modules =CreateChannelActivityModule.class)
    abstract CreateChannelActivity bindCreateChannelActivity();

    @ContributesAndroidInjector(modules = UserNameChannelActivityModule.class)
    abstract UserNameChannelActivity bindUserNameChannelActivity();

    @ContributesAndroidInjector(modules =CategoryActivityModule.class)
    abstract CategoryActivity bindCategoryListActivity();

    @ContributesAndroidInjector(modules =CityActivityModule.class)
    abstract CityActivity bindCityListActivity();

    @ContributesAndroidInjector(modules =ParameterTypeActivityModule.class)
    abstract ParameterTypeActivity bindCarTypeActivity();

    @ContributesAndroidInjector(modules =SignUpActivityModule.class)
    abstract SignUpActivity bindSignUpActivity();

    @ContributesAndroidInjector(modules =LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = ForgotPasswordActivityModule.class)
    abstract ForgotPasswordActivity bindForgotPasswordActivity();

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = AdvertisingCreateActivityModule.class)
    abstract AdvertisingCreateActivity bindAdvertisingCreateActivity();

    @ContributesAndroidInjector(modules = AdvertisingSearchActivityModule.class)
    abstract AdvertisingSearchActivity bindAdvertisingSearchActivity();

    @ContributesAndroidInjector(modules = SupportActivityModule.class)
    abstract SupportActivity bindSupportActivity();

    @ContributesAndroidInjector(modules = ContactUsActivityModule.class)
    abstract ContactUsActivity bindContactUsActivity();

    @ContributesAndroidInjector(modules = AboutUsActivityModule.class)
    abstract AboutUsActivity bindAboutUsActivity();

    @ContributesAndroidInjector(modules = RulesActivityModule.class)
    abstract RulesActivity bindRulesActivity();

    @ContributesAndroidInjector(modules = CrashReporterActivityModule.class)
    abstract CrashReporterActivity bindCrashReporterActivity();

    @ContributesAndroidInjector(modules ={
            AdvertisingDetailsActivityModule.class,
            ContactInfoDialogProvider.class,
            ImageViewShowDialogProvider.class}
            )
    abstract AdvertisingDetailsActivity bindAdvertisingDetailsActivity();
}
