package com.example.newsapp.data.datamodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private Integer totalResults;

    @SerializedName("articles")
    private List<Articles> articles;

    @SerializedName("sources")
    private List<Sources> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
    public List<Sources> getSources() {
        return sources;
    }



    public void setSources(List<Sources> sources) {
        this.sources = sources;
    }
}
