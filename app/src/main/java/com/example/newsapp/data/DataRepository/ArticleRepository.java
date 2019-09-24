package com.example.newsapp.data.DataRepository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.newsapp.data.api.ApiManager;
import com.example.newsapp.data.database.AppDatabase;
import com.example.newsapp.data.database.accessobjects.ArticleAccessObject;
import com.example.newsapp.data.database.accessobjects.SourcesAccessObject;
import com.example.newsapp.data.datamodels.Articles;
import com.example.newsapp.data.datamodels.CountryConstants;
import com.example.newsapp.data.datamodels.DataResponse;
import com.example.newsapp.data.datamodels.Sources;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

    private ArticleAccessObject articleAccessObject;
    private SourcesAccessObject sourcesAccessObject;
    private LiveData<List<Articles>> mAllArticles;
    private LiveData<List<Articles>> mAllArticlesByTitle;
    private LiveData<List<Articles>> mAllArticlesByDomain;
    private LiveData<List<Articles>> mAllArticlesNoQuery;
    private LiveData<List<Sources>> mSources;
    private LiveData<List<Articles>> mArticlesByCountry;
    private LiveData<List<String>> mCountries;
    private LiveData<List<String>> mNewsCategories;
    private LiveData<List<Articles>> mNewsBySetToRead;
    private LiveData<List<Articles>> mNewsByFavorite;
    private LiveData<List<Articles>> mNewsByDomain;

    private LiveData<List<Sources>> mSourcesByNewsCategory;

    private List<Articles> mTopHeadlines,
            mTopHeadlinesByCountryCategory,
            mTopHeadlinesByCountry,
            mTopHeadlinesBySearch;

    private Application application;


    public ArticleRepository(Application application){
        initializeDatabaseValues(application);
        this.application = application;

    }

    public void initializeDatabaseValues(Application application){
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        articleAccessObject  = appDatabase.articleAccessObject();
        sourcesAccessObject = appDatabase.sourcesAccessObject();
        mSources = sourcesAccessObject.fetchAllData();
        mCountries = sourcesAccessObject.fetchCountryLists();
        mAllArticlesNoQuery = articleAccessObject.fetchAllData();
        mNewsCategories = sourcesAccessObject.fetchCategoryList();

    }

    public void initializeDatabaseValues(Application application, String query){

        AppDatabase database = AppDatabase.getDatabase(application);
        articleAccessObject  = database.articleAccessObject();
        sourcesAccessObject = database.sourcesAccessObject();
    }

    public LiveData<List<Articles>> getmAllArticles(){

        return mAllArticles;
    }
    public LiveData<List<Articles>> getAllArticlesNoQuery(){
        return mAllArticlesNoQuery;
    }

    public LiveData<List<Sources>> getSources(){

        return mSources;
    }

    public LiveData<List<Sources>> getSourcesByNewsCategory(String categoryQuery){

        mSourcesByNewsCategory = sourcesAccessObject.fetchNewsNamesByCategory(categoryQuery);
        return mSourcesByNewsCategory;
    }

    public LiveData<List<Articles>> getArticlesByTitle(Context context, String title){

        try {
            new insertDataFromTitle(context,title,articleAccessObject).execute();

        } finally {

            mAllArticlesByTitle = articleAccessObject.fetchAllData("%"+title+"%");
        }

        return mAllArticlesByTitle;

    }

    public LiveData<List<Articles>> getArticlesByDomain(Context context, String domain) {

        try {
            new insertDataFromDomain(context,domain,articleAccessObject).execute();

        } finally {

            Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
            Matcher matcher = pattern.matcher(domain);
            matcher.find();
            domain  = matcher.group(2).substring(4);

            mAllArticlesByDomain = articleAccessObject
                    .fetchDataByDomain("%"+domain+"%");
        }

        return mAllArticlesByDomain;

    }

    public LiveData<List<Articles>> getDataByFavorite(){
        mNewsByFavorite = articleAccessObject.fetchDataByFavorite();
        return mNewsByFavorite;
    }
    public LiveData<List<Articles>> getDataBySetToRead(){
        mNewsBySetToRead = articleAccessObject.fetchDataByToRead();
        return mNewsBySetToRead;
    }


    public LiveData<List<String>> getCountries(){
        return mCountries;
    }

    public LiveData<List<String>> getNewsCategories() {
        return mNewsCategories;
    }

    public void insert(List<Articles> articles){
        new insertAsyncTask(articleAccessObject).execute(articles);

    }

    public void insertFavorite(boolean setToFavorite, String title){

        HashMap hashMap = new HashMap();
        hashMap.put("favorite",setToFavorite);
        hashMap.put("title", title);

        new insertFavoriteArticle(articleAccessObject).execute(hashMap);

    }
    public void insertSetToRead(boolean setToReadValue, String title){
        HashMap hashMap = new HashMap();
        hashMap.put("toReadValue",setToReadValue);
        hashMap.put("title", title);


        new insertSetToRead(articleAccessObject).execute(hashMap);

    }

    public LiveData<List<Articles>> getArticlesByCountry(Context context, String country) {

        try {

            CountryConstants countryConstants = new CountryConstants();

            HashMap countryMap = countryConstants.countryListData();

            for (Object key : countryMap.keySet()) {
                if (country.equals(countryMap.get(key))) {

                    new insertDataFromCountry(context,key.toString().toLowerCase()
                            ,articleAccessObject).execute();
                }
            }

        } finally {

            mArticlesByCountry = articleAccessObject
                    .fetchAllCountryData("%"+country+"%");
        }

        return mArticlesByCountry;
    }

    public static class insertAsyncTask extends AsyncTask<List<Articles>, Void, Void> {

        private ArticleAccessObject mAsyckTaskAccessObject;

        insertAsyncTask(ArticleAccessObject articleAccessObject){
            mAsyckTaskAccessObject = articleAccessObject;
        }

        @Override
        protected  Void doInBackground(final List<Articles>... params){

            for (int index = 0; index < params[0].size(); index++) {
                mAsyckTaskAccessObject.createDataIfNotExists(params[0].get(index));
            }

            return null;
        }

    }


    public static class insertDataFromCountry extends AsyncTask<String, Void, Void> {

        private ArticleAccessObject mInsertByCountryAccessObject;
        private Context context;
        private String queryValue;
        private List<Articles> articlesByCountry, modifiedArticleList;

        insertDataFromCountry(Context context, String query,ArticleAccessObject articleAccessObject){
            this.context = context;
            this.queryValue = query;
            mInsertByCountryAccessObject = articleAccessObject;
        }

        @Override
        protected Void doInBackground(String... strings){
            if (strings != null){

                ApiManager apiManager = new ApiManager(context, queryValue);

                ConnectivityManager connectivityManager =(ConnectivityManager)context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();

                if (isConnected) {

                    Call<DataResponse> call = apiManager.getTopHeadlines();
                    call.enqueue(new Callback<DataResponse>() {
                        @Override
                        public void onResponse(Call<DataResponse> call,
                                               Response<DataResponse> response) {

                            if (response.body() != null) {
                                CountryConstants countryConstants = new CountryConstants();
                                String countryQuery = countryConstants
                                        .countryListData().get(queryValue.toUpperCase());

                                articlesByCountry = response.body().getArticles();
                                for (int index = 0; index < articlesByCountry.size(); index++) {

                                    String author = articlesByCountry.get(index).getAuthor();
                                    articlesByCountry.get(index)
                                            .setAuthor(author + "\n"+ countryQuery);

                                    mInsertByCountryAccessObject
                                            .createDataIfNotExists(articlesByCountry
                                            .get(index));

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResponse> call, Throwable t) {
                            Log.d("Data", t.getLocalizedMessage());

                        }
                    });
                } else {

                    Handler handler =  new Handler(context.getMainLooper());
                    handler.post( new Runnable(){
                        public void run(){
                            Toast.makeText(context,"You are not connected to the internet",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            return null;
        }

    }


    public static class insertDataFromTitle extends AsyncTask<String, Void, Void> {

        private ArticleAccessObject mInsertByTitleAccessObject;
        private Context context;
        private String queryValue;
        private List<Articles> articlesByTitle;

        insertDataFromTitle(Context context, String query,
                            ArticleAccessObject articleAccessObject){
            this.context = context;
            this.queryValue = query;
            mInsertByTitleAccessObject = articleAccessObject;
        }

        @Override
        protected Void doInBackground(String... strings){
            if (strings != null){

                ApiManager apiManager = new ApiManager(context, queryValue);

                ConnectivityManager connectivityManager =(ConnectivityManager)context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();

                if (isConnected) {

                    Call<DataResponse> call = apiManager.getArticles();
                    call.enqueue(new Callback<DataResponse>() {
                        @Override
                        public void onResponse(Call<DataResponse> call,
                                               Response<DataResponse> response) {

                            if (response.body() != null) {

                                articlesByTitle = response.body().getArticles();

                                for (int index = 0; index < articlesByTitle.size(); index++) {

                                    mInsertByTitleAccessObject.createDataIfNotExists(articlesByTitle
                                            .get(index));

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResponse> call, Throwable t) {
                            Log.d("Data", t.getLocalizedMessage());

                        }
                    });
                } else {

                    Handler handler =  new Handler(context.getMainLooper());
                    handler.post( new Runnable(){
                        public void run(){
                            Toast.makeText(context,"You are not connected to the internet",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            return null;
        }

    }
    public static class insertDataFromDomain extends AsyncTask<String, Void, Void> {

        private ArticleAccessObject mInsertByTitleAccessObject;
        private Context context;
        private String queryValue;
        private List<Articles> articlesByDomain;

        insertDataFromDomain(Context context, String domain,ArticleAccessObject articleAccessObject){
            this.context = context;
            this.queryValue = domain;

            Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
            Matcher matcher = pattern.matcher(domain);
            matcher.find();
            this.queryValue   = matcher.group(2).substring(4);
            mInsertByTitleAccessObject = articleAccessObject;
        }

        @Override
        protected Void doInBackground(String... strings){
            if (strings != null){

                ApiManager apiManager = new ApiManager(context, queryValue);

                ConnectivityManager connectivityManager =(ConnectivityManager)context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();

                if (isConnected) {

                    Call<DataResponse> call = apiManager.getNewsByDomains();
                    call.enqueue(new Callback<DataResponse>() {
                        @Override
                        public void onResponse(Call<DataResponse> call,
                                               Response<DataResponse> response) {

                            if (response.body() != null) {

                                articlesByDomain = response.body().getArticles();

                                for (int index = 0; index < articlesByDomain.size(); index++) {

                                    mInsertByTitleAccessObject.createDataIfNotExists(articlesByDomain
                                            .get(index));

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResponse> call, Throwable t) {
                            Log.d("Data", t.getLocalizedMessage());

                        }
                    });
                } else {

                    Handler handler =  new Handler(context.getMainLooper());
                    handler.post( new Runnable(){
                        public void run(){
                            Toast.makeText(context,"You are not connected to the internet",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            return null;
        }

    }

    public static class insertFavoriteArticle extends AsyncTask<HashMap, Void, Void>{
        private ArticleAccessObject mFavoriteAsyncTaskAccessObject;

        insertFavoriteArticle(ArticleAccessObject articleAccessObject){
            mFavoriteAsyncTaskAccessObject = articleAccessObject;
        }
        @Override
        protected  Void doInBackground(HashMap... hashMaps){
            if (hashMaps != null) {

                boolean favorite = (boolean)hashMaps[0].get("favorite");
                String title = (String)hashMaps[0].get("title");
                mFavoriteAsyncTaskAccessObject.setNewsItemToFavorite(favorite,
                        title);
            }
            return null;
        }

    }
    public static class insertSetToRead extends AsyncTask<HashMap, Void, Void>{
        private ArticleAccessObject mSetToReadAsyncTaskAccessObject;

        insertSetToRead(ArticleAccessObject articleAccessObject){
            mSetToReadAsyncTaskAccessObject = articleAccessObject;
        }
        @Override
        protected  Void doInBackground(HashMap ... hashMaps){
            if (hashMaps != null) {

                boolean toReadValue = (boolean)hashMaps[0].get("toReadValue");
                String title = (String)hashMaps[0].get("title");
                mSetToReadAsyncTaskAccessObject.setNewsItemToRead(toReadValue, title);
            }
            return null;
        }
    }

}
