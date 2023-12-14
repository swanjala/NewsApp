package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.composables.screens.SelectionScreenComposable
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_CATEGORY
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_HOME
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.composables.screens.screenmodels.SourceType.LOCAL_SOURCE
import com.example.newsapp.composables.screens.screenmodels.SourceType.ONLINE
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsCategory
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.news.extensions.composeView
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel:NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
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
        SelectionScreenComposable(
            screenType = NEWS_HOME,
            viewModel = viewModel,
            handleArticleSelection = ::handleArticleSelection,
            handleHomeSelection = ::handleHomeSectionOptions
        )
    }

    private fun handleArticleSelection(screenType: SourceType, article: Article) {
       // val navController = findNavController()
       // val action = HomeFragmentDirections.nextAction(article, screenType)
       // navController.navigate(action)
    }

    private fun handleHomeSectionOptions(item: HomeButtonItem, screenType: ScreenType) =
        with(item) {
            val navigationController = findNavController()
            when (screenType) {
                NEWS_HOME -> {
                    when (sourceType) {
                        ONLINE -> {
                            if (destination == R.id.news_fragment_destination) {
                                navigationController.navigate(
                                    CategoriesFragmentDirections.nextAction(
                                        "",
                                        ONLINE
                                    )
                                )
                            }
                        }
                        LOCAL_SOURCE -> {
                            if (destination == R.id.news_fragment_destination) {
                                    navigationController.navigate(
                                        CategoriesFragmentDirections.nextAction(
                                            "",
                                            LOCAL_SOURCE
                                        )
                                    )
                            }
                        }
                        else -> {
                            navigationController.navigate(destination)
                        }
                    }
                }

                NEWS_CATEGORY -> {
                    val action =
                        CategoriesFragmentDirections.nextAction(
                            /*Todo
                            *  update the sources for the category as well
                            * */
                            "",
                            ONLINE
                        )
                    navigationController.navigate(action)
                }
                else -> {}
            }
        }
}