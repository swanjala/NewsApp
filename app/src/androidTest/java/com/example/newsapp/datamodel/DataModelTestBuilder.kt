package com.example.newsapp.datamodel

import com.example.newsapp.data.datamodels.Articles

object DataModelTestBuilder {

    fun createArticle(author: String,
                      title: String,
                      description: String,
                      url: String,
                      urlToImage: String,
                      publishedAt: String,
                      content: String): Articles {

        val articles = Articles()

        articles.author = author
        articles.title = title
        articles.description = description
        articles.url = url
        articles.urlToImage = urlToImage
        articles.publishedAt = publishedAt
        articles.content = content
        return articles
    }
}
