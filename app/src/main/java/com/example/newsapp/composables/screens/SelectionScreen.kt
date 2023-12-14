package com.example.newsapp.composables.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.composables.components.ArticleCard
import com.example.newsapp.composables.components.TopCard
import com.example.newsapp.composables.components.ErrorStateComposable
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.categorySpacerWidth
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.parentHorizontalPadding
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.parentRowHeight
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.roundedCornerDimensions
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.sharedSpacerHeight
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.HomeButtonResource
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsCategory
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectionScreenComposable(
    screenType: ScreenType,
    viewModel: NewsViewModel,
    handleHomeSelection: (HomeButtonItem, ScreenType) -> Unit,
    handleArticleSelection: (SourceType, Article) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsState()

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

    val buttonItems = HomeButtonResource.getButtonItems(
        screenType,
        LocalContext.current
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        when (screenType) {
            ScreenType.NEWS_HOME -> {
                Surface {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(290.dp),
                            state = state,
                            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
                        ) {
                            uiState.newsItems?.subList(0, 30)?.forEach { articleItem ->
                                item {
                                    TopCard(articleItem = articleItem)
                                    Spacer(modifier = Modifier.width(5.dp))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            if (uiState.newsItems != null) {
                                val itemsSize = uiState.newsItems?.size ?: 0
                                val otherItems = uiState.newsItems?.subList(31, itemsSize)

                                item {
                                    otherItems?.forEach { articleItem ->
                                        ArticleCard(
                                            sourceType = SourceType.ONLINE,
                                            article = articleItem,
                                            handleArticleSelection = handleArticleSelection
                                        )
                                    }
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

    Row(
        modifier = Modifier
            .height(parentRowHeight)
            .width(IntrinsicSize.Max)
            .clip(shape = RoundedCornerShape(roundedCornerDimensions))
            .clickable(
                enabled = true,
                onClick = onRowClicked
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = "",
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(categorySpacerWidth))

        Text(text = title)
    }
}

object HomeScreenComposableDimens {
    val sharedSpacerHeight = 10.dp
    val parentHorizontalPadding = 120.dp
    val parentRowHeight = 80.dp
    val roundedCornerDimensions = 20.dp
    val categorySpacerWidth = 10.dp
}

@Preview(showBackground = true)
@Composable
fun HomeCategoryCardPreview() {
    NewsAppTheme {
        HomeCategoryCard(
            item = HomeButtonItem(
                "The Autobots win the battle of Chicago!",
                R.drawable.ic_online_news,
                0,
                newsSourceCategory = NewsCategory.ENTERTAINMENT.type,
                SourceType.ONLINE
            ),
            screenType = ScreenType.ONLINE_NEWS_SCREEN,
            handleHomeSelection = { _, _ -> }
        )
    }
}
