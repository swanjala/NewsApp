package com.example.newsapp.dependencies

import com.example.newsapp.news.fragments.HomeFragment
import com.example.newsapp.news.fragments.NewsDetailsFragment
import com.example.newsapp.news.fragments.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesNewsFragment() : NewsFragment

    @ContributesAndroidInjector
    abstract fun contributesNewsDetailsFragment() : NewsDetailsFragment
}