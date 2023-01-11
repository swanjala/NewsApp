package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.composables.screens.SelectionScreenComposable
import com.example.newsapp.composables.screens.screenmodels.HomeButtonItem
import com.example.newsapp.composables.screens.screenmodels.ScreenType
import com.example.newsapp.composables.screens.screenmodels.SourceType
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.android.support.AndroidSupportInjection

class CategoriesFragment : Fragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                NewsAppTheme {
                    SelectionScreenComposable(
                        screenType = ScreenType.NEWS_CATEGORY,
                        handleHomeSelection = ::handleCategoriesNavigation
                    )
                }
            }
        }
    }

    private fun handleCategoriesNavigation(item: HomeButtonItem, screenType: ScreenType) {
        val action = CategoriesFragmentDirections.nextAction(
            SourceType.ONLINE
        )
        findNavController().navigate(action)
    }
}