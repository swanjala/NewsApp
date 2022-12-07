@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.newsapp.composables.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsapp.composables.components.ArticleCard
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.data.model.Article

@Composable
fun NewsListScreen(
    screenType: ScreenType,
    articles: List<Article>?,
    handleArticleSelected: (ScreenType,Article) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            articles?.forEach { article ->
                item {
                    ArticleCard(screenType,article, handleArticleSelected)
                }
            }
        }
    }
}
