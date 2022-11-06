package com.example.newsapp.composables.navigation

interface Destinations {
    val route: String
}

object MainScreen: Destinations {
    override val route = ROUTE_MAIN
}

object ArticlesScreen: Destinations {
    override val route = ROUTE_ARTICLE
}

private const val ROUTE_MAIN = "main"
private const val ROUTE_ARTICLE = "article/{url}"