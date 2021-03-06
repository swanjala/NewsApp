package com.example.newsapp.Fragments

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.adapters.CountryAdapter
import com.example.newsapp.data.datamodels.CountryConstants
import com.example.newsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_country_list.fb_back_to_main
import kotlinx.android.synthetic.main.fragment_country_list.rv_country_list
import java.util.*

class CountryListFragment : Fragment() {

    private val newsViewModel: NewsViewModel? = null
    private var countryMap: HashMap<String, String>? = null

    private val countryConstants = CountryConstants()

    override fun onCreateView(layoutInflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view = layoutInflater.inflate(R.layout.fragment_country_list,
                container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(rv_country_list.context)

        if (countryMap != null) {
            countryMap = countryConstants.countryListData()
        }

        rv_country_list.layoutManager = linearLayoutManager


        newsViewModel!!.fetchAllCountries()?.observe(this, Observer { countries ->
            val countryAdapter = CountryAdapter(activity!!, countries!!, countryMap!!)
            rv_country_list!!.adapter = countryAdapter
            countryAdapter.notifyDataSetChanged()

        })

        fb_back_to_main.setOnClickListener { v ->
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
