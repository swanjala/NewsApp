package com.example.newsapp.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.composables.MainScreenComposable
//import com.example.newsapp.composables.navigation.ArticlesScreen.articleUrl
import com.example.newsapp.composables.screens.ArticleScreenComposable
import com.example.newsapp.network.model.News
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    response: LiveData<News>
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
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("url")?.let { url ->
                ArticleScreenComposable(
                    URLDecoder.decode(url, "UTF-8")
                )
            }
        }
    }
}