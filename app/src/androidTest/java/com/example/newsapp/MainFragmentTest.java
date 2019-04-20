package com.example.newsapp;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.matcher.ViewMatchers;

import com.example.newsapp.Fragments.MainFragment;
import com.example.newsapp.Fragments.SourcesFragment;
import com.example.newsapp.adapters.SourcesAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import  android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import butterknife.BindView;
import data.datamodels.Sources;
import testing.SingleFragmentActivity;
import viewmodels.NewsViewModel;

@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule =
            new ActivityTestRule<>(SingleFragmentActivity.class,true, true);

    private NewsViewModel newsViewModel;
    private LiveData<List<Sources>> newsSources;
    private SourcesAdapter sourcesAdapter;

//    @BindView(R.id.rv_sources_layout)
//    RecyclerView rv_sources_layout;

    @Before
    public void init() {
        EspressoTestUtil.activityLoader(activityRule);
        SourcesFragment sourcesFragment = new SourcesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sourceCategory","");
        sourcesFragment.setArguments(bundle);
//        newsViewModel = mock(NewsViewModel.class);
//        when(newsViewModel.fetchAllSources()).thenReturn(newsSources);



        activityRule.getActivity().setFragment(sourcesFragment);
    }

    @Test
    public void testLoadList(){

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rv_sources_layout
//                .getContext());
//
//        rv_sources_layout.setLayoutManager(linearLayoutManager);
//        newsSources.observe((LifecycleOwner) this, sources -> {
//
//            new SourcesAdapter(getContext(), sources);
//            rv_sources_layout.setAdapter(sourcesAdapter);
//            sourcesAdapter.notifyDataSetChanged();
//
//        });

        onView(listMatcher().atPosition(0)).check(matches(isDisplayed()));

    }
    @NonNull
    private RecyclerViewMatcher listMatcher() {
        return new RecyclerViewMatcher(R.id.rv_news_layout);
    }
}
