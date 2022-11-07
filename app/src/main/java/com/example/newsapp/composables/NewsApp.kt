package com.example.newsapp.composables

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.LiveData
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.composables.navigation.NewsNavHost
import com.example.newsapp.network.model.News
import com.example.newsapp.news.domain.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsApp(newsViewModel: NewsViewModel, onSaveArticleClicked: () -> Unit) {
    NewsAppTheme {
        val navController = rememberNavController()

        Scaffold {
            NewsNavHost(
                navController = navController,
                viewModel = newsViewModel,
                onSaveArticleClicked = onSaveArticleClicked
            )
        }
    }
}
