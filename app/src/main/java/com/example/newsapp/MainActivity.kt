package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.FrameLayout
import com.example.newsapp.Fragments.CategoriesFragment
import com.example.newsapp.Fragments.MainFragment
import com.example.newsapp.Fragments.SourcesFragment
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mFirebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle: Bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,
                getString(R.string.firebase_main_activity_log_item_name))
        bundle.putSerializable(FirebaseAnalytics.Param.CONTENT_TYPE,
                getString(R.string.firebase_activity_log))
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        if (findViewById<ConstraintLayout>(R.id.largeScreen) != null) {
            LARGE_SCREEN_SIZE = true

            if (DATAFLAG.equals(getString(R.string.load_all_data_flag))) {
                loadData()
            } else if (DATAFLAG.equals(getString(R.string.load_data_sources_flag))) {
                loadSourcesList()
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {

        super.onRestoreInstanceState(savedInstanceState)
        if (findViewById<ConstraintLayout>(R.id.largeScreen) != null) {
            LARGE_SCREEN_SIZE = true
            if (DATAFLAG.equals(getString(R.string.load_all_data_flag))) {
                loadData();
            } else if (DATAFLAG.equals(getString(R.string.load_data_sources_flag))) {
                loadSourcesList()
            }
        } else if (findViewById<FrameLayout>(R.id.fr_main_holder) != null) {
            LARGE_SCREEN_SIZE = false;

            if (DATAFLAG.equals(getString(R.string.load_all_data_flag))) {
                loadData()
            }
        }
        if (findViewById<ConstraintLayout>(R.id.cl_country_detail) != null) {
            LARGE_SCREEN_SIZE = true
            loadCountries()
        }
        LARGE_SCREEN_SIZE = false
    }

    fun loadData() {

        val mainFragment = MainFragment()
        val bundle: Bundle = Bundle()
        bundle.putString(resources.getString(R.string.data_flag_key), DATAFLAG)

        mainFragment.arguments = bundle

        if (LARGE_SCREEN_SIZE && findViewById<FrameLayout>(R.id.frame_articles) != null) {
            fragmentContainer = R.id.frame_articles
        } else if (findViewById<FrameLayout>(R.id.fr_main_holder) != null) {
            fragmentContainer = R.id.fr_main_holder
        }
        if (fragmentContainer.toString().equals(0)) {
            fragmentManager = this.supportFragmentManager
            fragmentManager.beginTransaction()
                    .replace(fragmentContainer, mainFragment)
                    .commitAllowingStateLoss()
        }
    }

    fun loadSourcesList() {

        DATAFLAG = getString(R.string.load_all_data_flag)

        val sourcesFragment = SourcesFragment()
        val bundle: Bundle = Bundle()
        bundle.putString("sourceCategory", "")
        sourcesFragment.arguments = bundle

        if (LARGE_SCREEN_SIZE) {
            fragmentContainer = R.id.frame_articles
        } else {
            fragmentContainer = R.id.fr_main_holder
        }
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(fragmentContainer, sourcesFragment)
                .commitAllowingStateLoss()

    }

    fun loadCountries() {
        val intent = Intent(this, HeadlinesByCountry::class.java)
        startActivity(intent)
    }

    fun loadCategories() {
        val categoriesFragment: CategoriesFragment = CategoriesFragment()
        fragmentContainer = R.id.fr_main_holder
        fragmentManager = this.supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(fragmentContainer, categoriesFragment)
                .commitAllowingStateLoss()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {
            R.id.all_news -> {
                loadData()
                true
            }
            R.id.news_sources -> {
                loadSourcesList()
                true
            }
            R.id.news_categories -> {
                loadCategories()
                true
            }
            R.id.loadFavorites -> {
                DATAFLAG = getString(R.string.load_favorites_flag);
                loadData()
                true
            }
            R.id.action_settings -> {
                val startSettingIntent = Intent(this, Settings::class.java)
                startActivity(startSettingIntent)
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        private var DATAFLAG: String = ""
        private var LARGE_SCREEN_SIZE: Boolean = false
        private var fragmentContainer: Int = 0
    }

}