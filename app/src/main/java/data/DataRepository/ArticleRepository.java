package data.DataRepository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.util.List;

import data.DataRepository.repositorymodel.Country;
import data.DataRepository.repositorymodel.Source;
import data.database.AppDatabase;
import data.database.DataManager;
import data.database.accessobjects.ArticleAccessObject;
import data.database.accessobjects.SourcesAccessObject;
import data.datamodels.Articles;
import data.datamodels.Sources;

public class ArticleRepository {

    private ArticleAccessObject articleAccessObject;
    private SourcesAccessObject sourcesAccessObject;
    private LiveData<List<Articles>> mAllArticles;
    private LiveData<List<Sources>> mSources;
    private LiveData<List<String>> mCountries;
    private LiveData<List<String>> mNewsCategories;


    private List<Articles> mTopHeadlines,
            mTopHeadlinesByCountryCategory,
            mTopHeadlinesByCountry,
            mTopHeadlinesBySearch;

    public ArticleRepository(Application application){
        initializeDatabaseValues(application);

    }

    public ArticleRepository(Application application, Source source){

        initializeDatabaseValues(application);
        mTopHeadlines = new DataManager
                .TopHeadlines(application,source.getSourceName())
                .topHeadlinesData;
    }

    public ArticleRepository(Application application, Country country){

        initializeDatabaseValues(application);

        mTopHeadlinesByCountry = new DataManager
                .TopHeadlinesByCountry(application, country.getCountry())
                .topHeadlinesByCountry;
    }

    public ArticleRepository(Application application, Country country, String category){

        initializeDatabaseValues(application);

        mTopHeadlinesByCountryCategory = new DataManager
                .TopHeadlinesByCountryCategory(application,
                country,
                category)
                .topHeadlinesByCountryCategory;
    }

    public ArticleRepository(Application application, String queryValue){


        initializeDatabaseValues(application, queryValue);
        mTopHeadlinesBySearch = new DataManager
                .TopHeadlinesBySearch(application,
                queryValue)
                .topHeadlinesBySearch;

    }

    public void initializeDatabaseValues(Application application, String query){

        AppDatabase database = AppDatabase.getDatabase(application);
        articleAccessObject  = database.articleAccessObject();
        sourcesAccessObject = database.sourcesAccessObject();

        /* Instanciate this query value*/

        mAllArticles = articleAccessObject.fetchAllData("%"+query+"%");


    }


    public void initializeDatabaseValues(Application application){

        AppDatabase database = AppDatabase.getDatabase(application);
        articleAccessObject  = database.articleAccessObject();
        sourcesAccessObject = database.sourcesAccessObject();

        mAllArticles = articleAccessObject.fetchAllData();
        mSources = sourcesAccessObject.fetchAllData();
        mCountries = sourcesAccessObject.fetchCountryLists();
        mNewsCategories = sourcesAccessObject.fetchCategoryList();
    }

   
    public LiveData<List<Articles>> getmAllArticles(){

        return mAllArticles;
    }

    public LiveData<List<Sources>> getSources(){

        return mSources;
    }

    public LiveData<List<String>> getCountries(){
        return mCountries;
    }

    public LiveData<List<String>> getNewsCategories() {
        return mNewsCategories;
    }

    public List<Articles> getTopHeadlinesByDomain(){
        return mTopHeadlines;
    }

    public List<Articles> getTopHeadlinesByCountryCategory(){
        return mTopHeadlinesByCountryCategory;
    }

    public List<Articles> getmTopHeadlinesByCountry(){
        return mTopHeadlinesByCountry;
    }

    public List<Articles> getTopHeadlinesBySearch(){
        return mTopHeadlinesBySearch;
    }


    public void insert(List<Articles> articles){
        new insertAsyncTask(articleAccessObject).execute(articles);

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

}
