package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.newsapp.Fragments.CategoriesFragment;
import com.example.newsapp.Fragments.CountryListFragment;
import com.example.newsapp.Fragments.MainFragment;
import com.example.newsapp.Fragments.SourcesFragment;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String DATAFLAG;

    private  FragmentManager fragmentManager;
    private  int fragmentContainer;

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.DATAFLAG = "all_data";

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"MainActivity");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,"MainActivityLog");
        bundle.putSerializable(FirebaseAnalytics.Param.CONTENT_TYPE,"ActivityLog");

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);
         loadData();

    }

    @Override
    protected  void onResume() {
        super.onResume();

    }

    private void loadData() {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("DataFlag", DATAFLAG);
        mainFragment.setArguments(bundle);

        fragmentContainer = R.id.fr_main_holder;
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(fragmentContainer,mainFragment)
                .commitAllowingStateLoss();


    }


    private void loadSourcesList() {
        SourcesFragment sourcesFragment = new SourcesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sourceCategory","");
        sourcesFragment.setArguments(bundle);
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
                this.DATAFLAG = "all_data";
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

            case R.id.loadFavorites:
                this.DATAFLAG = "load_favorites";
                loadData();
                return true;

            case R.id.loadBySetToRead:
                this.DATAFLAG = "load_set_to_read";
                loadData();
                return true;

            case R.id.action_settings:
                Intent startSettingIntent = new
                        Intent(this, Settings.class);
                startActivity(startSettingIntent);

            default:
                return super.onOptionsItemSelected(item);
        }
  }

}


