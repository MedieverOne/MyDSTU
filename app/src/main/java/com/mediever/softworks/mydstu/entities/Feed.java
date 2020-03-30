package com.mediever.softworks.mydstu.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

@Entity(tableName = "feeds_table")
public class Feed {
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @Expose
    String id;
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
    @TypeConverters({PreviewConverter.class})
    @SerializedName("preview")
    @Expose
    Preview preview;

    public Feed(String id, String title, String text, String publish, String type, String date, Float rating, String author, String ava, Preview preview) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.publish = publish;
        this.type = type;
        this.date = date;
        this.rating = rating;
        this.author = author;
        this.ava = ava;
        this.preview = preview;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

}
