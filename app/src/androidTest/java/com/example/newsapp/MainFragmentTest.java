package com.example.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.newsapp.Fragments.SourcesFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import testing.SingleFragmentActivity;
import viewmodels.NewsViewModel;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule =
            new ActivityTestRule<>(SingleFragmentActivity.class,true, true);

    @Before
    public void init() {
        EspressoTestUtil.activityLoader(activityRule);
        SourcesFragment sourcesFragment = new SourcesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sourceCategory","");
        sourcesFragment.setArguments(bundle);

        activityRule.getActivity().setFragment(sourcesFragment);
    }

    @Test
    public void testLoadSourcesView(){

        onView(withId(R.id.rv_sources_layout)).check(matches(isDisplayed()));

    }
    @NonNull
    private RecyclerViewMatcher listMatcher() {
        return new RecyclerViewMatcher(R.id.rv_news_layout);
    }
}
