package com.example.newsapp.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.adapters.CountryAdapter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import data.datamodels.CountryConstants;
import viewmodels.NewsViewModel;

public class CountryListFragment extends Fragment {

    @BindView(R.id.rv_country_list)
    RecyclerView rv_country_list;

    private CountryAdapter countryAdapter;
    private NewsViewModel newsViewModel;

    private CountryConstants countryConstants = new CountryConstants();

    private HashMap<String, String> countryMap = countryConstants.countryListData();
    private boolean screenSize;

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){


        View   view = layoutInflater.inflate(R.layout.fragment_country_list,
                    container, false);
        ButterKnife.bind(this, view);
        return  view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(rv_country_list.getContext());


        rv_country_list.setLayoutManager(linearLayoutManager);

        this.newsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel.class);

        if (getArguments() != null && getArguments().getString("Country") !=  null) {

            newsViewModel.fetchAllCountries().observe(this, countries -> {

                countryAdapter = new CountryAdapter(getActivity(), countries, countryMap);
                rv_country_list.setAdapter(countryAdapter);
                countryAdapter.notifyDataSetChanged();

            });


        }else{

            newsViewModel.fetchArticlesByCountry(getArguments().getString("Country"))
                    .observe(this, countryData -> {

                countryAdapter = new CountryAdapter(getActivity(), countryData);
                rv_country_list.setAdapter(countryAdapter);
                countryAdapter.notifyDataSetChanged();

            });
        }




    }
}
