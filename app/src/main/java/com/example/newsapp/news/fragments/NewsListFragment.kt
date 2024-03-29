package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsapp.composables.navigation.Screen
import com.example.newsapp.composables.navigation.TopBarAction
import com.example.newsapp.composables.screens.NewsListScreen
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.composables.screens.screenmodels.SourceType.*
import com.example.newsapp.data.model.Article
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.news.extensions.composeView
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    private val sourcesArguments: NewsListFragmentArgs? by navArgs()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()

        viewModel.run {
            viewModelScope.launch {
                sourcesArguments?.let {
                    when (it.sourceType) {
                        ONLINE -> {
                            getNewsByCategory(it.sourceCategory)
                        }
                        LOCAL_SOURCE -> {
                            getSavedArticles()
                        }
                    }
                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = composeView {
        NewsListScreen(
            sourceType = ONLINE,
            screen = Screen.NewsList,
            viewModel = viewModel,
            handleArticleSelected = ::handleArticleSelection,
            onNavigationActionBarClicked = ::handleNavigationBarAction,
        )
    }

    private fun handleArticleSelection(screenType: SourceType, article: Article) {
        val navController = findNavController()
        val action = NewsListFragmentDirections.nextAction(article, screenType)
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