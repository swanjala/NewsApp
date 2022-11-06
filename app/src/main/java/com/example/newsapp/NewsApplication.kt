package com.example.newsapp

import com.example.newsapp.dependencies.AppComponent
import com.example.newsapp.dependencies.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class NewsApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return initializeDaggerComponent()
    }

    open fun initializeDaggerComponent(): AppComponent {
        val appComponent: AppComponent =
            DaggerAppComponent.factory().create(applicationContext)

        appComponent.inject(this)

        return appComponent
    }
}
