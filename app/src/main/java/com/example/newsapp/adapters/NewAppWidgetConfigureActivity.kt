package com.example.newsapp.adapters

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.newsapp.R

class NewAppWidgetConfigureActivity : Activity() {


    internal var mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

     lateinit var mAppWidgetText:EditText

    var mOnClickListener = View.OnClickListener {

        fun onClick(v:View){
            val context = this
            val widgetText = mAppWidgetText.text.toString()

            saveTitlePref(context, mAppWidgetId, widgetText )

            val appWidgetManager = AppWidgetManager.getInstance(context)
            NewAppWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId)

            val resultValue = Intent()
            setResult(RESULT_OK, resultValue)
            finish()
        }
    }
    fun NewAppWidgetConfigureActivity(){
        super.finish()
    }

    companion object{

       private var PREFS_NAME  = "com.example.newsapp.adapter.NewAppWidget"

        private var PREF_PREFIX_KEY = "appwidget_"

        internal fun saveTitlePref(context: Context, appWidgetId:Int, text:String){

            val prefs:SharedPreferences.Editor = context.getSharedPreferences(PREFS_NAME, 0).edit()
            prefs.putString(PREF_PREFIX_KEY + appWidgetId, text)
            prefs.apply()
        }

       internal fun loadTitlePref(context:Context, appWidgetId:Int):String{
           val sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
           val titleValue = sharedPreferences.getString(PREF_PREFIX_KEY + appWidgetId, null)

            if(titleValue != null){
                return titleValue
            } else {
                return context.getString(R.string.appwidget_text)
            }
        }

      internal  fun deleteTitlePref(context:Context, appWidgetId: Int){
          val sharedPreferencesEditor = context.getSharedPreferences(PREFS_NAME, 0).edit()
            sharedPreferencesEditor.remove(PREF_PREFIX_KEY + appWidgetId)
            sharedPreferencesEditor.apply()
        }
    }

    public override fun onCreate(icicle: Bundle){
        super.onCreate(icicle)
        setResult(RESULT_CANCELED)
        setContentView(R.layout.new_app_widget_configure)
        mAppWidgetText = findViewById(R.id.appwidget_text)

        val intent = intent
        val extras = intent.extras

        if (extras != null){
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }
        if(mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID){
            finish()
            return
        }
        mAppWidgetText.setText(loadTitlePref(this,mAppWidgetId))

    }

}