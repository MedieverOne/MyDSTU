package com.mediever.softworks.mydstu.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullDepartment {
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("text")
    @Expose
    String text;
    @SerializedName("logo")
    @Expose
    String logoUrl;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
