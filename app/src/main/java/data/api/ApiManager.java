package data.api;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.newsapp.R;

import java.util.List;

import data.datamodels.Articles;
import data.datamodels.DataResponse;
import retrofit2.Call;

public class ApiManager {

    public static List<Articles> articles;


    private String base_url,query,url;
    private Context context;

    private CallInstanceModel callInstance
            = new CallInstanceModel();

    public ApiManager(){

    }

    public ApiManager(Context context, String query) {
        this.context = context;
        this.query = query;

        this.base_url = context.getResources().getString(R.string.base_url);


    }


    public Call<DataResponse> getArticles(){

        this.callInstance.setInstance(CallInstance.callInstance());

        return callInstance.getInstance().getAllNews();
    }


}
