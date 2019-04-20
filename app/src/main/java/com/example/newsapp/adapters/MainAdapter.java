package com.example.newsapp.adapters;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import data.datamodels.Articles;
import viewmodels.NewsViewModel;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private List<Articles>  mArticleDataSet;
    private LayoutInflater layoutInflater;
    private Context context;

    public MainAdapter(Context context, List<Articles> articlesList){
        mArticleDataSet = articlesList;

        if (mArticleDataSet.size() > 0){
            Collections.sort(articlesList, (o1, o2)
                    -> o1.getPublishedAt().compareTo(o2.getPublishedAt()));
        }
        else{
            Toast.makeText(context,
                    context.getString(R.string.no_news_message),
                    Toast.LENGTH_LONG).show();
        }

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

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        Button btToRead, btFavorite;
        private String publishedAt;
        private NewsViewModel userActionViewModel;
        private Boolean contrastFlag;

        @SuppressLint("ResourceAsColor")
        public MyViewHolder(View articleView) {
            super(articleView);

            tv_Title = articleView.findViewById(R.id.tv_title);
            tv_description = articleView.findViewById(R.id.tv_description);
            tv_author = articleView.findViewById(R.id.tv_author);
            tv_date = articleView.findViewById(R.id.tv_date);
            iv_articleImage = articleView.findViewById(R.id.iv_image);
            btFavorite = articleView.findViewById(R.id.bt_mark_as_favorite);
            btToRead = articleView.findViewById(R.id.bt_to_read);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @SuppressLint("ResourceAsColor")
        public void setData(final Articles current, final int position) {

            publishedAt = current.getPublishedAt();
            this.tv_Title.setText(current.getTitle());
            this.tv_description.setText(current.getDescription());

            SharedPreferences sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(context);

            contrastFlag = sharedPreferences
                    .getBoolean(context.getString(R.string.high_contrast_key),
                            false);

            if (contrastFlag){
                tv_description.setTextColor(context.getResources()
                        .getColor(R.color.colorPrimaryDark,null));
                tv_description.setTextScaleX((float) 1.25);

                tv_Title.setTextColor(context.getResources()
                        .getColor(R.color.colorPrimaryDark));
                tv_Title.setTextScaleX((float) 1.25);
                tv_author.setTextColor(context
                        .getResources()
                        .getColor(R.color.colorPrimaryDark, null));
                tv_date.setTextColor(context
                        .getResources()
                        .getColor(R.color.colorPrimaryDark, null));


            }
            this.tv_author.setText(current.getAuthor());
            this.tv_date.setText(publishedAt.substring(0,10).concat(" | ")
                    .concat(publishedAt.substring(11,19)));

            if (current.getFavorite()){

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btFavorite.setForeground(context
                            .getDrawable(R.drawable.ic_thumb_up_accent_24dp));
                }

                btFavorite.setOnClickListener(v -> {

                    String titleQuery = "%" + current.getTitle().substring(0, 10) + "%";
                    userActionViewModel.setNewsItemsByFavorite(false, titleQuery);

                });

            } else{

                if (contrastFlag) {
                    btFavorite.setForeground(context.getDrawable
                            (R.drawable.ic_thumb_up_high_contrast_24dp));
                } else {
                    btFavorite.setForeground(context
                            .getDrawable(R.drawable.ic_thumb_up_black_24dp));
                }

                btFavorite.setOnClickListener(v -> {
                    btFavorite.setForeground(context
                            .getDrawable(R.drawable.ic_thumb_up_accent_24dp));

                    String titleQuery = "%" + current.getTitle().substring(0, 10) + "%";
                    userActionViewModel.setNewsItemsByFavorite(true, titleQuery);

                });

            }
            if (current.getToRead()){

                btToRead.setForeground(context.getDrawable(R.drawable.ic_library_add_accent_24dp));

                btToRead.setOnClickListener(v -> {
                    String titleQuery = "%" + current.getTitle().substring(0, 10) + "%";
                    userActionViewModel
                            .setNewsItemsBySetToRead(false, titleQuery);

                });

            }else{
                if (contrastFlag){
                    btToRead.setForeground(context
                            .getDrawable(R.drawable.ic_add_box__high_contrast_black_24dp));
                } else {
                    btToRead.setForeground(context
                            .getDrawable(R.drawable.ic_library_add_black_24dp));
                }
                btToRead.setOnClickListener(v -> {
                    String titleQuery = "%" + current.getTitle().substring(0, 10) + "%";
                    btFavorite.setForeground(context
                            .getDrawable(R.drawable.ic_library_add_accent_24dp));

                    userActionViewModel
                            .setNewsItemsBySetToRead(true, titleQuery);
                });
            }

            tv_description.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });

            Picasso.with(context).load(current.getUrlToImage()).fit().centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .fit()
                    .centerCrop()
                    .into(iv_articleImage);

            this.userActionViewModel = ViewModelProviders
                    .of((FragmentActivity) context)
                    .get(NewsViewModel.class);

        }
    }
}

