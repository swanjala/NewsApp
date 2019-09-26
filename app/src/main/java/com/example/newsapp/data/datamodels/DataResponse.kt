package com.example.newsapp.data.datamodels

import com.google.gson.annotations.SerializedName

class DataResponse {

    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults: Int? = null

    @SerializedName("articles")
    var articles: List<Articles>? = null

    @SerializedName("sources")
    var sources: List<Sources>? = null
}
