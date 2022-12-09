package com.example.newsapp.dependencies

import com.example.newsapp.news.fragments.CategoriesFragment
import com.example.newsapp.news.fragments.HomeFragment
import com.example.newsapp.news.fragments.NewsDetailsFragment
import com.example.newsapp.news.fragments.OnlineNewsFragment
import com.example.newsapp.news.fragments.SavedArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesNewsFragment() : OnlineNewsFragment

    @ContributesAndroidInjector
    abstract fun contributesNewsDetailsFragment() : NewsDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributesSavedNewsFragment(): SavedArticlesFragment

    @ContributesAndroidInjector
    abstract fun contributesCategoriesFragment(): CategoriesFragment
}