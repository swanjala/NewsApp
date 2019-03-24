package com.example.newsapp.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.newsapp.R;
import com.example.newsapp.adapters.MainAdapter;


import butterknife.BindView;
import butterknife.ButterKnife;
import viewModel.NewsViewModel;

public class MainFragment extends Fragment {

    @BindView(R.id.bt_load)
    Button bt_load;

    @BindView(R.id.et_search_value)
    EditText et_search_value;

    @BindView(R.id.fb_news_items)
    FloatingActionButton fb_news_items;

    @BindView(R.id.rv_news_layout)
    RecyclerView rv_news_layout;

    private MainAdapter mainAdapter;

    private NewsViewModel model;

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = layoutInflater.inflate(R.layout.fragment_main_news,
                container,
                false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rv_news_layout
                .getContext());

        rv_news_layout.setLayoutManager(linearLayoutManager);

        this.model= ViewModelProviders
                .of(this)
                .get(NewsViewModel.class);


        model.fetchAllArticles().observe(this, articlesList ->{
            mainAdapter = new MainAdapter(getContext(),articlesList);
            rv_news_layout.setAdapter(mainAdapter);
            mainAdapter.notifyDataSetChanged();

        });

    }
}
