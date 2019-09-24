package com.example.newsapp.data.api

import android.arch.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.IllegalArgumentException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory(){

    override fun get(returnType: Type, annotations: Array<Annotation>,
                     retrofit: Retrofit): CallAdapter<*, *>? {
        if(getRawType(returnType) != LiveData::class.java){
            return null
        }
        var observeType:Type = getParameterUpperBound(0, returnType as ParameterizedType)
        var rawObservableType = getRawType(observeType)

        if (!(observeType is ParameterizedType)) {
            throw IllegalArgumentException("Resource not parametarized")
        }
        if(rawObservableType != ApiResponse::class.java){
            throw IllegalArgumentException("Type not resource")
        }
        var bodyType = getParameterUpperBound(0,observeType as ParameterizedType)

        return LiveDataCallAdapter<Any>(bodyType)

    }

}