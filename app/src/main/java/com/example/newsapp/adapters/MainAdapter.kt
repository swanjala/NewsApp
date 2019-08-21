package com.example.newsapp.adapters

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.newsapp.R
import com.squareup.picasso.Picasso
import data.datamodels.Articles
import kotlinx.android.synthetic.main.card_news_data.*
import kotlinx.android.synthetic.main.card_news_data.view.*
import viewmodels.NewsViewModel
import java.util.*
import kotlin.coroutines.coroutineContext

class MainAdapter(context: Context, articlesList:List<Articles>):
        RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var layoutInflater:LayoutInflater

    var mArticles = articlesList


    init {

        Collections.sort(articlesList) { o1, o2 -> o1.publishedAt.compareTo(o2.publishedAt) }

        layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {

        val view:View  = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_news_data, viewGroup, false)

        return MyViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(myViewHolder:MyViewHolder, position: Int) {

        var currentArticle = mArticles.get(position);
        myViewHolder.setData(currentArticle,position);
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    class MyViewHolder(var view: View): RecyclerView.ViewHolder(view){

        var tv_title:TextView = view.findViewById(R.id.tv_title)

        var tv_description:TextView = view.findViewById(R.id.tv_description)
        var tv_author:TextView = view.findViewById(R.id.tv_author)
        var tv_date:TextView = view.findViewById(R.id.tv_date)
        var bt_favorite:Button = view.findViewById(R.id.bt_mark_as_favorite)
        var iv_articleImage:ImageView = view.findViewById(R.id.tv_date)
        var bt_toread:Button = view.findViewById(R.id.bt_to_read)
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

            this.tv_author.setText(current.author)
            this.tv_date.setText(publishedAt.substring(0,10) + " | " + publishedAt.substring(11,19))

            if (current.favorite){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    bt_favorite.foreground = mContext.getDrawable(R.drawable.ic_thumb_up_accent_24dp)
                }

                bt_favorite.setOnClickListener {
                    var titleQuery = "%" + current.title.substring(0,10)+ "%"
                    userActionViewModel.setNewsItemsByFavorite(false,titleQuery)
                }
            } else {
                if (contrastFlag){
                    bt_favorite.foreground = mContext
                            .getDrawable(R.drawable.ic_thumb_up_high_contrast_24dp)
                } else{
                    bt_favorite.foreground = mContext.getDrawable(R.drawable.ic_thumb_up_black_24dp)
                }

                bt_favorite.setOnClickListener{
                    bt_favorite.foreground = mContext.getDrawable(R.drawable.ic_thumb_up_accent_24dp)

                    var titleQuery ="%" +  current.title.substring(0,10)+ "%"
                    userActionViewModel.setNewsItemsByFavorite(true,titleQuery)
                }
            }

            if(current.toRead){
                bt_toread.foreground = mContext.getDrawable(R.drawable.ic_library_add_accent_24dp)
                bt_toread.setOnClickListener {

                    var titleQuery = "%" + current.title.substring(0,10) + "%"
                    userActionViewModel.setNewsItemsBySetToRead(false, titleQuery)
                }
            }

            tv_description.setOnClickListener{
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(current.url))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(intent)
            }

            Picasso.with(mContext).load(current.urlToImage)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(iv_articleImage)

            this.userActionViewModel = ViewModelProviders.of(mContext as FragmentActivity)
                    .get(NewsViewModel::class.java)

        }

    }

}