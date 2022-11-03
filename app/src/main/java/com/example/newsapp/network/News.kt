package com.example.newsapp.network

data class News(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Articles>
)