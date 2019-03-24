package data.DataRepository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.util.List;

import data.database.AppDatabase;
import data.database.utils.ArticleAccessObject;
import data.datamodels.Articles;

public class ArticleRepository {
    private ArticleAccessObject articleAccessObject;
    private LiveData<List<Articles>> mAllArticles;
    private String query;

   public ArticleRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);


        articleAccessObject  = database.articleAccessObject();

       SharedPreferences sharedPref = PreferenceManager
               .getDefaultSharedPreferences(application.getApplicationContext());
       this.query = sharedPref.getString("Query", "");

        mAllArticles = articleAccessObject.fetchAllData("%"+query+"%");
    }
    public LiveData<List<Articles>> getmAllArticles(){
        return mAllArticles;
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
