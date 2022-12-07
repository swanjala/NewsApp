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
import com.example.newsapp.R
import com.example.newsapp.composables.screens.ArticleReaderScreenComposable
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.android.support.AndroidSupportInjection
import java.net.URLDecoder
import javax.inject.Inject

class NewsDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }
    private val selectedArticle: NewsDetailsFragmentArgs by navArgs()

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
                            screenType = screenType,
                            url = URLDecoder.decode(article.url, "UTF-8"),
                            onSaveArticleClicked = ::handleSaveSelectedArticle
                        )
                    }
                }
            }
        }
    }

    private suspend fun handleSaveSelectedArticle() {
        val navigationController = findNavController()
        viewModel.saveNewsArticle(selectedArticle.article)
        navigationController.navigate(R.id.saved_news_destination)
    }
}