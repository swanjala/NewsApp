package com.example.newsapp.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.adapters.MainAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import viewmodels.NetworkDataViewModel;


public class CountryHeadlineFragment extends Fragment {

    @BindView(R.id.rv_news_layout)
    RecyclerView rv_news_layout;

    private NetworkDataViewModel networkDataViewModel;

    private MainAdapter mainAdapter;

    public static CountryHeadlineFragment create(String country){
        CountryHeadlineFragment countryHeadlineFragment = new
                CountryHeadlineFragment();
        Bundle args = new Bundle();
        args.putString("Country", country);
        countryHeadlineFragment.setArguments(args);
        return countryHeadlineFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        View view = layoutInflater.inflate(R.layout.fragment_main_news,
                container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rv_news_layout
                .getContext());
        rv_news_layout.setLayoutManager(linearLayoutManager);
        this.networkDataViewModel = ViewModelProviders
                .of(this)
                .get(NetworkDataViewModel.class);

        mainAdapter = new MainAdapter(getContext(),
                networkDataViewModel.fetchTopHeadlineByCountry());

        mainAdapter.notifyDataSetChanged();



    }
}
