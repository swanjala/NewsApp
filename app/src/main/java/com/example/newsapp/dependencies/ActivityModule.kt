package com.example.newsapp.dependencies

import com.example.newsapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributesMainActivity(): MainActivity


}