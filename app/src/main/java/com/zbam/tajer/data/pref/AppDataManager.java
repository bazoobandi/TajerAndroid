package com.zbam.tajer.data.pref;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 7/21/2018.
 */

public class AppDataManager implements DataManager{

    private final Context mContext ;
    private final PreferencesHelper mPreferencesHelper;
    //private ConfigData configData ;

    @Inject
    public AppDataManager(Context context,
                          PreferencesHelper preferencesHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
    }


    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public String getSecurityKey() {
        return null;
    }

    @Override
    public void setSecurityKey(String securityKey) {

    }

    @Override
    public String getCityId() {
        return mPreferencesHelper.getCityId();
    }

    @Override
    public void setCityId(String cityId) {
        mPreferencesHelper.setCityId(cityId);
    }

    @Override
    public String getCityName() {
        return mPreferencesHelper.getCityName();
    }

    @Override
    public void setCityName(String cityName) {
        mPreferencesHelper.setCityName(cityName);
    }

    @Override
    public String getDefaultLanguage() {
        return mPreferencesHelper.getDefaultLanguage();
    }

    @Override
    public void setDefaultLanguage(String defaultLang) {
        mPreferencesHelper.setDefaultLanguage(defaultLang);
        setIsDefaultLanguageSet(true);
    }

    @Override
    public void setIsDefaultLanguageSet(boolean b) {
        mPreferencesHelper.setIsDefaultLanguageSet(b);
    }

    @Override
    public boolean isDefaultLanguageSet() {
        return mPreferencesHelper.isDefaultLanguageSet();
    }

    @Override
    public void updateCity(String cityId, String cityName) {
        setCityId(cityId);
        setCityName(cityName);
    }
}
