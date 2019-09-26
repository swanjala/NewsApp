package com.example.newsapp.data.database.utils

import android.arch.persistence.room.TypeConverter

import com.google.gson.Gson

class NewsAppTypeConverter {

    internal var gson = Gson()
    @TypeConverter
    fun fromSource(source: Set<*>): String {

        var json = " "
        for (i in source.indices) {
            json = json + gson.toJson(source)

        }
        return json
    }
}
