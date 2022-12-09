package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.composables.screens.SelectionScreenComposable
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_HOME
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.data.model.NewsCategory
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.android.support.AndroidSupportInjection

class HomeFragment : Fragment() {
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
                    findNavController().apply {
                        SelectionScreenComposable(
                            screenType = NEWS_HOME,
                            handleHomeSelection = ::handleHomeSectionOptions
                        )
                    }
                }
            }
        }
    }

    private fun handleHomeSectionOptions(item: HomeButtonItem, screenType: ScreenType) =
        with(item) {
            val navigationController = findNavController()
            when (screenType) {
                NEWS_HOME -> {
                    when (sourceType) {
                        SourceType.ONLINE -> {
                            if (destination == R.id.news_fragment_destination) {
                                val action = CategoriesFragmentDirections.nextAction(
                                    NewsCategory.KEYWORD
                                )
                                navigationController.navigate(action)
                            }
                        }
                        else -> {
                            navigationController.navigate(destination)
                        }
                    }
                }

                ScreenType.NEWS_CATEGORY -> {
                    val action =
                        CategoriesFragmentDirections.nextAction(NewsCategory.valueOf(title.uppercase()))
                    navigationController.navigate(action)
                }
                else -> {}
            }
        }
}