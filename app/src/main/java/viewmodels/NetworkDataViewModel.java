package viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import data.DataRepository.ArticleRepository;
import data.DataRepository.repositorymodel.Country;
import data.DataRepository.repositorymodel.Source;
import data.datamodels.Articles;
import data.datamodels.Sources;

public class NetworkDataViewModel extends AndroidViewModel {


    private ArticleRepository mRepository;

    private LiveData<List<Articles>> articlesLiveData;
    private LiveData<List<Sources>> sourcesLiveData;
    private LiveData<List<String>> countryLiveData;
    private LiveData<List<String>> newsCategories;
    private List<Articles> articlesByDomain;
    private List<Articles> getArticlesByCountry;
    private List<Articles> getArticlesByCountryCategory;
    private List<Articles> getArticlesBySearch;

    private String queryParams;

    public NetworkDataViewModel (Application application, String queryParam){
        super(application);
        mRepository = new ArticleRepository(application, queryParam);

        getArticlesBySearch = mRepository.getTopHeadlinesBySearch();

    }

    public LiveData<List<Articles>> fetchAllArticles() {
        return articlesLiveData;

    }
    public List<Articles> fetchNewsByDomain() {
        return articlesByDomain;
    }
    public List<Articles> fetchTopHeadlineByCountry() {
        return getArticlesByCountry;
    }
    public List<Articles> fetchTopHeadlineByCountryCategory() {
        return getArticlesByCountryCategory;
    }

}
