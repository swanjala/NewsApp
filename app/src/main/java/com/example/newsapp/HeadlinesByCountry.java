package com.example.newsapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.newsapp.Fragments.CountryListFragment;

public class HeadlinesByCountry extends AppCompatActivity {


    private static boolean LARGE_SCREEN_FLAG;

    private int mainFragmentContainer;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        if (findViewById(R.id.cl_country_detail) != null){
            this.LARGE_SCREEN_FLAG = true;
        } else{
            this.LARGE_SCREEN_FLAG = false;
        }

        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(findViewById(R.id.cl_country_detail) != null){
            this.LARGE_SCREEN_FLAG = true;

        } else {
            this.LARGE_SCREEN_FLAG = false;
        }

        loadData();
    }

    /* Loads counties to the country list fragment
    * sets up a large screen flag
    * strats up the country list fragment */

    private void loadData() {

        CountryListFragment countryListFragment = new CountryListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.data_flag_key),
                getString(R.string.load_country_string));

        if (LARGE_SCREEN_FLAG) {

            bundle.putBoolean(getString(R.string.large_string_flag_key)
                    , LARGE_SCREEN_FLAG);

            mainFragmentContainer = R.id.frame_main;

        } else {
            mainFragmentContainer = R.id.frame_counties_holder;
        }

        countryListFragment.setArguments(bundle);

        fragmentManager = this.getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(mainFragmentContainer, countryListFragment)
                .commitAllowingStateLoss();


    }
}
