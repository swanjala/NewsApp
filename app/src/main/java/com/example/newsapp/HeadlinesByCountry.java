package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.newsapp.Fragments.CountryListFragment;

public class HeadlinesByCountry extends AppCompatActivity {

    private static RecyclerView recyclerView;

    private static RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    private static boolean LARGE_SCREEN_FLAG;

    private int mainFragmentContainer, detailFragmentContainer;
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
    }

    private void loadData() {
        CountryListFragment countryListFragment = new CountryListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("DataFlag", "LoadCountries");

        if (LARGE_SCREEN_FLAG) {

            bundle.putBoolean("LargeScreen", LARGE_SCREEN_FLAG);

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
