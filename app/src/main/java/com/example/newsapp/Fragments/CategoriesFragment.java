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
import com.example.newsapp.adapters.NewsCategoryAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import viewModel.NewsViewModel;

public class CategoriesFragment extends Fragment {

    @BindView(R.id.rv_news_categories)
    RecyclerView rv_news_sources;

    private NewsCategoryAdapter newsCategoryAdapter;
    private NewsViewModel newsViewModel;

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_categories,
                container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(rv_news_sources.getContext());

        rv_news_sources.setLayoutManager(linearLayoutManager);

        this.newsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel.class);

        newsViewModel.fetchNewsCategories().observe(this, categories->{
            newsCategoryAdapter = new NewsCategoryAdapter(getContext(),categories);
            rv_news_sources.setAdapter(newsCategoryAdapter);
            newsCategoryAdapter.notifyDataSetChanged();;
        });
    }

}
