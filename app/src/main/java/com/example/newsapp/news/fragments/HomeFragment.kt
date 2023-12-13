package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.composables.screens.SelectionScreenComposable
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_CATEGORY
import com.example.newsapp.composables.screens.screenmodels.ScreenType.NEWS_HOME
import com.example.newsapp.composables.screens.screenmodels.SourceType.LOCAL_SOURCE
import com.example.newsapp.composables.screens.screenmodels.SourceType.ONLINE
import com.example.newsapp.news.extensions.composeView
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
    ) = composeView {
        SelectionScreenComposable(
            screenType = NEWS_HOME,
            handleHomeSelection = ::handleHomeSectionOptions
        )
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