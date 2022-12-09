package com.example.newsapp.composables.navigation

import androidx.annotation.StringRes
import com.example.newsapp.R

sealed class Screen(
    val navigationAction: TopBarAction,
    @StringRes val labelResourceId: Int
) {
    object OnlineNews: Screen(
        navigationAction = TopBarAction.Back,
        labelResourceId = R.string.top_bar_online_news
    )
    object SavedNews: Screen(
        navigationAction = TopBarAction.Back,
        labelResourceId = R.string.top_bar_saved_news,
    )

    object ArticleReader: Screen(
        navigationAction = TopBarAction.Back,
        labelResourceId = R.string.top_bar_news_reader
    )

}