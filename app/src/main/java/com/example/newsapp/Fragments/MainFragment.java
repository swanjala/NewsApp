package com.example.newsapp.Fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.newsapp.R;
import com.example.newsapp.adapters.MainAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import viewmodels.NewsViewModel;

public class MainFragment extends Fragment {

    @BindView(R.id.fb_news_items)
    FloatingActionButton fb_news_items;

    @BindView(R.id.fb_news_back)
    FloatingActionButton fb_news_back;

    @BindView(R.id.rv_news_layout)
    RecyclerView rv_news_layout;

    @BindView(R.id.adView)
    AdView mAdView;

    private MainAdapter mainAdapter;

    private NewsViewModel model;

    private static String DATAFLAG;

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = layoutInflater.inflate(R.layout.fragment_main_news,
                container,
                false);
        ButterKnife.bind(this, view);
        return view;

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        MobileAds.initialize(getContext(),
                getString(R.string.mobile_ads_id));

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.bringToFront();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rv_news_layout
                .getContext());

        rv_news_layout.setLayoutManager(linearLayoutManager);


        if (getArguments() != null && getArguments()
                .getString(getString(R.string.main_fragment_get_data_flag_key)) != null){

            this.model= ViewModelProviders
                    .of(this)
                    .get(NewsViewModel.class);

            DATAFLAG = getArguments()
                    .getString(getString(R.string.main_fragment_get_data_flag_key));

            if (DATAFLAG.equals(getString(R.string.all_data_flag))){
                model.fetchAllArticlesNoQuery().observe(this, articlesList ->{
                    mainAdapter = new MainAdapter(getContext(),articlesList);
                    rv_news_layout.setAdapter(mainAdapter);
                    mainAdapter.notifyDataSetChanged();

                });
            } else if (DATAFLAG.equals(getString(R.string.load_favorites_flag))){
                model.fetchDataByFavorite().observe(this, articlesList -> {
                    mainAdapter = new MainAdapter(getContext(), articlesList);
                    rv_news_layout.setAdapter(mainAdapter);
                    mainAdapter.notifyDataSetChanged();
                });
            } else if(DATAFLAG.equals(getString(R.string.load_set_to_read))){
                model.fetchDataBySetToRead().observe(this, articlesList -> {
                    mainAdapter = new MainAdapter(getContext(), articlesList);
                    rv_news_layout.setAdapter(mainAdapter);
                    mainAdapter.notifyDataSetChanged();
                });
            } else  if(DATAFLAG.equals(getString(R.string.main_fragment_domain_search_value))){

                model.fetchArticlesByDomain(getArguments()
                        .getString(getString(R.string.main_fragment_query_value)))
                        .observe(this, articlesList -> {
                        mainAdapter = new MainAdapter(getContext(), articlesList);
                        rv_news_layout.setAdapter(mainAdapter);
                        mainAdapter.notifyDataSetChanged();
                });
            }
            else  if(DATAFLAG.equals(getString(R.string.title_search_value))){

                model.fetchArticlesByTitle(getArguments()
                        .getString(getString(R.string.main_fragment_query_value)))
                        .observe(this, articlesList -> {
                            mainAdapter = new MainAdapter(getContext(), articlesList);
                            rv_news_layout.setAdapter(mainAdapter);
                            mainAdapter.notifyDataSetChanged();
                        });
            }
            else if(DATAFLAG.equals(getString(R.string.main_fragment_data_by_url_value))){
                model.fetchArticlesByDomain(getArguments()
                        .getString(getString(R.string.current_url_key)))
                        .observe(this,articlesList -> {
                            mainAdapter = new MainAdapter(getContext(), articlesList);

                            rv_news_layout.setAdapter(mainAdapter);
                            mainAdapter.notifyDataSetChanged();
                        });
            }

            else if(getArguments()
                    .getString(getString(R.string.main_fragment_get_data_flag_key))
                    .equals(getString(R.string.articles_by_country_value))){

                String countryName = getArguments()
                        .getString(getString(R.string.country_data_flag));

                model.fetchArticlesByCountry(countryName).observe(this, articlesList -> {

                    mainAdapter = new MainAdapter(getContext(),
                            articlesList);
                    rv_news_layout.setAdapter(mainAdapter);
                    mainAdapter.notifyDataSetChanged();
                });


            }
        }

        fb_news_items.setOnClickListener(v -> {
             int fragmentContainer;
             FragmentTransaction fragmentTransaction;

            SearchFragment searchFragment  = new SearchFragment();
            fragmentContainer = R.id.fr_main_holder;
            assert getFragmentManager() != null;
            fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.replace(fragmentContainer,searchFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();

        });

        FragmentManager fragmentManager = getFragmentManager();

        assert fragmentManager != null;
        if (fragmentManager.getBackStackEntryCount()> 0) {

            fb_news_back.setVisibility(View.VISIBLE);

            fb_news_back.setOnClickListener(v -> {
                    fragmentManager.popBackStack();

            });

        }

    }
}
