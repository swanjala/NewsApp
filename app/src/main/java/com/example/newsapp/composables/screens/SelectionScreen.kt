package com.example.newsapp.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.categorySpacerWidth
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.parentHorizontalPadding
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.parentRowHeight
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.roundedCornerDimensions
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.sharedSpacerHeight
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.HomeButtonResource
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun HomeScreenComposable(
    screenType: ScreenType,
    handleHomeSelection: (HomeButtonItem, ScreenType) -> Unit
) {

    val buttonItems = HomeButtonResource.getButtonItems(
        screenType,
        LocalContext.current
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = parentHorizontalPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            buttonItems.forEach { item ->
                HomeCategoryCard(
                    item = item,
                    screenType = screenType,
                    handleHomeSelection = handleHomeSelection
                )
                Spacer(Modifier.width(sharedSpacerHeight))
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
            )
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.width(categorySpacerWidth))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title)
        }
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
                "Autobots win the battle of Chicago!",
                R.drawable.ic_online_news,
                0,
                SourceType.ONLINE
            ),
            screenType = ScreenType.ONLINE_NEWS_SCREEN,
            handleHomeSelection = { _, _ -> }
        )
    }
}
