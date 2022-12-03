package com.example.newsapp.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsapp.composables.components.ArticleCard
import com.example.newsapp.composables.navigation.SavedArticlesScreen
import com.example.newsapp.news.NewsViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreenComposable(
    viewModel: NewsViewModel,
    navController: NavController
) {
    val state by viewModel.response.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state?.articles?.size ?: 0) {
                state?.articles?.forEach { article ->
                    ArticleCard(article, navController) {
                        coroutineScope.launch {
                            viewModel.saveNewsArticle(article)
                            navController.navigate(route = SavedArticlesScreen.route)
                        }
                    }
                }
            }
        }
    }
}
