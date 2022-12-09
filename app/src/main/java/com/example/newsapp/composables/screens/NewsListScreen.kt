
package com.example.newsapp.composables.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsapp.composables.components.ArticleCard
import com.example.newsapp.composables.components.NavigationBar
import com.example.newsapp.composables.navigation.Screen
import com.example.newsapp.composables.navigation.TopBarAction
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.data.model.Article

@Composable
fun NewsListScreen(
    sourceType: SourceType,
    screen: Screen,
    articles: List<Article>?,
    handleArticleSelected: (SourceType, Article) -> Unit,
    onNavigationActionBarClicked: (TopBarAction) -> Unit
) {
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
                articles?.forEach { article ->
                    item {
                        ArticleCard(sourceType, article, handleArticleSelected)
                    }
                }
            }
        }
    }
}
