package com.example.newsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.newsapp.Fragments.CategoriesFragment;
import com.example.newsapp.Fragments.CountryListFragment;
import com.example.newsapp.Fragments.MainFragment;
import com.example.newsapp.Fragments.SourcesFragment;

import java.util.ArrayList;
import java.util.List;
import data.datamodels.Articles;
import viewmodels.NewsViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private List<Articles> articleList = new ArrayList<>();
    private NewsViewModel model;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText searchEditText;
    private Button loadButton;

    private  FragmentManager fragmentManager;
    private  int fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

    }

    private void loadData() {
        MainFragment mainFragment = new MainFragment();

        fragmentContainer = R.id.fr_main_holder;
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(fragmentContainer,mainFragment)
                .commitAllowingStateLoss();


    }

    private void loadSourcesList() {

        SourcesFragment sourcesFragment = new SourcesFragment();
        fragmentContainer = R.id.fr_main_holder;
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(fragmentContainer,sourcesFragment)
                .commitAllowingStateLoss();

    }


    private void loadCountries() {

        CountryListFragment countryListFragment = new CountryListFragment();
        fragmentContainer = R.id.fr_main_holder;
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(fragmentContainer,countryListFragment)
                .commitAllowingStateLoss();

    }

    public void loadCategories() {

        CategoriesFragment categoriesFragment = new CategoriesFragment();

        fragmentContainer = R.id.fr_main_holder;
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(fragmentContainer,categoriesFragment)
                .commitAllowingStateLoss();

    }

    @Override
    public void onClick(View view) {

        String query;
        if (view.getId() == R.id.fb_news_items) {

        }

//        if (view.getId() == R.id.bt_load){
//
//            query = searchEditText.getText().toString();
//            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putString("Query", query);
//            editor.apply();
//
//            loadData();
//        }
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

            case R.id.top_headlines:
                loadCountries();

                return true;

            case R.id.news_categories:
                loadCategories();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
  }

}


