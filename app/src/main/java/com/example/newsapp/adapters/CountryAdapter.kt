package com.example.newsapp.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.example.newsapp.Fragments.MainFragment
import com.example.newsapp.R

class CountryAdapter(val context: Context,val countryList:List<String>,
                     countryData:HashMap<String, String>)
    : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    lateinit var layoutInflator: LayoutInflater


    var mContext: Context = context


    var mCountryData: HashMap<String, String> = countryData


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CountryViewHolder {

        val view: View = layoutInflator
                .inflate(R.layout.card_country_list, viewGroup, false)
        val countryViewHolder: CountryAdapter.CountryViewHolder = CountryViewHolder(view, mCountryData)
        return countryViewHolder
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        var currentCountry:String = countryList.get(position)
        holder.setData(currentCountry, position)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    class CountryViewHolder(countryView: View, countryData: HashMap<String, String>)
        : RecyclerView.ViewHolder(countryView) {

        lateinit var tv_country: TextView
        lateinit var fragmentTransaction: FragmentTransaction

        var fragmentContainer: Int = 0
        var mCountryData = countryData

        fun setData(currentCountry: String, position: Int) {
            this.tv_country.setText(mCountryData.get(currentCountry.toUpperCase()))


            tv_country.setOnClickListener {
                val fragmentManager: FragmentManager = (this as AppCompatActivity).supportFragmentManager

                val mainFragment = MainFragment()

                val bundle = Bundle()

                bundle.putString(applicationContext.getString(R.string.data_flag_key),
                        applicationContext.getString(R.string.articles_by_country_flag))

                var country = mCountryData.get(currentCountry.toUpperCase())
                bundle.putString(applicationContext.getString(R.string.country_data_flag), country)

                fragmentContainer = if (findViewById<FrameLayout>(R.id.frame_counties_holder) != null)
                    R.id.frame_counties_holder else R.id.frame_detail

                mainFragment.arguments = bundle
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(fragmentContainer, mainFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
            }
        }

    }
}