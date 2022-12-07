package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newsapp.composables.screens.NewsListScreen
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedArticlesFragment : Fragment() {
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
        val navController = findNavController()

        lifecycleScope.launch {
            viewModel.getSavedArticles()
        }

        return ComposeView(requireContext()).apply {
            viewModel.savedArticles.observe(viewLifecycleOwner) { articles ->
                setContent {
                    NewsAppTheme {
                        NewsListScreen(
                            articles = articles,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}