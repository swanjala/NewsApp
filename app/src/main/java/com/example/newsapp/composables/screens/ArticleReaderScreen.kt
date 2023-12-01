package com.example.newsapp.composables.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newsapp.R
import com.example.newsapp.composables.components.NavigationBar
import com.example.newsapp.composables.components.NavigationBarDimens
import com.example.newsapp.composables.navigation.Screen
import com.example.newsapp.composables.navigation.Screen.*
import com.example.newsapp.composables.navigation.TopBarAction
import com.example.newsapp.composables.screens.ArticleReaderScreen.buttonSize
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleReaderScreenComposable(
    sourceType: SourceType,
    screen: Screen,
    url: String,
    onSaveArticleClicked: suspend () -> Unit,
    onNavigationActionBarClicked: (TopBarAction) -> Unit
) {
    rememberCoroutineScope().apply {
        Spacer(modifier = Modifier.height(NavigationBarDimens.navigationBarHeight))
        Scaffold(
            floatingActionButton = {
                when (sourceType) {
                    SourceType.ONLINE -> {
                        FloatingActionButton(
                            modifier = Modifier.padding(bottom = 40.dp),
                            onClick = {
                                launch {
                                    onSaveArticleClicked.invoke()
                                }
                            },
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_save_news),
                                contentDescription = stringResource(R.string.action_save),
                                modifier = Modifier
                                    .size(buttonSize)
                            )
                        }
                    }
                    SourceType.LOCAL_SOURCE -> {}
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true,
            topBar = {
                NavigationBar(
                    screen = screen,
                    onNavigationActionClicked = onNavigationActionBarClicked
                )
            }
        ) {
            WebViewComposable(url)
        }
    }
}

object ArticleReaderScreen  {
    val buttonSize = 45.dp
}

@Composable
fun WebViewComposable(url: String) {
    AndroidView(
        factory = { currentContext ->
            WebView(currentContext).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenComposablePreview() {
    NewsAppTheme {
        ArticleReaderScreenComposable(
            sourceType = SourceType.ONLINE,
            screen = ArticleReader,
            url = "",
            {},
            {}
        )
    }
}