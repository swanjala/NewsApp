package com.example.newsapp.dependencies

import com.example.newsapp.Fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule{

    @ContributesAndroidInjector
    internal abstract fun  contributesCategoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector
    internal abstract fun contributesCountryListFragment():CountryListFragment

    @ContributesAndroidInjector
    internal abstract fun contributesMainFragment() :MainFragment

    @ContributesAndroidInjector
    internal abstract fun contributesSearchFragment():SearchFragment

    @ContributesAndroidInjector
    internal abstract  fun contributesSettingsFragment():SettingsFragment

    @ContributesAndroidInjector
    internal abstract fun contributesSourcesFragment():SourcesFragment


}