package com.example.newsapp.composables.screens.screenmodels

import android.content.Context
import com.example.newsapp.R
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_CATEGORY
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_HOME

object HomeButtonResource {

    fun getButtonItems(screenType: ScreenType, context: Context): List<HomeButtonItem> {
        return when (screenType) {
            NEWS_HOME -> {
                listOf(
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_online_articles),
                        iconResource = R.drawable.ic_online_news,
                        destination = R.id.news_fragment_destination,
                        sourceType = SourceType.ONLINE
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_saved_articles),
                        iconResource = R.drawable.ic_saved_news,
                        destination = R.id.saved_news_destination,
                        sourceType = SourceType.LOCAL_SOURCE
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_article_category),
                        iconResource = R.drawable.ic_article_category,
                        destination = R.id.news_categories,
                        null
                    )
                )
            }

            NEWS_CATEGORY -> {
                listOf(
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_business),
                        iconResource = R.drawable.ic_business_news,
                        destination = R.id.news_fragment_destination,
                        sourceType = SourceType.ONLINE
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_entertainment),
                        iconResource = R.drawable.ic_entertainment_news,
                        destination = R.id.news_fragment_destination,
                        sourceType = SourceType.ONLINE
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_general),
                        iconResource = R.drawable.ic_general_news,
                        destination = R.id.news_fragment_destination,
                        sourceType = SourceType.ONLINE
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_technology),
                        iconResource = R.drawable.ic_technology_news,
                        destination = R.id.news_fragment_destination,
                        sourceType = SourceType.ONLINE
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_science),
                        iconResource = R.drawable.ic_science_news,
                        destination = R.id.news_fragment_destination,
                        sourceType = SourceType.ONLINE
                    ),
                    HomeButtonItem(
                        title = context.getString(R.string.button_action_category_item_sports),
                        iconResource = R.drawable.ic_sports_news,
                        destination = R.id.news_fragment_destination,
                        sourceType = SourceType.ONLINE
                    )
                )
            }
            else -> {
                emptyList()
            }
        }
    }
}
