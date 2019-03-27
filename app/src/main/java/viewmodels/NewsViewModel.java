package viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import data.DataRepository.ArticleRepository;
import data.datamodels.Articles;
import data.datamodels.Sources;


public class NewsViewModel extends AndroidViewModel {

    ArticleRepository mRepository;

    private LiveData<List<Articles>> articlesLiveData;
    private LiveData<List<Sources>> sourcesLiveData;
    private LiveData<List<String>> countryLiveData;
    private LiveData<List<String>> newsCategories;
    private LiveData<List<Articles>> articlesByDomain;
    private String queryParams;

    public NewsViewModel (Application application){
        super(application);
        mRepository = new ArticleRepository(application);
        articlesLiveData = mRepository.getmAllArticles();
        sourcesLiveData = mRepository.getSources();
        countryLiveData =mRepository.getCountries();
        newsCategories = mRepository.getNewsCategories();
      //  articlesByDomain = mRepository.getDataByDomain();

    }


    public LiveData<List<Articles>> fetchAllArticles() {
       return articlesLiveData;

    }
    public LiveData<List<Articles>> fetchArticlesByDomain() {
        return articlesByDomain;
    }
    public LiveData<List<Sources>> fetchAllSources() {
        return sourcesLiveData;
    }
    public LiveData<List<String>> fetchAllCountries() {
        return countryLiveData;
    }
    public LiveData<List<String>> fetchNewsCategories() {
        return newsCategories;
    }

}
