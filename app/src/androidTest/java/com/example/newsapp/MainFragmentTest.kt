package com.example.newsapp

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.example.newsapp.Fragments.SourcesFragment

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import testing.SingleFragmentActivity
import com.example.newsapp.viewmodels.NewsViewModel

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @Rule
    var activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Before
    fun init() {
        EspressoTestUtil.activityLoader(activityRule)
        val sourcesFragment = SourcesFragment()
        val bundle = Bundle()
        bundle.putString("sourceCategory", "")
        sourcesFragment.arguments = bundle

        activityRule.activity.setFragment(sourcesFragment)
    }

    @Test
    fun testLoadSourcesView() {

        onView(withId(R.id.rv_sources_layout)).check(matches(isDisplayed()))

    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.rv_news_layout)
    }
}
