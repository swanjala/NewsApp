package com.example.newsapp.dependencies

import com.example.newsapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity():MainActivity
}
