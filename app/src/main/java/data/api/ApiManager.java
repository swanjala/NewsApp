package data.api;

import android.content.Context;

import com.example.newsapp.BuildConfig;

import java.util.List;

import data.datamodels.Articles;
import data.datamodels.DataResponse;
import retrofit2.Call;

public class ApiManager {

    public static List<Articles> articles;


    private String query;
    private Context context;
    private String apiKey;
    private String domain, country, category;


    private CallInstanceModel callInstance
            = new CallInstanceModel();


    public ApiManager(Context context) {

        this.callInstance.setInstance(CallInstance.callInstance());
         this.apiKey = BuildConfig.ApiKey;
    }
    public ApiManager(Context context, String query) {

        this.callInstance.setInstance(CallInstance.callInstance());
        this.query = query;
        this.apiKey = BuildConfig.ApiKey;
    }
    public ApiManager(Context context, String country, String category){
        this.callInstance.setInstance(CallInstance.callInstance());
        this.country = country;
        this.category = category;
        this.apiKey = BuildConfig.ApiKey;
    }

    public Call<DataResponse> getArticles(){
        return callInstance.getInstance().getAllNews(query, apiKey);
    }

    public Call<DataResponse> getSources() {

        return callInstance.getInstance().getSources(apiKey);

    }

    public Call<DataResponse> getNewsByDomains(){
        return callInstance.getInstance().getNewsByDomains(query, apiKey);
    }
    public Call<DataResponse> getTopHeadlines(){
        return callInstance.getInstance().getTopHeadlines(query,apiKey);
    }
    public Call<DataResponse> getTopHeadlinesByCountryCategory() {
        return callInstance.getInstance().getTopHeadlinesByCountryCategory(query, category, apiKey);
    }
    public Call<DataResponse> getTopHeadlineBySearch(){
        return callInstance.getInstance().getTopHeadlineBySearch(query, apiKey);
    }


}
