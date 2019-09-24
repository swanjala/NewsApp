package com.example.newsapp.dependencies

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.api.AuthInterceptor
import com.example.newsapp.utils.Constants.Companion.BASE_URL
import com.example.newsapp.utils.Constants.Companion.CONNECTION_TIMEOUT
import com.example.newsapp.utils.Constants.Companion.WRITE_TIMEOUT
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
        return OkHttpClient().newBuilder()
                .addInterceptor(AuthInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
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
                .build()
    }
}