package com.example.newsapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import com.example.newsapp.data.DataRepository.ArticleRepository;
import com.example.newsapp.data.datamodels.Articles;
import com.example.newsapp.data.datamodels.Sources;


public class NewsViewModel extends AndroidViewModel {

    ArticleRepository mRepository;

    private LiveData<List<Articles>> articlesLiveData;
    private LiveData<List<Articles>> articlesNoQueryLiveData;
    private LiveData<List<Sources>> sourcesLiveData;
    private LiveData<List<String>> countryLiveData;
    private LiveData<List<String>> newsCategories;
    private LiveData<List<Articles>> newsByFavorite;
    private LiveData<List<Articles>> newsBySetToRead;
    private LiveData<List<Articles>> articlesByDomain;
    private LiveData<List<Articles>> articlesByTitle;
    private LiveData<List<Articles>> articlesByCountry;
    private LiveData<List<Sources>> sourcesByNewsCategory;
    private Context context;

    public NewsViewModel (Application application){
        super(application);
        mRepository = new ArticleRepository(application);
        sourcesLiveData = mRepository.getSources();
        articlesNoQueryLiveData = mRepository.getAllArticlesNoQuery();

        articlesLiveData = mRepository.getmAllArticles();
        countryLiveData =mRepository.getCountries();
        newsCategories = mRepository.getNewsCategories();
        countryLiveData =mRepository.getCountries();
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Articles>> fetchAllArticlesNoQuery(){
        return articlesNoQueryLiveData;
    }

    public LiveData<List<Articles>> fetchArticlesByTitle(String queryParam) {

      articlesByTitle = mRepository.getArticlesByTitle(context,queryParam);

        return articlesByTitle;
    }
    public LiveData<List<Sources>> fetchAllSources() {
        return sourcesLiveData;
    }
    public LiveData<List<String>> fetchAllCountries() {
        return countryLiveData;
    }
    public LiveData<List<Articles>> fetchArticlesByCountry(String country){

        articlesByCountry  = mRepository.getArticlesByCountry(context,country);

        return articlesByCountry;
    }
    public LiveData<List<String>> fetchNewsCategories() {
        return newsCategories;
    }
    public LiveData<List<Articles>> fetchArticlesByDomain(String query){

        articlesByDomain = mRepository.getArticlesByDomain(context,query);
       return articlesByDomain;
    }
    public LiveData<List<Sources>> fetchDataByNewsCategories(String categoryQuery){
        sourcesByNewsCategory = mRepository.getSourcesByNewsCategory(categoryQuery);

        return sourcesByNewsCategory;
    }
    public LiveData<List<Articles>> fetchDataByFavorite(){

        newsByFavorite = mRepository.getDataByFavorite();
        return newsByFavorite;
    }

    public LiveData<List<Articles>> fetchDataBySetToRead(){
        newsBySetToRead = mRepository.getDataBySetToRead();
        return newsBySetToRead;
    }

    public void setNewsItemsByFavorite(boolean setItem, String query){

        mRepository.insertFavorite(setItem, query);
    }
    public void setNewsItemsBySetToRead(boolean setItem, String query){
        mRepository.insertSetToRead(setItem, query);
    }

}
