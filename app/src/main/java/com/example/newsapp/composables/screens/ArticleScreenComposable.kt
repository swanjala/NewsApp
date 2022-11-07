package com.example.newsapp.composables.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.newsapp.composables.navigation.MainScreen

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
