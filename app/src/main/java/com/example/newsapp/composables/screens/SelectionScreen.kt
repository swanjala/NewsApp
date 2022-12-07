package com.example.newsapp.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.categorySpacerHeight
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.parentRowHeight
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.parentRowWidth
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.roundedCornerDimensions
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.sharedSpacerHeight
import com.example.newsapp.composables.screens.HomeScreenComposableDimens.surfaceColumnPadding
import com.example.newsapp.composables.screens.extensions.HomeButtonResource
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun HomeScreenComposable(
    screenType: ScreenType,
    navigationController: NavController?
) {

    val buttonItems = HomeButtonResource.getButtonItems(
        screenType,
        LocalContext.current
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = surfaceColumnPadding),
            verticalArrangement = Arrangement.Center
        ) {
            navigationController?.let { navController ->
                buttonItems.forEach { item ->
                    HomeCategoryCard(
                        title = item.title,
                        destinationResource = item.destination,
                        navController
                    )
                    Spacer(Modifier.height(sharedSpacerHeight))
                }
            }
        }
    }
}

@Composable
fun HomeCategoryCard(
    title: String,
    destinationResource: Int,
    navigationController: NavController?
) {
    Row(
        modifier = Modifier
            .height(parentRowHeight)
            .width(parentRowWidth)
            .clip(shape = RoundedCornerShape(roundedCornerDimensions))
            .clickable(
                enabled = true,
                onClick = {
                    navigationController?.navigate(destinationResource)
                })
    ) {
        Column {
            Icon(
                painter = painterResource(id = R.drawable.add_news),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.width(categorySpacerHeight))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title)
        }
    }
}

object HomeScreenComposableDimens {
    val surfaceColumnPadding = 60.dp
    val sharedSpacerHeight = 10.dp
    val parentRowHeight = 80.dp
    val parentRowWidth = 300.dp
    val roundedCornerDimensions = 20.dp
    val categorySpacerHeight = 25.dp
}

@Preview(showBackground = true)
@Composable
fun HomeCategoryCardPreview() {
    NewsAppTheme {
        HomeCategoryCard(
            title = "The Autobots are back!",
            destinationResource = 0,
            navigationController = null
        )
    }
}