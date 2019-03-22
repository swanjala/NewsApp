package com.example.newsapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

import data.api.ApiManager;
import data.datamodels.Articles;
import data.datamodels.DataResponse;
import data.networkutils.UrlBuilder;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    private List<Articles> articleList = new ArrayList<>();
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UrlBuilder urlBuilder = new UrlBuilder();


        FloatingActionButton floatingActionButton = findViewById(R.id.fb_news_items);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDialog();
            }
        });

    }

    public void inputDialog() {

        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("News App")
                .setMessage("Enter Topic")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());


                        new Thread(new Task()).start();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    class Task implements Runnable{
        @Override
        public void run() {


        }
    }

}
