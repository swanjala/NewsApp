package com.example.newsapp.data.model

import androidx.room.TypeConverter
import org.json.JSONObject

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: ArticleSource): String = with(SourceTypeProperties) {
        return JSONObject().apply {
            put(id, source.id ?: 0)
            put(name, source.name)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): ArticleSource = with(SourceTypeProperties) {
        val json = JSONObject(source)
        return ArticleSource(json.get(id), json.getString(name))
    }
}

object SourceTypeProperties {
    const val id = "id"
    const val name = "name"
}
