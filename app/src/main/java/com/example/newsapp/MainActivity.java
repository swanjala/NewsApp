package com.example.newsapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import data.datamodels.Articles;
import viewModel.NewsViewModel;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{


    private List<Articles> articleList = new ArrayList<>();
    private NewsViewModel model;
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

        this.model= ViewModelProviders
                .of(this)
                .get(NewsViewModel.class);

        FloatingActionButton floatingActionButton = findViewById(R.id.fb_news_items);

    }

    private void loadData() {

        model.fetchAllArticles().observe(this, articlesList ->{
            mAdapter = new MainAdapter(this,articlesList);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            searchEditText.setVisibility(View.INVISIBLE);
            loadButton.setVisibility(View.INVISIBLE);
        });
    }

    private void loadSourcesList() {

        model.fetchAllSources().observe(this, sources -> {

            mAdapter = new SourcesAdapter(this, sources);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            searchEditText.setVisibility(View.INVISIBLE);
            loadButton.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public void onClick(View view) {

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

  @Override
    public boolean onCreateOptionsMenu(Menu menu){
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.main_menu, menu);
      return true;
  }

  @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.all_news:
                loadData();
                return true;

            case R.id.news_sources:
                loadSourcesList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
  }

}


