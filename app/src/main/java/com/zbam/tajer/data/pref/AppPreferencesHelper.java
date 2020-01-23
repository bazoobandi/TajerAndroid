package com.zbam.tajer.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.zbam.tajer.di.PreferenceInfo;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 7/16/2018.
 */

public class AppPreferencesHelper implements PreferencesHelper{

    private static final String PREF_KEY_USERNAME = "PREF_KEY_USERNAME";
    private static final String PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD";
    private static final String PREF_KEY_SECURITY_KEY = "PREF_KEY_SECURITY_KEY";
    private static final String PREF_KEY_CITY_ID = "PREF_KEY_CITY_ID";
    private static final String PREF_KEY_CITY_NAME = "PREF_KEY_CITY_NAME";
    private static final String PREF_KEY_DEFAULT_LANG = "PREF_KEY_DEFAULT_LANG" ;
    private static final String PREF_KEY_IS_DEFAULT_LANG_SET = "PREF_KEY_IS_DEFAULT_LANG_SET" ;


    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getUsername() {
        return mPrefs.getString(PREF_KEY_USERNAME,"");
    }

    @Override
    public void setUsername(String username) {
        mPrefs.edit().putString(PREF_KEY_USERNAME,username).apply();
    }

    @Override
    public String getPassword() {
        return mPrefs.getString(PREF_KEY_PASSWORD,"");
    }

    @Override
    public void setPassword(String password) {
        mPrefs.edit().putString(PREF_KEY_PASSWORD,password).apply();
    }

    @Override
    public String getSecurityKey() {
        return mPrefs.getString(PREF_KEY_SECURITY_KEY,"");
    }

    @Override
    public void setSecurityKey(String securityKey) {
        mPrefs.edit().putString(PREF_KEY_SECURITY_KEY,securityKey).apply();
    }

    @Override
    public String getCityId() {
        return mPrefs.getString(PREF_KEY_CITY_ID,"");
    }

    @Override
    public void setCityId(String cityId) {
        mPrefs.edit().putString(PREF_KEY_CITY_ID,cityId).apply();
    }

    @Override
    public String getCityName() {
        return mPrefs.getString(PREF_KEY_CITY_NAME,"");
    }

    @Override
    public void setCityName(String cityName) {
        mPrefs.edit().putString(PREF_KEY_CITY_NAME,cityName).apply();
    }

    @Override
    public String getDefaultLanguage() {
        return mPrefs.getString(PREF_KEY_DEFAULT_LANG,"fa");
    }

    @Override
    public void setDefaultLanguage(String defaultLang) {
        mPrefs.edit().putString(PREF_KEY_DEFAULT_LANG,defaultLang
        ).apply();
    }

    @Override
    public void setIsDefaultLanguageSet(boolean b) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_DEFAULT_LANG_SET , b).apply();
    }

    @Override
    public boolean isDefaultLanguageSet() {
        return mPrefs.getBoolean(PREF_KEY_IS_DEFAULT_LANG_SET ,false);
    }
}
