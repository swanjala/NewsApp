package viewModel;

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

    public NewsViewModel (Application application){
        super(application);
        mRepository = new ArticleRepository(application);
        articlesLiveData = mRepository.getmAllArticles();
        sourcesLiveData = mRepository.getSources();
        countryLiveData =mRepository.getCountries();
        newsCategories = mRepository.getNewsCategories();

    }

    public LiveData<List<Articles>> fetchAllArticles() {
       return articlesLiveData;

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
