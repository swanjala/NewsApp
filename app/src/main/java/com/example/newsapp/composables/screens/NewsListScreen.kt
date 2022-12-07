@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.newsapp.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.composables.components.ArticleCard
import com.example.newsapp.data.model.Article
import com.example.newsapp.news.fragments.NewsFragmentDirections

@Composable
fun NewsListScreen(
    articles: List<Article>?,
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            articles?.forEach { article ->
                item {
                    ArticleCard(article) {
                        val action = NewsFragmentDirections.nextAction(article)
                        navController.navigate(action)
                    }
                }
            }
        }
    }
}
