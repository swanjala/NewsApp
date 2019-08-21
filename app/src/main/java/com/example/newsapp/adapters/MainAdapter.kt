package com.example.newsapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.newsapp.R
import data.datamodels.Articles
import kotlinx.android.synthetic.main.card_news_data.view.*
import viewmodels.NewsViewModel
import java.util.*

class MainAdapter(context: Context, articlesList:List<Articles>):
        RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var layoutInflater:LayoutInflater

    init {

        Collections.sort(articlesList) { o1, o2 -> o1.publishedAt.compareTo(o2.publishedAt) }

        layoutInflater = LayoutInflater.from(context)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var tvTitle:TextView = view.findViewById(R.id.tv_title)

         var description:TextView = view.findViewById(R.id.tv_description)
         var tv_author:TextView = view.findViewById(R.id.tv_author)
         var tv_date:TextView = view.findViewById(R.id.tv_date)
         var iv_articleImage:ImageView = view.findViewById(R.id.tv_date)
        var publishedAt:String = ""
         lateinit var userActionViewModel: NewsViewModel
        var contrastFlag:Boolean = false



    }

}