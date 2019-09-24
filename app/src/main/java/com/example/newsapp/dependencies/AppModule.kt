package com.example.newsapp.dependencies

import android.app.Application
import android.content.Context
import com.example.newsapp.data.api.NetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.net.ContentHandler
import javax.inject.Singleton

@Module
class AppModule(private val application: Application){

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit):NetworkService{
        return retrofit.create(NetworkService::class.java)
    }
}