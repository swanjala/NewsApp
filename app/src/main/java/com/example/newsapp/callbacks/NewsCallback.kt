package com.example.newsapp.callbacks

import com.example.newsapp.data.datamodels.Articles

interface NewsCallback {
    fun onClick(articles: Articles)
}
