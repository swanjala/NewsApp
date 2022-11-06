package com.example.newsapp.network.model

data class News(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Article>
)