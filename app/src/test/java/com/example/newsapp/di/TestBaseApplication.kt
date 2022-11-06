package com.example.newsapp.di

import com.example.newsapp.NewsApplication
import com.example.newsapp.dependencies.AppComponent

class TestBaseApplication: NewsApplication() {

    override fun initializeDaggerComponent(): AppComponent {
        val component: TestAppComponent = DaggerTestAppComponent.factory()
            .create(this)
        component.inject(this)
        return component
    }

}