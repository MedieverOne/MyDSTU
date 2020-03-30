package com.mediever.softworks.mydstu.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mediever.softworks.mydstu.entities.Department;

import java.util.List;

public class DepartmentsModel {
    @SerializedName("timestamp")
    @Expose
    Integer timestamp;
    @SerializedName("list")
    @Expose
    List<Department> list = null;

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public void setList(List<Department> list) {
        this.list = list;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public List<Department> getList() {
        return list;
    }
}
