package com.example.newsapp.Fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.newsapp.R

import butterknife.BindView
import butterknife.ButterKnife

import com.example.newsapp.adapters.SourcesAdapter
import com.example.newsapp.viewmodels.NewsViewModel

class SourcesFragment : Fragment() {

    @BindView(R.id.rv_sources_layout)
    internal var rv_sources_layout: RecyclerView? = null

    private var sourcesAdapter: SourcesAdapter? = null
    private var model: NewsViewModel? = null

    override fun onCreateView(layoutInflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_main_sources,
                container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(rv_sources_layout!!
                .context)

        rv_sources_layout!!.layoutManager = linearLayoutManager

        this.model = ViewModelProviders
                .of(this)
                .get(NewsViewModel::class.java)

        if (arguments!!.getString(getString(R.string.source_category_flag_key)) != null && arguments!!.getString(getString(R.string.source_category_flag_key)) !== "") {

            model!!.fetchDataByNewsCategories(arguments!!
                    .getString(getString(R.string.source_category_flag_key)))
                    .observe(this, { sources ->

                        sourcesAdapter = SourcesAdapter(context!!, sources)
                        rv_sources_layout!!.adapter = sourcesAdapter
                        sourcesAdapter!!.notifyDataSetChanged()

                    })

        } else {

            model!!.fetchAllSources().observe(this, { sources ->

                sourcesAdapter = SourcesAdapter(context!!, sources)
                rv_sources_layout!!.adapter = sourcesAdapter
                sourcesAdapter!!.notifyDataSetChanged()

            })
        }


    }
}
