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
import androidx.navigation.NavController
import com.example.newsapp.composables.components.ArticleCard
import com.example.newsapp.network.model.News
import com.example.newsapp.news.domain.NewsViewModel

@Composable
fun MainScreenComposable(
    viewModel: NewsViewModel,
    navController: NavController
) {
    val state by viewModel.response.observeAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state?.articles?.size ?: 0) {
                state?.articles?.forEach { article ->
                    ArticleCard(article, navController)
                }
            }
        }
    }
}
