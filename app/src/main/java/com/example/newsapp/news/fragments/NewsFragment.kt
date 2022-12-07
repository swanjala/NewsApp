package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.composables.screens.NewsListScreen
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                NewsAppTheme {
                    val onlineNewsState: News? by viewModel.response.observeAsState()
                    onlineNewsState?.let {
                        NewsListScreen(
                            screenType = ScreenType.ONLINE_NEWS_SCREEN,
                            articles = it.articles,
                            handleArticleSelected = ::handleArticleSelection
                        )
                    }
                }
            }
        }
    }

    private fun handleArticleSelection(screenType: ScreenType, article: Article) {
        val navController = findNavController()
        val action = NewsFragmentDirections.nextAction(article, screenType)
        navController.navigate(action)
    }
}