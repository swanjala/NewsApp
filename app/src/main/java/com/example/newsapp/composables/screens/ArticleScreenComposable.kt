package com.example.newsapp.composables.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.ui.theme.NewsAppTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleScreenComposable(
    url: String,
    navController: NavController?
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 40.dp),
                onClick = {
                    navController?.popBackStack()
                }
            ) {
                Text(text = stringResource(id = R.string.button_action_save))
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true
    ) {
        AndroidView(
            factory = { currentContext ->
                WebView(currentContext).apply {
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenComposablePreview() {
    NewsAppTheme {
        ArticleScreenComposable(
            url = "",
            navController = null
        )
    }
}