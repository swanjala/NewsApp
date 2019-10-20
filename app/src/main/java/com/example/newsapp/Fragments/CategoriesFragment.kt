package com.example.newsapp.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsCategoryAdapter
import com.example.newsapp.viewmodels.NewsViewModel

class CategoriesFragment : Fragment() {


    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(layoutInflater: LayoutInflater,
                              viewgroup: ViewGroup?, savedInstanceState: Bundle?): View {

        var view = layoutInflater.inflate(R.layout.fragment_categories,
                viewgroup, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(view!!.context)

        var recyclerView: RecyclerView = view!!.findViewById(R.id.rv_news_categories)

        recyclerView.layoutManager = linearLayoutManager

        newsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel::class.java)

        newsViewModel.fetchNewsCategories()?.observe(this, Observer {
            val newsCategoryAdatper = NewsCategoryAdapter(context!!, it!!)
            recyclerView.adapter = newsCategoryAdatper
            newsCategoryAdatper.notifyDataSetChanged()

        })

    }

}