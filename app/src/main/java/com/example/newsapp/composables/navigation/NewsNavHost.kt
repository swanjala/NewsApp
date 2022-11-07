package com.example.newsapp.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.composables.MainScreenComposable
import com.example.newsapp.composables.screens.ArticleScreenComposable
import com.example.newsapp.news.NewsViewModel
import java.net.URLDecoder

@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.route,
        modifier = modifier
    ) {
        composable(route = MainScreen.route) {
            MainScreenComposable(viewModel, navController)
        }
        composable(
            route = ArticlesScreen.route,
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("url")?.let { url ->
                ArticleScreenComposable(
                    URLDecoder.decode(url, "UTF-8"),
                    navController
                )
            }
        }
    }
}