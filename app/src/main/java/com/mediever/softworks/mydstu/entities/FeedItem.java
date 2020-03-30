package com.mediever.softworks.mydstu.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedItem {
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("text")
    @Expose
    String text;
    @SerializedName("publish")
    @Expose
    String publish;
    @SerializedName("type")
    @Expose
    String type;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("rating")
    @Expose
    Float rating;
    @SerializedName("author")
    @Expose
    String author;
    @SerializedName("ava")
    @Expose
    String ava;

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getPublish() {
        return publish;
    }

    public String getDate() {
        return date;
    }

    public String getAva() {
        return ava;
    }

    public String getAuthor() {
        return author;
    }

    public Float getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setText(String text) {
        this.text = text;
    }
}
