package com.example.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import data.datamodels.Articles;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private List<Articles>  mArticleDataSet;
    private LayoutInflater layoutInflater;
    private Context context;


    public MainAdapter(Context context, List<Articles> articlesList){
        mArticleDataSet = articlesList;

        Collections.sort(articlesList, (o1, o2)
                -> o1.getPublishedAt().compareTo(o2.getPublishedAt()));


        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType){
        View view = layoutInflater.inflate(R.layout.card_news_data, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Articles currentArticle = mArticleDataSet.get(position);
        holder.setData(currentArticle,position);
    }

    @Override
    public int getItemCount() {

        return mArticleDataSet.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView tv_Title, tv_description, tv_author, tv_date;
        ImageView iv_articleImage;

        public MyViewHolder(View articleView) {
            super(articleView);

            tv_Title = articleView.findViewById(R.id.tv_title);
            tv_description = articleView.findViewById(R.id.tv_description);
            tv_author = articleView.findViewById(R.id.tv_author);
            tv_date = articleView.findViewById(R.id.tv_date);
            iv_articleImage = articleView.findViewById(R.id.iv_image);

        }

        public void setData(final Articles current, final int position){
            this.tv_Title.setText(current.getTitle());
            this.tv_description.setText(current.getDescription());
            this.tv_author.setText(current.getAuthor());
            this.tv_date.setText(current.getPublishedAt());

            Picasso.with(context).load(current.getUrlToImage()).fit().centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .fit()
                    .centerCrop()
                    .into(iv_articleImage);
        }


    }

}

