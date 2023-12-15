package com.example.newsapp.composables.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.composables.components.ArticleCardDimens
import com.example.newsapp.composables.components.CountryButton
import com.example.newsapp.composables.components.ErrorStateComposable
import com.example.newsapp.composables.components.InputCard
import com.example.newsapp.composables.components.Title
import com.example.newsapp.composables.components.TopCard
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.parentHorizontalPadding
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.parentRowHeight
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.roundedCornerDimensions
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.sharedSpacerHeight
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.HomeButtonResources
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsCategory
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

const val INITIAL_BATCH = 30

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectionScreenComposable(
    screenType: ScreenType,
    viewModel: NewsViewModel,
    navigationController: NavController?,// Currently unused. To implement later.
    handleHomeSelection: (HomeButtonItem, ScreenType) -> Unit,
    handleArticleSelection: (SourceType, Article) -> Unit,
    handleOnSearchClicked: (String) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsState()

    val buttonItems = HomeButtonResources.getButtonItems(
        ScreenType.NEWS_CATEGORY, LocalContext.current
    )

    val countryItems = HomeButtonResources.getCountryItems()

    val state = rememberLazyListState()

    with(uiState) {
        if (errorState) {
            rememberCoroutineScope().apply {
                ErrorStateComposable(
                    onRetryClicked = {
                        launch {
                            viewModel.getAllNews()
                        }
                    }
                )
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        when (screenType) {
            ScreenType.NEWS_HOME -> {
                Surface {
                    Column {
                        Title(titleResource = R.string.home_label_explore)

                        InputCard(onSearchClicked = handleOnSearchClicked)

                        Title(titleResource = R.string.home_label_categories)

                        LazyRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            item {
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                            buttonItems.forEach { buttonItem ->
                                item {
                                    HomeCategoryCard(
                                        item = buttonItem,
                                        screenType = ScreenType.NEWS_HOME,
                                        handleHomeSelection = handleHomeSelection
                                    )
                                }
                                item {
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                            }
                        }

                        Title(titleResource = R.string.home_label_latest_news)

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(290.dp),
                            state = state,
                            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
                        ) {
                            item {
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                            uiState.newsItems?.subList(0, INITIAL_BATCH)?.forEach { articleItem ->
                                item {
                                    TopCard(articleItem = articleItem)
                                    Spacer(modifier = Modifier.width(5.dp))
                                }
                            }
                        }

                        Title(titleResource = R.string.home_label_sources_country)

                        LazyRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            item {
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                            countryItems.forEach { countryItem ->
                                item {
                                    CountryButton(imageResource = countryItem.iconResource)
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                            }
                        }
                    }
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = parentHorizontalPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    buttonItems.forEachIndexed { index, item ->
                        HomeCategoryCard(
                            item = item,
                            screenType = screenType,
                            handleHomeSelection = handleHomeSelection
                        )
                        if (index != buttonItems.size) {
                            Spacer(Modifier.width(sharedSpacerHeight))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeCategoryCard(
    item: HomeButtonItem,
    screenType: ScreenType,
    handleHomeSelection: (HomeButtonItem, ScreenType) -> Unit
) = with(item) {
    val onRowClicked = {
        handleHomeSelection(item, screenType)
    }
    //temporarily a row, more components to be added.
    Row(
        modifier = Modifier
            .height(parentRowHeight)
            .width(100.dp)
            .clickable(
                enabled = true, onClick = onRowClicked
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(roundedCornerDimensions))
                .height(300.dp)
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = ArticleCardDimens.fontSizeLarge
            )
        }
    }
}

object HomeScreenComposableDimens {
    val sharedSpacerHeight = 10.dp
    val parentHorizontalPadding = 120.dp
    val parentRowHeight = 80.dp
    val roundedCornerDimensions = 20.dp
}

@Preview(showBackground = true)
@Composable
fun HomeCategoryCardPreview() {
    NewsAppTheme {
        HomeCategoryCard(
            item = HomeButtonItem(
                title = "The Autobots win the battle of Chicago!",
                iconResource = R.drawable.ic_online_news,
                destination = 0,
                newsSourceCategory = NewsCategory.ENTERTAINMENT.type,
                SourceType.ONLINE
            ),
            screenType = ScreenType.ONLINE_NEWS_SCREEN, handleHomeSelection = { _, _ -> })
    }
}
