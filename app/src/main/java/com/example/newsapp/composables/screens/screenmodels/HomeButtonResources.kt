package com.example.newsapp.composables.screens.screenmodels

import android.content.Context
import com.example.newsapp.R
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_CATEGORY
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_HOME
import com.example.newsapp.data.model.Country
import com.example.newsapp.data.model.NewsCategory

object HomeButtonResource {

    fun getButtonItems(screenType: ScreenType, context: Context): List<HomeButtonItem> =
        with(context) {
            return when (screenType) {
                NEWS_HOME -> {
                    listOf(
                        HomeButtonItem(
                            title = getString(R.string.button_action_online_articles),
                            iconResource = R.drawable.ic_online_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = NewsCategory.EVERYTHING.type,
                            sourceType = SourceType.ONLINE
                        ),
                        HomeButtonItem(
                            title = getString(R.string.button_action_saved_articles),
                            iconResource = R.drawable.ic_saved_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = null,
                            sourceType = SourceType.LOCAL_SOURCE
                        ),
                        HomeButtonItem(
                            title = getString(R.string.button_action_article_category),
                            iconResource = R.drawable.ic_article_category,
                            newsSourceCategory = null,
                            destination = R.id.news_categories,
                            sourceType = null
                        )
                    )
                }

                NEWS_CATEGORY -> {
                    listOf(
                        HomeButtonItem(
                            title = getString(R.string.button_action_category_item_business),
                            iconResource = R.drawable.ic_business_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = NewsCategory.BUSINESS.type,
                            sourceType = SourceType.ONLINE
                        ),
                        HomeButtonItem(
                            title = getString(R.string.button_action_category_item_entertainment),
                            iconResource = R.drawable.ic_entertainment_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = NewsCategory.ENTERTAINMENT.type,
                            sourceType = SourceType.ONLINE
                        ),
                        HomeButtonItem(
                            title = getString(R.string.button_action_category_item_general),
                            iconResource = R.drawable.ic_general_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = NewsCategory.GENERAL.type,
                            sourceType = SourceType.ONLINE
                        ),
                        HomeButtonItem(
                            title = getString(R.string.button_action_category_item_health),
                            iconResource = R.drawable.ic_general_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = NewsCategory.HEALTH.type,
                            sourceType = SourceType.ONLINE
                        ),
                        HomeButtonItem(
                            title = getString(R.string.button_action_category_item_technology),
                            iconResource = R.drawable.ic_technology_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = NewsCategory.TECHNOLOGY.type,
                            sourceType = SourceType.ONLINE
                        ),
                        HomeButtonItem(
                            title = getString(R.string.button_action_category_item_science),
                            iconResource = R.drawable.ic_science_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = NewsCategory.SCIENCE.type,
                            sourceType = SourceType.ONLINE
                        ),
                        HomeButtonItem(
                            title = getString(R.string.button_action_category_item_sports),
                            iconResource = R.drawable.ic_sports_news,
                            destination = R.id.news_fragment_destination,
                            newsSourceCategory = NewsCategory.SPORTS.type,
                            sourceType = SourceType.ONLINE
                        )
                    )
                }

                else -> {
                    emptyList()
                }
            }
        }

    fun getCountryButtons() {
        listOf(
            CountryButton(
                iconResource = R.drawable.flag_norway,
                countryName = Country.NORWAY.name,
                countryCode = Country.NORWAY.countryCode
            ),
            CountryButton(
                iconResource = R.drawable.flag_australia,
                countryName = Country.AUSTRALIA.name,
                countryCode = Country.AUSTRALIA.countryCode
            ),
            CountryButton(
                iconResource = R.drawable.flag_canada,
                countryName = Country.CANADA.name,
                countryCode = Country.CANADA.countryCode
            ),
            CountryButton(
                iconResource = R.drawable.flag_france,
                countryName = Country.FRANCE.name,
                countryCode = Country.FRANCE.countryCode
            ),
            CountryButton(
                iconResource = R.drawable.flag_germany,
                countryName = Country.GERMANY.name,
                countryCode = Country.GERMANY.countryCode
            ),
            CountryButton(
                iconResource = R.drawable.flag_sweden,
                countryName = Country.SWEDEN.name,
                countryCode = Country.SWEDEN.countryCode
            ),
            CountryButton(
                iconResource = R.drawable.flag_uk,
                countryName = Country.UK.name,
                countryCode = Country.AUSTRALIA.countryCode
            ),
            CountryButton(
                iconResource = R.drawable.flag_australia,
                countryName = Country.AUSTRALIA.name,
                countryCode = Country.AUSTRALIA.countryCode
            ),
            CountryButton(
                iconResource = R.drawable.flag_australia,
                countryName = Country.AUSTRALIA.name,
                countryCode = Country.AUSTRALIA.countryCode
            ),
        )

    }
}
