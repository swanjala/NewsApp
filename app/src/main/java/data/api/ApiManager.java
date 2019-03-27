package data.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.newsapp.BuildConfig;
import com.example.newsapp.R;

import java.util.List;

import javax.sql.DataSource;

import data.datamodels.Articles;
import data.datamodels.DataResponse;
import retrofit2.Call;

public class ApiManager {

    public static List<Articles> articles;


    private String base_url,query,url;
    private Context context;
    private String apiKey;
    private String domain, country, category;


    private CallInstanceModel callInstance
            = new CallInstanceModel();


    public ApiManager(Context context) {

        this.callInstance.setInstance(CallInstance.callInstance());
         this.apiKey = BuildConfig.ApiKey;
    }
    public ApiManager(Context context, String country) {

        this.callInstance.setInstance(CallInstance.callInstance());
        this.country = country;
        this.apiKey = BuildConfig.ApiKey;
    }
    public ApiManager(Context context, String country, String category){
        this.callInstance.setInstance(CallInstance.callInstance());
        this.country = country;
        this.category = category;
        this.apiKey = BuildConfig.ApiKey;
    }


    public Call<DataResponse> getArticles(){

        /*update this for dynamic querying*/
        query = "nokia";

        return callInstance.getInstance().getAllNews(query, apiKey);
    }

    public Call<DataResponse> getSources() {

        return callInstance.getInstance().getSources(apiKey);

    }

    public Call<DataResponse> getNewsByDomains(){
        return callInstance.getInstance().getNewsByDomains(domain, apiKey);
    }
    public Call<DataResponse> getTopHeadlines(){
        return callInstance.getInstance().getTopHeadlines(country,apiKey);
    }
    public Call<DataResponse> getTopHeadlinesByCountryCategory() {
        return callInstance.getInstance().getTopHeadlinesByCountryCategory(country, category, apiKey);
    }
    public Call<DataResponse> getTopHeadlineBySearch(){
        return callInstance.getInstance().getTopHeadlineBySearch(query, apiKey);
    }


}
