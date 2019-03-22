package com.example.newsapp;

import android.app.Dialog;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

import data.api.ApiManager;
import data.datamodels.Articles;
import data.datamodels.DataResponse;
import data.networkutils.UrlBuilder;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Response;
import viewModel.NewsViewModel;


public class MainActivity extends AppCompatActivity {


    private List<Articles> articleList = new ArrayList<>();
    private String query;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_news_layout);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        NewsViewModel model = ViewModelProviders
                .of(this)
                .get(NewsViewModel.class);

            model.fetchAllArticles().observe(this, articlesList ->{
                mAdapter = new MainAdapter(this,articlesList);
                recyclerView.setAdapter(mAdapter);

            });


        FloatingActionButton floatingActionButton = findViewById(R.id.fb_news_items);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }



}
