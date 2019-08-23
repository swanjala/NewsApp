package com.example.newsapp.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import viewmodels.NewsViewModel

class CategoriesFragment: Fragment() {


    lateinit var newsViewModel:NewsViewModel

    override fun onCreateView(layoutInflater: LayoutInflater,
    viewgroup: ViewGroup?, savedInstanceState: Bundle?) : View {

        var view = layoutInflater.inflate(R.layout.fragment_categories,
                viewgroup, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

        var linearLayoutManager:LinearLayoutManager= LinearLayoutManager(view!!.context)



    }

}