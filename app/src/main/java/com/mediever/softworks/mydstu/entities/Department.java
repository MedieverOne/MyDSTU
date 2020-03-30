package com.mediever.softworks.mydstu.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "departments_table")
public class Department {
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("logo")
    @Expose
    String logo;
    @SerializedName("title")
    @Expose
    String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
