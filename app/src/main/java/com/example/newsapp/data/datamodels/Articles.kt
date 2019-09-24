package com.example.newsapp.data.datamodels

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.newsapp.data.database.utils.NewsAppTypeConverter

@Entity(tableName = "articles")

@TypeConverters(NewsAppTypeConverter::class)
data class Articles(@PrimaryKey(autoGenerate = true) var id: Int?) {

    @ColumnInfo(name = "author") var author:String
    @ColumnInfo(name ="title") var author:String

    @SerializedName("author")
    @Expose
    var author:String





}