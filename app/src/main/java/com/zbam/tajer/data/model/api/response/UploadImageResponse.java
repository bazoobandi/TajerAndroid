package com.zbam.tajer.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by z.bazoubandi on 9/5/2018.
 */

public class UploadImageResponse {

    @SerializedName("url")
    @Expose
    private String imageUrl;

    @SerializedName("imageNumber")
    @Expose
    private String imageNumber;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageNumber() {
        return imageNumber;
    }
}
