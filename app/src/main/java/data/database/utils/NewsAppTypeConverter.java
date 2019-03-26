package data.database.utils;

import android.annotation.SuppressLint;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.util.StringUtil;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import data.datamodels.Sources;

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
