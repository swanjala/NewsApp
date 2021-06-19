package com.example.newsapp.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.newsapp.R
import kotlinx.android.synthetic.main.fragment_search_news_items.bt_search
import kotlinx.android.synthetic.main.fragment_search_news_items.et_search_parameter

class SearchFragment : Fragment() {


    private var fragmentContainer: Int = 0

    override fun onCreateView(layoutInflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return layoutInflater
                .inflate(R.layout.fragment_search_news_items,
                        container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bt_search.setOnClickListener { v ->
            val queryValue = et_search_parameter.text.toString()
            navigationHandler(getString(R.string.navigation_handler_title_search_key), queryValue)
        }
    }

    private fun navigationHandler(valueType: String, queryValue: String) {

        val mainFragment = MainFragment()
        val bundle = Bundle()
        val fragmentManager = this.fragmentManager

        if (valueType == "Domain Search") {

            DATAFLAG = "domain_search"

            bundle.apply {
                putString("DataFlag", DATAFLAG)
                putString("QueryValue", queryValue)
            }

            mainFragment.arguments = bundle

            fragmentContainer = R.id.fr_main_holder
            fragmentManager?.beginTransaction()
                ?.replace(fragmentContainer, mainFragment)
                ?.commitAllowingStateLoss()

        } else if (valueType.trim { it <= ' ' } == getString(R.string.navigation_handler_title_search_key)) {

            DATAFLAG = "title_search"
            bundle.apply {
                putString("DataFlag", DATAFLAG)
                putString("QueryValue", queryValue)
            }

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
