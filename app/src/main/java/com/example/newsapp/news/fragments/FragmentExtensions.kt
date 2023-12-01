package com.example.newsapp.news.fragments

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.newsapp.ui.theme.NewsAppTheme

fun Fragment.composeView(content: @Composable ()-> Unit) = ComposeView(requireContext()).apply {
    setContent {
        NewsAppTheme {
            content()
        }
    }
}
