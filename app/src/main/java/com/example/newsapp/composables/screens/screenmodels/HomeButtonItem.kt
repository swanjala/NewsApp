package com.example.newsapp.composables.screens.screenmodels


data class HomeButtonItem(
    val title: String,
    val iconResource: Int,
    val destination: Int,
    val newsSourceCategory: String?,
    val sourceType: SourceType?
)
