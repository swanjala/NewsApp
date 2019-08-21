package com.example.newsapp.adapters

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import android.widget.RemoteViews
import com.example.newsapp.R
import data.datamodels.Articles

class NewAppWidget : AppWidgetProvider() {

    fun  updateAppWidget(context: Context, appWidgetManager:AppWidgetManager,
                                     appWidgetId:Int): Unit{
        var remoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)

//        AsyncTask<Context, Void, LiveData<List<Articles>>> () {
//        }
    }

    override fun onUpdate(context:Context,
                          appWidgetManager: AppWidgetManager,
                          appWidgetIds: Int[]){
        for (appWidgetId:Int, appWidgetIds ){
            updateAppWidget(context, appWidgetManager , appWidgetId)

        }
    }

    override fun onDeleted(context:Context, appWidgetIds:Int[]){
        for (appWidgetId:Int : appWidgetId){
            NewAppWidgetConfigureActivity.deleteTitlePref(context, appwidgetId);
        }
    }


}