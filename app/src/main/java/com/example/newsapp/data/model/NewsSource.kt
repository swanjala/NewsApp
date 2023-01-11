package com.example.newsapp.data.model

import android.net.Uri

data class NewsSource(
    val id: String,
    val name: String,
    val description: String,
    val url: Uri,
    val category: String,
    val language: String,
    val country: String
)
