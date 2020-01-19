package com.example.newsapp.dependencies

import android.arch.lifecycle.ViewModel
import com.example.newsapp.viewmodels.MainViewModel
import com.example.newsapp.viewmodels.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.example.newsapp.viewmodels.ViewModelFactory
import android.R.string.no
import javax.inject.Provider
import dagger.MapKey
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import javax.inject.Singleton
import kotlin.reflect.KClass

class ViewModelModule {

    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    @Singleton
    internal fun viewModelFactory(providerMap: NewsViewModel): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }
}