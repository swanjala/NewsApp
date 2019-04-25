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
import com.example.newsapp.adapters.SourcesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import viewmodels.NewsViewModel;

public class SourcesFragment extends Fragment {

    @BindView(R.id.rv_sources_layout)
    RecyclerView rv_sources_layout;

    private SourcesAdapter sourcesAdapter;
    private NewsViewModel model;

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_main_sources,
                container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rv_sources_layout
                .getContext());

        rv_sources_layout.setLayoutManager(linearLayoutManager);

        this.model= ViewModelProviders
                .of(this)
                .get(NewsViewModel.class);

        if (getArguments().getString(getString(R.string.source_category_flag_key)) != null
                && getArguments().getString(getString(R.string.source_category_flag_key)) != "" ){

            model.fetchDataByNewsCategories(getArguments()
                    .getString(getString(R.string.source_category_flag_key)))
                    .observe(this, sources -> {

                sourcesAdapter = new SourcesAdapter(getContext(), sources);
                rv_sources_layout.setAdapter(sourcesAdapter);
                sourcesAdapter.notifyDataSetChanged();

            });

        } else{

            model.fetchAllSources().observe(this, sources -> {

                sourcesAdapter = new SourcesAdapter(getContext(), sources);
                rv_sources_layout.setAdapter(sourcesAdapter);
                sourcesAdapter.notifyDataSetChanged();

            });
        }



    }
}
