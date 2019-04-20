package com.example.newsapp.datamodel;

import data.datamodels.Articles;

public class DataModelTestBuilder {

    public static Articles createArticle(String author,
                                         String title,
                                         String description,
                                         String url,
                                         String urlToImage,
                                         String publishedAt,
                                         String content){

        Articles articles = new Articles();

        articles.setAuthor(author);
        articles.setTitle(title);
        articles.setDescription(description);
        articles.setUrl(url);
        articles.setUrlToImage(urlToImage);
        articles.setPublishedAt(publishedAt);
        articles.setContent(content);
        return  articles;
    }
}
