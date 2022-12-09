package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsapp.composables.navigation.Screen
import com.example.newsapp.composables.navigation.TopBarAction
import com.example.newsapp.composables.screens.NewsListScreen
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnlineNewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    private val selectedCategory: OnlineNewsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        viewModel.run {
            updateData(selectedCategory.requestCategory)
        }
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
                            sourceType = SourceType.ONLINE,
                            screen = Screen.OnlineNews,
                            articles = it.articles,
                            handleArticleSelected = ::handleArticleSelection,
                            onNavigationActionBarClicked = ::handleNavigationBarAction
                        )
                    }
                }
            }
        }
    }

    private fun handleArticleSelection(screenType: SourceType, article: Article) {
        val navController = findNavController()
        val action = OnlineNewsFragmentDirections.nextAction(article, screenType)
        navController.navigate(action)
    }

    private fun handleNavigationBarAction(topBarAction: TopBarAction) {
        when (topBarAction) {
            is TopBarAction.Back -> {
                activity?.onBackPressed()
            }
        }
    }
}