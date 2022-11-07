package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.composables.NewsApp
import com.example.newsapp.news.NewsViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NewsApplication).initializeDaggerComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContent {
                NewsApp(
                    viewModel,
                    onSaveArticleClicked = {viewModel.saveNewsArticle()}
                )
        }
    }
}
