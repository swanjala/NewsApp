package com.example.newsapp.composables

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.composables.navigation.NewsNavHost
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsApp(newsViewModel: NewsViewModel) {
    NewsAppTheme {
        val navController = rememberNavController()

        Scaffold {
            NewsNavHost(
                navController = navController,
                viewModel = newsViewModel
            )
        }
    }
}
