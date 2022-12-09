package com.example.newsapp.composables.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.composables.components.NavigationBarDimens.navigationBarHeight
import com.example.newsapp.composables.navigation.Screen
import com.example.newsapp.composables.navigation.TopBarAction
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.ui.theme.CustomRed
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.android.material.R.*

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    screen: Screen,
    onNavigationActionClicked: (TopBarAction) -> Unit
) {
    TopAppBar(
        modifier = modifier.height(navigationBarHeight),
        backgroundColor = Color.Gray.copy(alpha = 0.2f),
        elevation = 0.dp,
        navigationIcon = {
            CircularIconButton(
                modifier = Modifier.padding(
                    start = NavigationBarDimens.navigationIconPaddingStart
                ),
                icon = painterResource(id = drawable.material_ic_keyboard_arrow_left_black_24dp),
            ) {
                onNavigationActionClicked(screen.navigationAction)
            }
        },
        title = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .weight(NavigationBarDimens.titleWeight)
                        .padding(end = NavigationBarDimens.titlePaddingEnd),
                    text = LocalContext.current.getString(screen.labelResourceId),
                    style = MaterialTheme.typography.subtitle2.copy(
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    )
}

object NavigationBarDimens {
    const val titleWeight = 1f
    val navigationIconPaddingStart = 6.dp
    val titlePaddingEnd = 68.dp
    val navigationBarHeight = 70.dp
}

@Preview
@Composable
fun PreviewNavigationBar() {
    NewsAppTheme() {
        Scaffold(
            topBar = {
                NavigationBar(
                    screen = Screen.OnlineNews
                ) {
                    // nothing to do here
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {}
        }
    }
}

