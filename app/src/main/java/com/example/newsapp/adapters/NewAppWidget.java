package com.example.newsapp.adapters;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import com.example.newsapp.MainActivity;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.database.AppDatabase;
import data.datamodels.Articles;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in
 * {@link NewAppWidgetConfigureActivity NewAppWidgetConfigureActivity}
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        new AsyncTask<Context, Void, LiveData<List<Articles>>>(){
            @Override
            protected LiveData<List<Articles>> doInBackground(Context... context) {

                AppDatabase db = AppDatabase.getDatabase(context[0]);
                LiveData<List<Articles>> articleDataList
                        = db.articleAccessObject().fetchAllData();

                return articleDataList;
            }

            @Override
            protected void onPostExecute(LiveData<List<Articles>> List) {
                super.onPostExecute(List);

                if(List != null){

                    List.observeForever(articlesList -> {

                        int dataIndex = 0;
                        views.setTextViewText(R.id.tv_news_title,
                                articlesList.get(dataIndex).getTitle());
                        views.setTextViewText(R.id.tv_description,
                                articlesList.get(dataIndex).getDescription());

                        views.setTextViewText(R.id.tv_source_widget,
                                articlesList.get(dataIndex).getPublishedAt());

                        Intent intent = new Intent(context, MainActivity.class);

                        PendingIntent pendingIntent = PendingIntent
                                .getActivity(context,0,
                                intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        views.setOnClickPendingIntent(R.id.bt_open_app, pendingIntent);

                        if (articlesList.get(dataIndex).getUrlToImage() != null){
                            Picasso.with(context)
                                    .load(articlesList.get(dataIndex).getUrlToImage())
                                    .into(views, R.id.imageView, new int[]{appWidgetId}
                                    );
                        }


                        appWidgetManager.updateAppWidget(appWidgetId, views);
                    });

                }
            }

        }.execute(context);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);


        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            NewAppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

}

