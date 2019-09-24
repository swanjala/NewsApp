package com.example.newsapp.dependencies

import com.example.newsapp.MainActivity
import com.example.newsapp.application.NewsApplication

interface ApplicationComponent{
    fun inject(target:NewsApplication)
}