package com.example.newsapp.dependencies

import com.example.newsapp.viewmodels.MainViewModel
import com.example.newsapp.viewmodels.NewsViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule{

    @Binds
    abstract fun bindNewsViewModel(mainViewModel: MainViewModel)
}