package com.example.newsapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.newsapp.R
import java.util.*

class MyCountryAdapter(context: Context, private val countryList: List<String>,
                       private val countryData:HashMap<String, String>):
        RecyclerView.Adapter<MyCountryAdapter.CountryViewHolder>(){

    class CountryViewHolder(countryView: View, countryData: HashMap<String, String>):
            RecyclerView.ViewHolder(countryView){

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CountryViewHolder{
        val view:View  = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_country_list, viewGroup, false)

        return CountryViewHolder(view, countryData)
    }

    override fun onBindViewHolder(countryViewHolder: CountryViewHolder, position:Int){
        val currentCountry = countryList.get(position)

        val tv_country : TextView = countryViewHolder.itemView.findViewById(R.id.tv_country)

        tv_country.setText(countryData.get(currentCountry))

        tv_country.setOnClickListener { v->
            Log.d("clicked", "clicked")
        }
    }
    override fun getItemCount(): Int{
        return countryList.size
    }

}