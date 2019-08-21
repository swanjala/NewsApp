package com.example.newsapp.adapters

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
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
import kotlin.coroutines.coroutineContext

class MainAdapter(context: Context, articlesList:List<Articles>):
        RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var layoutInflater:LayoutInflater


    init {

        Collections.sort(articlesList) { o1, o2 -> o1.publishedAt.compareTo(o2.publishedAt) }

        layoutInflater = LayoutInflater.from(context)
    }

    class MyViewHolder(var view: View): RecyclerView.ViewHolder(view){

        var tv_title:TextView = view.findViewById(R.id.tv_title)

        var tv_description:TextView = view.findViewById(R.id.tv_description)
        var tv_author:TextView = view.findViewById(R.id.tv_author)
        var tv_date:TextView = view.findViewById(R.id.tv_date)
        var iv_articleImage:ImageView = view.findViewById(R.id.tv_date)
        var publishedAt:String = ""
        lateinit var userActionViewModel: NewsViewModel
        var contrastFlag:Boolean = false

        @RequiresApi(api= Build.VERSION_CODES.M)
        fun setData(current:Articles, position:Int){

            val mContext:Context = view.context
            publishedAt = current.publishedAt
            tv_title.setText(current.title)
            this.tv_description.setText(current.description)

            var sharedPreferences:SharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(mContext)
            contrastFlag =sharedPreferences
                    .getBoolean(mContext.getString(R.string.high_contrast_key),false)

            if(contrastFlag){
                tv_description.setTextColor(mContext.resources
                        .getColor(R.color.colorPrimaryDark, null))
                tv_description.textScaleX = 1.25f

                tv_title.setTextColor(mContext.resources
                        .getColor(R.color.colorPrimaryDark, null))
                tv_title.textScaleX = 1.25f

                tv_author.setTextColor(mContext.resources
                        .getColor(R.color.colorPrimaryDark, null))

                tv_date.setTextColor(mContext.resources
                        .getColor(R.color.colorPrimaryDark, null))

            }
        }

    }

}