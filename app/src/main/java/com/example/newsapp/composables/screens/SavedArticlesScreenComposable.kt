package com.example.newsapp.composables.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsapp.composables.components.ArticleCard
import com.example.newsapp.news.NewsViewModel

@Composable
fun SavedArticlesScreenComposable(
    viewModel: NewsViewModel,
    navController: NavController
) {
    val savedArticlesState by viewModel.savedArticles
        .observeAsState()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        viewModel.getSavedArticles()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()) {
            savedArticlesState?.let { articles ->
                items(articles.size) {
                    articles.forEach { article ->
                        ArticleCard(article = article, navController = navController) {
                           // navController.navigate("${ArticlesScreen.route}/${article.url}")
                        }
                    }
                }
            }
        }
    }
}