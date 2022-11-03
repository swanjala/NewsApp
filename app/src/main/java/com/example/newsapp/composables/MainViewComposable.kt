package com.example.newsapp.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import com.example.newsapp.Greeting
import com.example.newsapp.network.News

@Composable
fun MainViewComposable(
    response: LiveData<News>
) {
    val state by response.observeAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn() {
            items(state?.articles?.size ?: 0) {
                state?.articles?.forEach { article ->
                    article.author?.let { it1 -> Greeting(name = it1) }
                }
            }
        }
        Greeting(name = state?.articles?.size.toString())
    }
}