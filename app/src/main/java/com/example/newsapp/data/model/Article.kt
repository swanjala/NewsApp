package com.example.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val source: Source,
    @ColumnInfo
    val author: String?,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val description: String?,
    @ColumnInfo
    val url: String?,
    @ColumnInfo
    val urlToImage: String?,
    @ColumnInfo
    val publishedAt: String?,
    @ColumnInfo
    val content: String?
)
