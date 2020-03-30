package com.mediever.softworks.mydstu.network.models;

import com.google.gson.annotations.SerializedName;

public class ErrorMessage {
    @SerializedName("answer")
    private String answer;
    @SerializedName("error")
    private String error;

    public String getAnswer() {
        if(answer == null)
            answer = "";
        return answer;
    }

    public String getError() {
        if(answer == null)
            answer = "";
        return error;
    }

    public void setMessage(String answer) {
        this.answer = answer;
    }

    public void setError(String error) {
        this.error = error;
    }
}
