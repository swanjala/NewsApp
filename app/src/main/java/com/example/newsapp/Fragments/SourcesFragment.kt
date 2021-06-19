package com.example.newsapp.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.adapters.SourcesAdapter
import com.example.newsapp.viewmodels.NewsViewModel
import com.example.newsapp.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_main_sources.rv_sources_layout
import javax.inject.Inject

class SourcesFragment @Inject constructor(): Fragment() {

    private var sourcesAdapter: SourcesAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(layoutInflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_main_sources,
                container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(rv_sources_layout
                .context)

        rv_sources_layout.layoutManager = linearLayoutManager

        val viewModel = ViewModelProviders.of(this, viewModelFactory)[NewsViewModel::class.java]

        arguments?.let {

            if (it.getString(getString(R.string.source_category_flag_key)) != null && it.getString(getString(R.string.source_category_flag_key)) !== "") {

                viewModel.fetchDataByNewsCategories(it.getString(getString(R.string.source_category_flag_key)))
                    .observe(this, Observer { sources ->

                        sourcesAdapter = context?.let { it1 -> SourcesAdapter(it1, sources!!) }
                        rv_sources_layout.adapter = sourcesAdapter
                        sourcesAdapter?.notifyDataSetChanged()

                    })

            } else {

                viewModel.fetchAllSources()?.observe(this, Observer { sources ->

                    sourcesAdapter = SourcesAdapter(context!!, sources!!)
                    rv_sources_layout.adapter = sourcesAdapter
                    sourcesAdapter?.notifyDataSetChanged()

                })
            }
        }
    }
}
