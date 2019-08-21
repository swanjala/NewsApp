package com.example.newsapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import data.datamodels.Articles
import java.util.*

class MainAdapter(context: Context, articlesList:List<Articles>):
        RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var layoutInflater:LayoutInflater

    init {

        Collections.sort(articlesList) { o1, o2 -> o1.publishedAt.compareTo(o2.publishedAt) }

        layoutInflater = LayoutInflater.from(context)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        lateinit var tvTitle:TextView

        lateinit var description:TextView
        lateinit var tv_author:TextView
        lateinit var tv_date:TextView

    }

}