package com.mediever.softworks.mydstu.entities;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PreviewConverter {

    @TypeConverter
    public String fromPrewiev(Preview preview) {
        Gson gson = new Gson();
        String json = gson.toJson(preview);
        return json;
    }

    @TypeConverter
    public Preview toPreview(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        Preview preview = new Preview();
        preview.setUrl(jsonObject.getString("url"));
        preview.setHeight(jsonObject.getInt("height"));
        preview.setWidth(jsonObject.getInt("width"));
        return preview;
    }

}
