package com.example.newsapp.dependencies


import android.content.Context
import com.example.newsapp.MainActivity
import com.example.newsapp.NewsApplication
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
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(app: NewsApplication)
    fun inject(activity: MainActivity)
}
