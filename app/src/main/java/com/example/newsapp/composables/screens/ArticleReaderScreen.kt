package com.example.newsapp.composables.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newsapp.R
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleReaderScreenComposable(
    screenType: ScreenType,
    url: String,
    onSaveArticleClicked: suspend () -> Unit
) {

    when (screenType) {
        ScreenType.ONLINE_NEWS_SCREEN -> {
            rememberCoroutineScope().apply {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            modifier = Modifier.padding(bottom = 40.dp),
                            onClick = {
                                launch {
                                    onSaveArticleClicked.invoke()
                                }
                            },
                        ) {
                            Text(text = stringResource(id = R.string.button_action_save))
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    isFloatingActionButtonDocked = true,
                ) {
                    WebViewComposable(url)
                }
            }
        }
        else -> {
            Surface(Modifier.fillMaxSize()) {
                WebViewComposable(url)
            }
        }
    }
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
            screenType = ScreenType.ONLINE_NEWS_SCREEN,
            url = "",
            {}
        )
    }
}