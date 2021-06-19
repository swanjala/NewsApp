package com.example.newsapp

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.newsapp.Fragments.CountryListFragment

class HeadlinesByCountry : AppCompatActivity() {

    var LARGE_SCREEN_FLAG: Boolean = false
    var mainFragmentContainer: Int = 0
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        LARGE_SCREEN_FLAG = if (findViewById<ConstraintLayout>
                (R.id.cl_country_detail) != null) true else false
        loadData()

    }

    override fun onResume() {

        super.onResume()
        LARGE_SCREEN_FLAG = if (findViewById<ConstraintLayout>
                (R.id.cl_country_detail) != null) true else false
        loadData()
    }

    fun loadData() {

        val countryListFragment = CountryListFragment()
        val bundle = Bundle()

        bundle.putString(getString(R.string.data_flag_key), getString(R.string.load_country_string))

        if (LARGE_SCREEN_FLAG) {
            bundle.putBoolean(getString(R.string.large_string_flag_key), LARGE_SCREEN_FLAG)
            mainFragmentContainer = R.id.frame_main
        } else {
            mainFragmentContainer = R.id.frame_counties_holder
        }
        countryListFragment.arguments = bundle
        fragmentManager = this.supportFragmentManager
        fragmentManager.beginTransaction().replace(mainFragmentContainer, countryListFragment)
                .commitAllowingStateLoss()
    }

}
