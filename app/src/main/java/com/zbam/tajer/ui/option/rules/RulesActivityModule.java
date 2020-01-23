package com.zbam.tajer.ui.option.rules;

import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.option.aboutus.AboutUsViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 9/29/2018.
 */

@Module
public class RulesActivityModule {
    @Provides
    RulesViewModel provideRulesViewModel(DataManager dataManager){
        return new RulesViewModel(dataManager);
    }
}
