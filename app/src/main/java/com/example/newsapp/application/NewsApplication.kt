package com.example.newsapp.application

import android.app.Application
import com.example.newsapp.dependencies.AppModule
import com.example.newsapp.dependencies.ApplicationComponent
import com.example.newsapp.dependencies.DaggerApplicationComponent

class NewsApplication: Application() {

    lateinit var applicationComponent:ApplicationComponent

    override fun onCreate(){
        super.onCreate()
        applicationComponent = initDagger(this)
    }

    private fun initDagger(application:NewsApplication): ApplicationComponent =
            DaggerApplicationComponent.builder()
                    .appModule(AppModule(application))
                    .build()
}