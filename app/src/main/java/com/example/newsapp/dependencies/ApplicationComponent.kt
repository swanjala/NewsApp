package com.example.newsapp.dependencies

import com.example.newsapp.application.NewsApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
AppModule::class,
FragmentModule::class,
NetworkModule::class,
RoomModule::class,
ViewModelModule::class])
interface ApplicationComponent{
    fun inject(target:NewsApplication)
}