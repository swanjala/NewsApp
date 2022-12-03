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
        // Binds application instance
        @BindsInstance
        fun application(application: Application): Builder

        // Builds the application graph
        fun build(): AppComponent
    }

    fun inject(newsApplication: NewsApplication)
}
/*
* applications
* never
* die
* near
* violent
* makers*/

/*
* Android injector : Installed to allow for the application injector
* NetworkModule : Contains logic that initializes network dependencies
* DatabaseModule : Contains logic that initiilizes databse depedencies
* NewsModule : Contains logic that exposes repository and it's dependencies
* ViewModelModule: Contains logic that exposes the viewmModels and the viewModel factory
* MainActivityModule : Allows for the application injection in the activity.
*/