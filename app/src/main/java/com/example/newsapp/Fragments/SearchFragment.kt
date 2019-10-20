package com.example.newsapp.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.newsapp.R

import butterknife.BindView
import butterknife.ButterKnife

class SearchFragment : Fragment() {


    @BindView(R.id.et_search_parameter)
    internal var et_search_parameter: EditText? = null

    @BindView(R.id.bt_search)
    internal var bt_search: Button? = null
    private var fragmentContainer: Int = 0

    private var DATAFLAG = ""


    override fun onCreateView(layoutInflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = layoutInflater
                .inflate(R.layout.fragment_search_news_items,
                        container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        bt_search!!.setOnClickListener { v ->
            val queryValue = et_search_parameter!!.text.toString()
            navigationHandler(getString(R.string.navigation_handler_title_search_key), queryValue)
        }


    }

    private fun navigationHandler(valueType: String, queryValue: String) {

        val mainFragment = MainFragment()
        val bundle = Bundle()
        val fragmentManager = this.fragmentManager

        if (valueType == "Domain Search") {

            this.DATAFLAG = "domain_search"

            bundle.putString("DataFlag", DATAFLAG)
            bundle.putString("QueryValue", queryValue)
            mainFragment.arguments = bundle

            fragmentContainer = R.id.fr_main_holder
            fragmentManager!!.beginTransaction()
                    .replace(fragmentContainer, mainFragment)
                    .commitAllowingStateLoss()
        } else if (valueType.trim { it <= ' ' } == getString(R.string.navigation_handler_title_search_key)) {

            this.DATAFLAG = "title_search"

            bundle.putString("DataFlag", DATAFLAG)
            bundle.putString("QueryValue", queryValue)
            mainFragment.arguments = bundle

            fragmentContainer = R.id.fr_main_holder
            fragmentManager!!.beginTransaction()
                    .replace(fragmentContainer, mainFragment)
                    .commitAllowingStateLoss()


        } else {
            Toast.makeText(context,
                    getString(R.string.search_value_error_message),
                    Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        private var DATAFLAG: String? = null
    }
}
