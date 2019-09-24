package com.example.newsapp.application

import android.app.Application

class NewsApplication: Application() {

    lateinit var applicationComponent:ApplicationComponent

    override fun onCreate(){
        super.onCreate()
        applicationComponent = initDagger(this)
    }

    private fun initDagger(application:NewsApplication):ApplicationComponent =
            DaggerApplicationComponent.builder()
                    .appModule(ApplicationModule(application))
                    .build()
}