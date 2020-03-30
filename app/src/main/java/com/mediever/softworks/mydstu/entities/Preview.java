package com.mediever.softworks.mydstu.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preview {
    @SerializedName("url")
    @Expose
    String url;
    @SerializedName("height")
    @Expose
    Integer height;
    @SerializedName("width")
    @Expose
    Integer width;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }
}
