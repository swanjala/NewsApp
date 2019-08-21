package com.example.newsapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.newsapp.R
import data.datamodels.Sources
import kotlinx.android.synthetic.main.card_source_data.view.*

class SourcesAdapter(context: Context, sourceList:List<Sources>):
        RecyclerView.Adapter<SourcesAdapter.SourceViewHolder>() {

    init {
        var layoutInflater = LayoutInflater.from(context)
    }

    class SourceViewHolder(view: View): RecyclerView.ViewHolder(view){

        var tv_name: TextView = view.findViewById(R.id.tv_name)
        var tv_description:TextView = view.findViewById(R.id.tv_description)
        var tv_category:TextView = view.findViewById(R.id.tv_category)
        var tv_language:TextView = view.findViewById(R.id.tv_language)
        var tv_country:TextView = view.findViewById(R.id.tv_country)

    }

}