package com.example.newsapp.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.composables.MainScreenComposable
import com.example.newsapp.composables.screens.ArticleScreenComposable
import com.example.newsapp.network.model.News

@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    response: News
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.route,
        modifier = modifier
    ) {
        composable(route = MainScreen.route) {
            MainScreenComposable(response, navController)
        }
        composable(
            route = ArticlesScreen.route,
            arguments = ArticlesScreen.arguments
        ) {
            val articleUrlLink = it.arguments?.getString(ArticlesScreen.articleUrlArg)
            articleUrlLink?.let { it1 -> ArticleScreenComposable(it1) }
        }
    }
}