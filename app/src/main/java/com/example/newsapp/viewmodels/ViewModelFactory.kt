package com.example.newsapp.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import java.lang.RuntimeException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val mNewsViewModel: NewsViewModel) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel
        if (modelClass == NewsViewModel::class.java) {
            viewModel = mNewsViewModel
        } else {
            throw RuntimeException("unsupported view model class: $modelClass")
        }
        return viewModel as T
    }


}