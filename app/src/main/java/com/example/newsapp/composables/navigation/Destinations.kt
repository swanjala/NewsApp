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

object SavedArticlesScreen: Destinations {
    override val route = ROUTE_SAVED_ARTICLES
}
private const val ROUTE_MAIN = "main"
private const val ROUTE_ARTICLE = "article/{url}"
private const val ROUTE_SAVED_ARTICLES = "savedArticles"