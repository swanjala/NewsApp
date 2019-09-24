package com.example.newsapp.dependencies

import com.example.newsapp.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule{
    @Provides
    @Singleton
    internal fun provideOkHttpInterceptors():HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
                .setLevel(if(BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY else
                HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    internal fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient().Builder()
                .addInterceptor(AuthInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT.toLong(),TimeUnit.MICROSECONDS)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitClient(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
    }
}