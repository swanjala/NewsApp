package com.example.newsapp.adapters;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapp.Fragments.MainFragment;
import com.example.newsapp.R;

import java.util.HashMap;
import java.util.List;

import data.datamodels.Articles;

public class CountryAdapter extends
        RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<String> mCountryList;
    private List<Articles> articlesByCountry;
    private LayoutInflater layoutInflater;
    private static Context context;
    private HashMap<String, String> countryData;
    private static boolean mScreenFlag;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public CountryAdapter(Context context, List<String> countryList,
                          HashMap<String, String> countryData){

        mCountryList = countryList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.countryData = countryData;
    }


    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                int viewType){

        if (articlesByCountry != null) {

            View view = layoutInflater.inflate(R.layout.card_country_list,
                    viewGroup,false);

            CountryViewHolder countryViewHolder = new CountryViewHolder(view, countryData);

            return countryViewHolder;
        }else
        {
            View view = layoutInflater.inflate(R.layout.card_country_list,
                    viewGroup,false);
            CountryViewHolder countryViewHolder = new CountryViewHolder(view, countryData);

            return countryViewHolder;
        }


    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position){
        String currentCountry = mCountryList.get(position);
        holder.setData(currentCountry, position);
    }
    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

   static class CountryViewHolder extends  RecyclerView.ViewHolder{

        private TextView tv_country;
        private HashMap<String, String> mCountryData;
       private  int fragmentContainer;
       private  FragmentManager fragmentManager;


        public CountryViewHolder(View countryView,
                                 HashMap<String, String> countryData){
            super(countryView);

            tv_country = countryView.findViewById(R.id.tv_country);
            this.mCountryData = countryData;

        }

        public void setData(final String currentCountry, final int position){

            this.tv_country.setText(mCountryData.get(currentCountry.toUpperCase()));

            tv_country.setOnClickListener(v -> {
                FragmentManager fragmentManager = ((AppCompatActivity)context)
                        .getSupportFragmentManager();


                MainFragment mainFragment = new MainFragment();
                FragmentTransaction fragmentTransaction;
                Bundle bundle = new Bundle();

                bundle.putString("DataFlag", "ArticlesByCountry");

                String country = mCountryData.get(currentCountry.toUpperCase());

                bundle.putString("Country", country);

                if (((AppCompatActivity) context)
                        .findViewById(R.id.frame_counties_holder) != null) {

                    fragmentContainer = R.id.frame_counties_holder;
                } else if (((AppCompatActivity) context)
                        .findViewById(R.id.frame_detail) != null){

                    fragmentContainer = R.id.frame_detail;

                }

                mainFragment.setArguments(bundle);

                fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(fragmentContainer,mainFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commitAllowingStateLoss();


            });
        }

    }
}
