package com.mediever.softworks.mydstu.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("rating")
    @Expose
    Float rating;

    public Rating(String id, Float rating) {
        this.id = id;
        this.rating = rating;
    }

    public Float getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setId(String id) {
        this.id = id;
    }
}
