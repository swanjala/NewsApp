package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsapp.composables.navigation.Screen
import com.example.newsapp.composables.navigation.TopBarAction
import com.example.newsapp.composables.screens.ArticleReaderScreenComposable
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.android.support.AndroidSupportInjection
import java.net.URLDecoder
import javax.inject.Inject

class ArticleReaderFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }
    private val selectedArticle: ArticleReaderFragmentArgs by navArgs()

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
                    selectedArticle.apply {
                        ArticleReaderScreenComposable(
                            sourceType = sourceType,
                            screen = Screen.ArticleReader,
                            url = URLDecoder.decode(article.url, "UTF-8"),
                            onSaveArticleClicked = ::handleSaveSelectedArticle
                        ) { topBarAction ->
                            when (topBarAction) {
                                is TopBarAction.Back -> {
                                    activity?.onBackPressed()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun handleSaveSelectedArticle() {
        viewModel.saveNewsArticle(selectedArticle.article)
        val navigationController = findNavController()
        navigationController.popBackStack()
    }
}