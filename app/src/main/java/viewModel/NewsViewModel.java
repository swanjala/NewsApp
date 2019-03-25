package viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import data.DataRepository.ArticleRepository;
import data.datamodels.Articles;
import data.datamodels.Source;


public class NewsViewModel extends AndroidViewModel {

    ArticleRepository mRepository;

    private LiveData<List<Articles>> articlesLiveData;
    private LiveData<List<Source>> sourcesLiveData;
    private LiveData<List<String>> countryLiveData;

    public NewsViewModel (Application application){
        super(application);
        mRepository = new ArticleRepository(application);
        articlesLiveData = mRepository.getmAllArticles();
        sourcesLiveData = mRepository.getSources();
        countryLiveData =mRepository.getCountries();

    }

    public LiveData<List<Articles>> fetchAllArticles() {
       return articlesLiveData;

    }
    public LiveData<List<Source>> fetchAllSources() {
        return sourcesLiveData;
    }
    public LiveData<List<String>> fetchAllCountries() {
        return countryLiveData;
    }

}
