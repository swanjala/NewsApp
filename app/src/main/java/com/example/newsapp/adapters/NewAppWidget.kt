package com.example.newsapp.adapters

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.RemoteViews
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.squareup.picasso.Picasso
import data.database.AppDatabase
import data.datamodels.Articles

class NewAppWidget : AppWidgetProvider() {

    override fun onUpdate(context:Context,
                          appWidgetManager: AppWidgetManager,
                          appWidgetIds: IntArray){
       for (appWidgetId in appWidgetIds){
           updateAppWidget(context, appWidgetManager, appWidgetId)
       }
    }

    override fun onDeleted(context:Context, appWidgetIds:IntArray){

        for (appWidgetId in appWidgetIds){
            NewAppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId)
        }
    }

    companion object{
        internal fun updateAppWidget(context:Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId:Int){
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)

            object: AsyncTask<Context, Void, LiveData<List<Articles>>>(){
                override fun doInBackground(vararg context:Context): LiveData<List<Articles>>{
                    val db= AppDatabase.getDatabase(context[0])

                    return db.articleAccessObject().fetchAllData()
                }

                override fun onPostExecute(List:LiveData<List<Articles>>?){
                    super.onPostExecute(List)
                    List?.observeForever{
                        articlesList ->
                        val dataIndex = 0
                        views.setTextViewText(R.id.tv_news_title,
                                articlesList?.get(dataIndex)?.title)
                        views.setTextViewText(R.id.tv_description,
                                articlesList?.get(dataIndex)?.description)

                        views.setTextViewText(R.id.tv_source_widget,
                                articlesList?.get(dataIndex)?.publishedAt)

                        val intent = Intent(context, MainActivity::class.java)

                        val pendingIntent = PendingIntent.getActivity(context, 0,
                                intent, PendingIntent.FLAG_UPDATE_CURRENT)
                        views.setOnClickPendingIntent(R.id.bt_open_app, pendingIntent)
                        if(articlesList!!.get(dataIndex).urlToImage != null){
                            Picasso.with(context)
                                    .load(articlesList.get(dataIndex).urlToImage)
                                    .into(views, R.id.imageView, intArrayOf(appWidgetId))
                        }

                        appWidgetManager.updateAppWidget(appWidgetId, views)

                    }

                }
            }.execute(context)
        }

    }


}