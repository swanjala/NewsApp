package com.example.newsapp.data.database.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import java.util.Set;

public class NewsAppTypeConverter {

    Gson gson = new Gson();
    @TypeConverter
    public String fromSource(Set source) {

        String json = " ";
        for (int i = 0; i < source.size() ; i++) {
             json = json.concat( gson.toJson(source));

        }
        return json;
    }
}
