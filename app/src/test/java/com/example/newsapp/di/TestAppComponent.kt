package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.NewsApplication
import com.example.newsapp.dependencies.AppComponent
import com.example.newsapp.dependencies.NetworkModule
import com.example.newsapp.dependencies.NewsModule
import com.example.newsapp.dependencies.ViewModelModule
import com.example.newsapp.news.domain.NewsViewModelTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NewsModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface TestAppComponent : AppComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context): TestAppComponent
    }
    fun inject(app:TestBaseApplication)
    fun inject(test: NewsViewModelTest)
}