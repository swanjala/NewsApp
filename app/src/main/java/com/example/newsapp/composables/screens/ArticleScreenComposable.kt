package com.example.newsapp.composables.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.newsapp.composables.navigation.Destinations
import com.example.newsapp.composables.navigation.MainScreen
import com.example.newsapp.news.domain.NewsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleScreenComposable(url: String, navController: NavController) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.padding(bottom = 40.dp), onClick = {
                navController.navigate(MainScreen.route)
            }) {
                Text(text = "Save")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true
    ) {
        AndroidView(factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        })
    }
}
