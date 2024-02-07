package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.data.model.NewsCategory
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.news.extensions.composeView
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        onResumeByCategory()
    }

    private fun onResumeByCategory() {
        viewModel.run {
            viewModelScope.launch {
                getNewsByCategory(NewsCategory.EVERYTHING.type)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = composeView {
        /*
                NewsAppTheme {
            SelectionScreenComposable(
                screenType = ScreenType.NEWS_CATEGORY,
                viewModel = viewModel,
                handleHomeSelection = ::handleCategoriesNavigation
            )
        }
       */
    }

    private fun handleCategoriesNavigation(item: HomeButtonItem, screenType: ScreenType) {
        val action = CategoriesFragmentDirections.nextAction(
            item.newsSourceCategory ?: "news",
            SourceType.ONLINE,
        )

        findNavController().navigate(action)
    }
}