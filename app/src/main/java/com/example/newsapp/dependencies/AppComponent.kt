package com.example.newsapp.dependencies


import android.app.Application
import com.example.newsapp.NewsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        DataStoreModule::class,
        ViewModelModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(newsApplication: NewsApplication)
}
