package data.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import data.DataRepository.repositorymodel.Country;
import data.api.ApiManager;
import data.datamodels.Articles;
import data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataManager {

    public static class TopHeadlines extends AsyncTask<Void, Void, Void> {

        private ApiManager apiManager;

        private  Context context;
        public List<Articles> topHeadlinesData;
        private String source;

        public TopHeadlines(Context context,String source) {
            this.context = context;
            this.source = source;
        }

        @Override
        protected Void doInBackground(final Void... params) {


            apiManager = new ApiManager(context);
            /* Top Headlines*/

            Call<DataResponse> topHeadlinesCall = apiManager.getTopHeadlines();

            topHeadlinesCall.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> topHeadlinesCall,
                                       Response<DataResponse> response) {


                    if (response.body() != null) {

                        for (int i = 0; i < response.body().getArticles().size(); i++) {

                            //  articleAccessObject.createDataIfNotExists(response.body().getArticles().get(i));
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> topHeadlinesCall, Throwable t) {
                    Log.d("Data", t.getLocalizedMessage());

                }
            });

            return null;
        }

    }

    public static class TopHeadlinesByCountry extends AsyncTask<Call, Void, List<Articles>> {

        public List<Articles> topHeadlinesByCountry;

        private ApiManager apiManager;

        private Context context;

        private String country;

        public TopHeadlinesByCountry(Context context,String country) {
            this.context = context;
            this.country = country;



        }

        @Override
        protected List<Articles> doInBackground(Call... params) {
            // articleAccessObject.createDataIfNotExists();

            Log.d("pointer","point of exectuion");

            apiManager = new ApiManager(context);
            /* Top Headlines*/



            Call<DataResponse> getTopHeadlinesByCountryCall = apiManager.getTopHeadlines();

            getTopHeadlinesByCountryCall.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> getTopHeadlinesByCountryCall,
                                       Response<DataResponse> response) {

                 //   response.body();


                    if (response.body() != null) {

//                        for (int i = 0; i < response.body().getArticles().size(); i++) {
//
//                            //  articleAccessObject.createDataIfNotExists(response.body().getArticles().get(i));
//                        }

                        topHeadlinesByCountry= response.body().getArticles();

                        Log.d("Values", topHeadlinesByCountry.get(0).getAuthor());

                    }
                }

                @Override
                public void onFailure(Call<DataResponse> getTopHeadlinesByCountryCall, Throwable t) {
                    Log.d("Data", t.getLocalizedMessage());

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(List<Articles> articlesList) {
            super.onPostExecute(articlesList);
            this.topHeadlinesByCountry = articlesList;

            Log.d("data post", String.valueOf(articlesList));
        }
    }

    public static class TopHeadlinesBySearch extends AsyncTask<Void, Void, Void> {


        private ApiManager apiManager;

        private Context context;

        private String queryValue;

        public List<Articles> topHeadlinesBySearch;

        public TopHeadlinesBySearch(Context context, String queryValue) {
            this.context = context;
            this.queryValue = queryValue;
        }

        @Override
        protected Void doInBackground(final Void... params) {

            apiManager = new ApiManager(context);
            /* Top Headlines*/

            Call<DataResponse> getTopHeadlinesBySearchCall = apiManager.getTopHeadlines();

            getTopHeadlinesBySearchCall.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> getTopHeadlinesBySearchCall,
                                       Response<DataResponse> response) {


                    if (response.body() != null) {

                        for (int i = 0; i < response.body().getArticles().size(); i++) {

                            //  articleAccessObject.createDataIfNotExists(response.body().getArticles().get(i));
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> getTopHeadlinesBySearchCall, Throwable t) {
                    Log.d("Data", t.getLocalizedMessage());

                }
            });

            return null;
        }


    }

    public static class TopHeadlinesByCountryCategory extends AsyncTask<Void, Void, Void> {


        private ApiManager apiManager;

        private Context context;

        public List<Articles> topHeadlinesByCountryCategory;

        public TopHeadlinesByCountryCategory(Context context, Country country, String category) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(final Void... params) {

            apiManager = new ApiManager(context);
            /* Top Headlines*/

            Call<DataResponse> getTopHeadlinesBySearchCall = apiManager.getTopHeadlines();

            getTopHeadlinesBySearchCall.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> getTopHeadlinesBySearchCall,
                                       Response<DataResponse> response) {


                    if (response.body() != null) {

                        for (int i = 0; i < response.body().getArticles().size(); i++) {

                            //  articleAccessObject.createDataIfNotExists(response.body().getArticles().get(i));
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataResponse> getTopHeadlinesBySearchCall, Throwable t) {
                    Log.d("Data", t.getLocalizedMessage());

                }
            });

            return null;
        }


    }

}