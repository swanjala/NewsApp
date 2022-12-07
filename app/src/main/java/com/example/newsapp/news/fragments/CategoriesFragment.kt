package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.newsapp.composables.screens.HomeScreenComposable
import com.example.newsapp.composables.screens.screenmodels.ScreenType
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
                    findNavController().apply {
                        HomeScreenComposable(
                            screenType = ScreenType.NEWS_CATEGORY,
                            navigationController = this
                        )
                    }
                }
            }
        }
    }
}