package com.zbam.tajer.data.model.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shahab Bagheri on 2/14/2018.
 */

public class SupportTopicResponse implements Parcelable {

    @SerializedName("iSupportTopicId")
    @Expose
    private String id ;

    @SerializedName("vText")
    @Expose
    private String text ;

    @SerializedName("iParentTopicId")
    @Expose
    private String parentId ;

    @SerializedName("iChildCount")
    @Expose
    private String childCount ;

    protected SupportTopicResponse(Parcel in) {
        id = in.readString();
        text = in.readString();
        parentId = in.readString();
        childCount = in.readString();
    }

    public static final Creator<SupportTopicResponse> CREATOR = new Creator<SupportTopicResponse>() {
        @Override
        public SupportTopicResponse createFromParcel(Parcel in) {
            return new SupportTopicResponse(in);
        }

        @Override
        public SupportTopicResponse[] newArray(int size) {
            return new SupportTopicResponse[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getParentId() {
        return parentId;
    }

    public String getChildCount() {
        return childCount;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(text);
        dest.writeString(parentId);
        dest.writeString(childCount);
    }
}
