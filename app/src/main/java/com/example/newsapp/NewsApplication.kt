package com.example.newsapp

import android.app.Activity
import android.app.Application
import com.example.newsapp.dependencies.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
open class NewsApplication : Application(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun androidInjector(): AndroidInjector<Any>?  {
       return dispatchingAndroidInjector as
                AndroidInjector<Any>?
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}
