package com.example.newsapp.data.api;

import android.content.Context;

import com.example.newsapp.BuildConfig;

import java.util.List;

import com.example.newsapp.data.datamodels.Articles;
import com.example.newsapp.data.datamodels.DataResponse;
import retrofit2.Call;

public class ApiManager {

    public static List<Articles> articles;


    private String query;
    private Context context;
    private String apiKey;
    private String domain, country, category;


    private CallInstanceModel callInstanceModel
            = new CallInstanceModel();


    public ApiManager(Context context) {

        this.callInstanceModel.setInstance(CallInstance.callInstance());
         this.apiKey = BuildConfig.ApiKey;
    }
    public ApiManager(Context context, String query) {

        this.callInstanceModel.setInstance(CallInstance.callInstance());
        this.query = query;
        this.apiKey = BuildConfig.ApiKey;
    }

    public Call<DataResponse> getArticles(){
        return callInstanceModel.getInstance().getAllNews(query, apiKey);
    }

    public Call<DataResponse> getSources() {

        return callInstanceModel.getInstance().getSources(apiKey);

    }

    public Call<DataResponse> getNewsByDomains(){
        return callInstanceModel.getInstance().getNewsByDomains(query, apiKey);
    }
    public Call<DataResponse> getTopHeadlines(){
        return callInstanceModel.getInstance().getTopHeadlines(query,apiKey);
    }


}
