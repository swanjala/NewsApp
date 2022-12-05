package com.example.newsapp.news.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.composables.screens.ArticleScreenComposable
import dagger.android.support.AndroidSupportInjection
import java.net.URLDecoder

class NewsDetailsFragment : Fragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val url = arguments?.getString("articleUrl")
        val navigationController = findNavController()
        return ComposeView(requireContext()).apply {
            setContent {
                url?.let {
                    ArticleScreenComposable(
                        url = URLDecoder.decode(it, "UTF-8"),
                        navController = navigationController
                    )
                }
            }
        }
    }
}