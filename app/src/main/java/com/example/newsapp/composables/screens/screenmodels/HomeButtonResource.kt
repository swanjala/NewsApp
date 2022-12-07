package com.example.newsapp.composables.screens.extensions

import android.content.Context
import com.example.newsapp.R
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_HOME
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_CATEGORY

object HomeButtonResource {

    fun getButtonItems(screenType: ScreenType, context: Context): List<HomeButtonItem> {
        return when (screenType) {
            NEWS_HOME -> {
                listOf(
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_online_articles),
                        destination = R.id.news_fragment_destination
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_saved_articles),
                        destination = R.id.saved_news_destination
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_article_category),
                        destination = R.id.news_categories
                    )
                )
            }

            NEWS_CATEGORY -> {
                listOf(
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_business),
                        destination = R.id.news_fragment_destination
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_entertainment),
                        destination = R.id.news_fragment_destination
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_general),
                        destination = R.id.news_fragment_destination
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_technology),
                        destination = R.id.news_fragment_destination
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_health),
                        destination = R.id.news_fragment_destination
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_science),
                        destination = R.id.news_fragment_destination
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_sports),
                        destination = R.id.news_fragment_destination
                    )
                )
            }
            else -> {
                emptyList()
            }
        }
    }
}
