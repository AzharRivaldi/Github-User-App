package com.azhar.githubusers.model.follow;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Azhar Rivaldi on 19-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class ModelFollow {

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(avatarUrl);
        parcel.writeString(login);
        parcel.writeString(htmlUrl);
    }

    protected ModelFollow(Parcel in) {
        avatarUrl = in.readString();
        login = in.readString();
        htmlUrl = in.readString();
    }

    public static final Creator<ModelFollow> CREATOR = new Creator<ModelFollow>() {
        @Override
        public ModelFollow createFromParcel(Parcel in) {
            return new ModelFollow(in);
        }

        @Override
        public ModelFollow[] newArray(int size) {
            return new ModelFollow[size];
        }
    };

}
