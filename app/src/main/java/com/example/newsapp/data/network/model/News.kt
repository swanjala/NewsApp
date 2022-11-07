package com.example.newsapp.data.network.model

data class News(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Article>
)