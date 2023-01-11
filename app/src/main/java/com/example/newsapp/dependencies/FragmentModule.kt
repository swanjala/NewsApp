package com.example.newsapp.dependencies

import com.example.newsapp.news.fragments.CategoriesFragment
import com.example.newsapp.news.fragments.HomeFragment
import com.example.newsapp.news.fragments.ArticleReaderFragment
import com.example.newsapp.news.fragments.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesNewsFragment() : NewsListFragment

    @ContributesAndroidInjector
    abstract fun contributesNewsDetailsFragment() : ArticleReaderFragment

    @ContributesAndroidInjector
    abstract fun contributesCategoriesFragment(): CategoriesFragment
}