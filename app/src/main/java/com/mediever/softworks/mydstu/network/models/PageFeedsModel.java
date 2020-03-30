package com.mediever.softworks.mydstu.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mediever.softworks.mydstu.entities.Feed;

import java.util.List;

public class PageFeedsModel {
    @SerializedName("totalpages")
    @Expose
    private Integer totalpages;
    @SerializedName("data")
    @Expose
    private List<Feed> data = null;

    public Integer getTotalpages() {
        return totalpages;
    }

    public List<Feed> getData() {
        return data;
    }

    public void setData(List<Feed> data) { this.data = data; }

    public void setTotalpages(Integer totalpages) {
        this.totalpages = totalpages;
    }
}
