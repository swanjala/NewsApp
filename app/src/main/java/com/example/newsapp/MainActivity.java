package com.example.newsapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import data.datamodels.Articles;
import viewModel.NewsViewModel;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{


    private List<Articles> articleList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchEditText;
    private Button loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.et_search_value);
        searchEditText.setVisibility(View.INVISIBLE);

        loadButton = findViewById(R.id.bt_load);
        loadButton.setVisibility(View.INVISIBLE);

        recyclerView = findViewById(R.id.rv_news_layout);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadData();

        FloatingActionButton floatingActionButton = findViewById(R.id.fb_news_items);

    }

    private void loadData() {

        NewsViewModel model = ViewModelProviders
                .of(this)
                .get(NewsViewModel.class);
        model.fetchAllArticles().observe(this, articlesList ->{
            mAdapter = new MainAdapter(this,articlesList);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            searchEditText.setVisibility(View.INVISIBLE);
            loadButton.setVisibility(View.INVISIBLE);
        });

    }


    @Override
    public void onClick(View view) {

        Toast.makeText(this,"Stop kicking me", Toast.LENGTH_LONG).show();

        String query;
        if (view.getId() == R.id.fb_news_items) {

            searchEditText.setVisibility(View.VISIBLE);
            searchEditText.bringToFront();
            loadButton.setVisibility(View.VISIBLE);

        }

        if (view.getId() == R.id.bt_load){

            query = searchEditText.getText().toString();
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Query", query);
            editor.apply();

            loadData();
        }


  }
}


