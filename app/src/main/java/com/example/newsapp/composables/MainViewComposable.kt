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
import com.example.newsapp.network.model.News

@Composable
fun MainViewComposable(
    response: LiveData<News>
) {
    val state by response.observeAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state?.articles?.size ?: 0) {
                state?.articles?.forEach { article ->
                    ArticleCard(article)
                }
            }
        }
    }
}
