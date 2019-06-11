package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.newsapp.Fragments.CategoriesFragment;
import com.example.newsapp.Fragments.MainFragment;
import com.example.newsapp.Fragments.SourcesFragment;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String DATAFLAG;
    private static boolean LARGE_SCREEN_SIZE;

    private  FragmentManager fragmentManager;
    private  int fragmentContainer;

    private FirebaseAnalytics mFirebaseAnalytics;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,
                getString(R.string.firebase_item_id_value));

        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,
                getString(R.string.firebase_main_activity_log_item_name));

        bundle.putSerializable(FirebaseAnalytics.Param.CONTENT_TYPE,
                getString(R.string.firebase_activity_log));

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);

        if (findViewById(R.id.largeScreen) != null){
            this.LARGE_SCREEN_SIZE = true;

            if(DATAFLAG != null) {

                if (DATAFLAG.equals(getString(R.string.load_all_data_flag))) {
                    loadData();
                } else if (DATAFLAG.equals(getString(R.string.load_data_sources_flag))) {

                    loadSourcesList();
                }
            }

        } else if(findViewById(R.id.cl_country_detail) != null){
            this.LARGE_SCREEN_SIZE = true;
            loadCountries();
        } else if (findViewById(R.id.fr_main_holder) != null){
            this.LARGE_SCREEN_SIZE = false;

            if(DATAFLAG != null) {
                if (DATAFLAG != null && DATAFLAG.equals(getString(R.string.load_all_data_flag))) {
                    loadData();
                } else if (DATAFLAG.equals(getString(R.string.load_data_sources_flag))) {
                    loadSourcesList();
                }
            } else {
                loadSourcesList();
            }
        }
    }

    @Override
    public  void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (findViewById(R.id.largeScreen) != null){
            this.LARGE_SCREEN_SIZE = true;

            if(DATAFLAG != null) {

                if (DATAFLAG.equals(getString(R.string.load_all_data_flag))) {
                    loadData();
                } else if (DATAFLAG.equals(getString(R.string.load_data_sources_flag))) {

                    loadSourcesList();
                }
            }

        } else if(findViewById(R.id.fr_main_holder) != null ){
            this.LARGE_SCREEN_SIZE = false;

            if(DATAFLAG != null){
                if (DATAFLAG.equals(getString(R.string.load_all_data_flag))){
                    loadData();
                }
            }
        }

       if (findViewById(R.id.cl_country_detail) != null){
           this.LARGE_SCREEN_SIZE = true;

           loadCountries();
       }

        this.LARGE_SCREEN_SIZE = false;

    }

    /* Method loads the main fragment on create or on-resume
    * and passes in instruction flags on the type of data to be
    * loaded and the screen size to use*/
    private void loadData() {

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(getResources()
                .getString(R.string.data_flag_key), DATAFLAG);

        mainFragment.setArguments(bundle);
        
        if (LARGE_SCREEN_SIZE && findViewById(R.id.frame_articles) != null){
            fragmentContainer = R.id.frame_articles;

        }else if (findViewById(R.id.fr_main_holder) != null){
            fragmentContainer = R.id.fr_main_holder;
        }

        if (!String.valueOf(fragmentContainer).equals("0")) {

            fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(fragmentContainer, mainFragment)
                    .commitAllowingStateLoss();
        }

    }

    /* Method loads sources when called,
    * sets up the data flag,
    * sets up the screen by loaded size
    * loads the initializes the sources fragment
    */

    private void loadSourcesList() {
        this.DATAFLAG =
                getString(R.string.load_data_sources_flag);
        SourcesFragment sourcesFragment = new SourcesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sourceCategory","");
        sourcesFragment.setArguments(bundle);

        if (LARGE_SCREEN_SIZE){
            fragmentContainer = R.id.frame_articles;
        }else {
            fragmentContainer = R.id.fr_main_holder;
        }
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(fragmentContainer,sourcesFragment)
                .commitAllowingStateLoss();

    }

    private void loadCountries() {

        Intent intent = new Intent(this, HeadlinesByCountry.class);
        startActivity(intent);


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
                this.DATAFLAG = getString(R.string.load_all_data_flag);
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
                this.DATAFLAG = getString(R.string.load_favorites_flag);
                loadData();
                return true;

            case R.id.loadBySetToRead:
                this.DATAFLAG = getString(R.string.load_by_set_to_read_flag);
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


