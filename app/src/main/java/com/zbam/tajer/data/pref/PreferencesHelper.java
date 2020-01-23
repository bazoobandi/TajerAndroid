package com.zbam.tajer.data.pref;

/**
 * Created by z.bazoubandi on 7/17/2018.
 */

public interface PreferencesHelper {

    String getUsername();
    void setUsername(String username);

    String getPassword();
    void setPassword(String password);

    String getSecurityKey();
    void setSecurityKey(String securityKey);

    String getCityId();
    void setCityId(String cityId);

    String getCityName();
    void setCityName(String cityName);

    String getDefaultLanguage();
    void setDefaultLanguage(String defaultLang);

    void setIsDefaultLanguageSet(boolean b);
    boolean isDefaultLanguageSet();
}
