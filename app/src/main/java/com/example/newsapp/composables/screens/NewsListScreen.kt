package com.example.newsapp.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.newsapp.composables.components.ArticleCard
import com.example.newsapp.composables.components.ErrorStateComposable
import com.example.newsapp.composables.components.NavigationBar
import com.example.newsapp.composables.navigation.Screen
import com.example.newsapp.composables.navigation.TopBarAction
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.data.model.Article
import com.example.newsapp.news.NewsViewModel
import kotlinx.coroutines.launch

@Composable
fun NewsListScreen(
    sourceType: SourceType,
    screen: Screen,
    viewModel: NewsViewModel,
    handleArticleSelected: (SourceType, Article) -> Unit,
    onNavigationActionBarClicked: (TopBarAction) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    with(uiState) {
        if (errorState) {
            rememberCoroutineScope().apply {
                ErrorStateComposable(onRetryClicked = {
                    launch {
                        viewModel.getAllNews()
                    }
                })
            }
        } else {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    NavigationBar(
                        screen = screen,
                        onNavigationActionClicked = onNavigationActionBarClicked
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (newsItems != null) {
                            newsItems.forEach { newsItems ->
                                item {
                                    ArticleCard(sourceType, newsItems, handleArticleSelected)
                                }
                            }
                        } else savedArticles?.forEach { articles ->
                            item {
                                ArticleCard(
                                    sourceType = sourceType,
                                    article = articles,
                                    handleArticleSelection = handleArticleSelected
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
