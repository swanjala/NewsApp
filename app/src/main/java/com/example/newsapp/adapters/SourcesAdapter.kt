package com.example.newsapp.adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsapp.Fragments.MainFragment
import com.example.newsapp.R
import com.example.newsapp.data.datamodels.Sources

class SourcesAdapter(context: Context, sourceList: List<Sources>) :
        RecyclerView.Adapter<SourcesAdapter.SourceViewHolder>() {


    var mSourceList = sourceList
    var layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SourceViewHolder {

        var view: View = layoutInflater.inflate(R.layout.card_source_data, viewGroup, false)
        return SourceViewHolder(view)

    }

    override fun onBindViewHolder(sourceViewHolder: SourceViewHolder, position: Int) {

        var currentSource: Sources = mSourceList.get(position)
        sourceViewHolder.setData(currentSource, position)
    }

    override fun getItemCount(): Int {
        return mSourceList.size

    }

    class SourceViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var tv_name: TextView = view.findViewById(R.id.tv_name)
        var tv_description: TextView = view.findViewById(R.id.tv_description)
        var tv_category: TextView = view.findViewById(R.id.tv_category)
        var tv_language: TextView = view.findViewById(R.id.tv_language)
        var tv_country: TextView = view.findViewById(R.id.tv_country)
        var mContext = view.context

        fun setData(current: Sources, position: Int) {
            tv_name.setText(current.name)
            tv_description.setText(current.description)
            var fragmentManager = (mContext as AppCompatActivity).supportFragmentManager

            var mainFragment = MainFragment()
            var bundle = Bundle()
            bundle.putString(mContext.getString(R.string.data_by_url_flag_key),
                    mContext.getString(R.string.data_by_url_value))
            bundle.putString(mContext.getString(R.string.current_url_key), current.url)

            mainFragment.arguments = bundle

            tv_description.setOnClickListener {
                var fragmentContainer: Int = R.id.fr_main_holder
                var fragmentTransaction = fragmentManager.beginTransaction()

                fragmentTransaction.replace(fragmentContainer, mainFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss()

            }

            tv_category.text = (current.category.substring(0, 1).toUpperCase()
                    + current.category.substring(1))
            tv_language.setText(current.language)
            this.tv_country.setText(current.country.toUpperCase())

        }


    }


}