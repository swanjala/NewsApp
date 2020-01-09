package com.example.newsapp.data.DataRepository.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.newsapp.data.DataRepository.repositorymodel.Objects
import com.example.newsapp.data.api.ApiResponse

abstract class ResourceHandler<ResultType, RequestType> @MainThread
constructor(private val appExecutor: AppExecutor) {
    private var result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        result.setValue(Resource.loading<ResultType>(null))
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                networkFetch(dbSource)
            } else {
                result.addSource(dbSource, { newData -> setValue(Resource.success(newData)) })
            }
        }

    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (!Objects.equals(result.getValue(), newValue)) {
            result.setValue(newValue)
        }
    }

    private fun networkFetch(dbSource: LiveData<ResultType>) {
        val apiResourseLiveData = createCall()
        result.addSource(dbSource, { newData -> setValue(Resource.loading(newData)) })
        result.addSource(apiResourseLiveData) { response ->
            result.removeSource(apiResourseLiveData)
            result.removeSource(dbSource)
        }
    }

    protected fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    @WorkerThread
    protected fun processResponse(response: ApiResponse<RequestType>): RequestType? {
        return response.body
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}

