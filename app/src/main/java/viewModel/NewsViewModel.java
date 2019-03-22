package viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import data.DataRepository.ArticleRepository;
import data.datamodels.Articles;


public class NewsViewModel extends AndroidViewModel {

    ArticleRepository mRepository;

    private LiveData<List<Articles>> articlesLiveData;

    public NewsViewModel (Application application){
        super(application);
        mRepository = new ArticleRepository(application);
        articlesLiveData = mRepository.getmAllArticles();

    }

    LiveData<List<Articles>> fetchAllArticles() {
        return articlesLiveData;

    }
}
