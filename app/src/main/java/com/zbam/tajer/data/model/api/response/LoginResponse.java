package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 7/25/2018.
 */

public class LoginResponse {

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    @SerializedName("expires_in")
    @Expose
    private String expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }
}
