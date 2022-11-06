package com.example.newsapp.composables.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val route: String
    val url: String?
}

object MainScreen: Destinations {
    override val route = ROUTE_MAIN
    override val url = null
}

object ArticlesScreen: Destinations {
    override val route = ROUTE_ARTICLE
    override val url :String? = null
    const val articleUrl = "article_url"
    val articleUrlArg = "$route/{$articleUrl}"

    val arguments = listOf(
        navArgument(articleUrlArg) {type = NavType.StringType}
    )
}

private const val ROUTE_MAIN = "main"
private const val ROUTE_ARTICLE = "article"