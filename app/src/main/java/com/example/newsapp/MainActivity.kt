package com.example.newsapp

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapp.news.NewsViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainActivity : FragmentActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector as AndroidInjector<Any>
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(
            R.id.news_nav_host_fragment
        ) as NavHostFragment
        val navController = host.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->

            val destination: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

        }
    }
}
