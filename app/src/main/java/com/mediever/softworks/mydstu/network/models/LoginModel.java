package com.mediever.softworks.mydstu.network.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("login")
    private String email;
    @SerializedName("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
