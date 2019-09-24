package com.example.newsapp.data.datamodels

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import com.example.newsapp.data.database.utils.NewsAppTypeConverter

@Entity(tableName = "articles", indices = [Index(value = ["description"], unique = true)])
@TypeConverters(NewsAppTypeConverter::class)
class Articles {

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null
    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("toRead")
    @Expose
    var toRead: Boolean = false

    @SerializedName("favorite")
    @Expose
    var favorite: Boolean = false


}