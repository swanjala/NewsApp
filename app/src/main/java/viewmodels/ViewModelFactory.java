package viewmodels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStoreOwner;

import data.DataRepository.repositorymodel.Country;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private Country mParam;


    public ViewModelFactory(Application application, Country param){
        mApplication = application;
        mParam = param;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass){
        return (T) new NetworkDataViewModel(mApplication,mParam);

    }



}
