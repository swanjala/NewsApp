package data.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.newsapp.BuildConfig;
import com.example.newsapp.R;

import java.util.List;

import data.datamodels.Articles;
import data.datamodels.DataResponse;
import retrofit2.Call;

public class ApiManager {

    public static List<Articles> articles;


    private String base_url,query,url;
    private Context context;
    private String apiKey;

    private CallInstanceModel callInstance
            = new CallInstanceModel();


    public ApiManager(Context context) {

        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        this.query = sharedPref.getString("Query", "");

        this.callInstance.setInstance(CallInstance.callInstance());
         this.apiKey = BuildConfig.ApiKey;
    }


    public Call<DataResponse> getArticles(){

        return callInstance.getInstance().getAllNews(query, apiKey);
    }

    public Call<DataResponse> getSources() {

        return callInstance.getInstance().getSources(apiKey);

    }


}
