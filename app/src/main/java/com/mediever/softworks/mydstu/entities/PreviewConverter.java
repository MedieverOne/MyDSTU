package com.mediever.softworks.mydstu.entities;

import android.util.Log;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PreviewConverter {

    @TypeConverter
    public String fromPreview(Preview preview)  {
        Gson gson = new Gson();
        String json = gson.toJson(preview);
        return json;
    }

    @TypeConverter
    public Preview toPreview(String data)  {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Preview preview = new Preview();
            preview.setUrl(jsonObject.getString("url"));
            preview.setHeight(jsonObject.getInt("height"));
            preview.setWidth(jsonObject.getInt("width"));
            return preview;
        } catch (JSONException e) {
            Log.d("HALO", "Invalid JSON string: ", e);
            return null;
        }
    }

}
