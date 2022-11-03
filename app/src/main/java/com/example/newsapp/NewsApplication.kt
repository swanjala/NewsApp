package com.example.newsapp

import android.app.Application
import androidx.activity.ComponentActivity
import com.example.newsapp.dependencies.AppComponent
import com.example.newsapp.dependencies.DaggerAppComponent

open class NewsApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
