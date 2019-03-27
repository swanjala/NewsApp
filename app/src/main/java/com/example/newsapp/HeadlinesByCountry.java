package com.example.newsapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.newsapp.Fragments.CountryHeadlineFragment;
import com.example.newsapp.Fragments.MainFragment;
import com.example.newsapp.adapters.CountryHeadlineAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import data.DataRepository.repositorymodel.Country;
import data.api.ApiManager;
import data.datamodels.Articles;
import data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import viewmodels.NetworkDataViewModel;
import viewmodels.ViewModelFactory;

public class HeadlinesByCountry extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private int fragmentContainer;

    private NetworkDataViewModel networkDataViewModel;
    private List<Articles> data;

    private static RecyclerView recyclerView;

    private static RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected  void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.rv_activity_detail);

         layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();

        String country = intent.getStringExtra("countryValue");

        Log.d("Data info", country);

        Country countryData = new Country();
        countryData.setCountry(country);

        new TopHeadlinesByCountry(this,countryData.getCountry()).execute();


    }

    public static class TopHeadlinesByCountry extends AsyncTask<Call, Void, List<Articles>> {



        private ApiManager apiManager;
        private List<Articles> topHeadlinesByCountry;

        private Context context;

        private String country;

        public TopHeadlinesByCountry(Context context,String country) {
            this.context = context;
            this.country = country;



        }

        @Override
        protected List<Articles> doInBackground(Call... params) {





            apiManager = new ApiManager(context, country);
            /* Top Headlines*/

            Call<DataResponse> getTopHeadlinesByCountryCall = apiManager.getTopHeadlines();


            getTopHeadlinesByCountryCall.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> getTopHeadlinesByCountryCall,
                                       Response<DataResponse> response) {


                    if (response.body() != null) {


                        mAdapter = new CountryHeadlineAdapter(context,response.body().getArticles());

                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                        Log.d("Values", String.valueOf(response.body().getArticles()));

                    }
                }

                @Override
                public void onFailure(Call<DataResponse> getTopHeadlinesByCountryCall, Throwable t) {
                    Log.d("Data", t.getLocalizedMessage());
                    Log.d("Running this", "Running this in headline call");

                }
            });

            return null;
        }

    }
}
