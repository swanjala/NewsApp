package com.example.newsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import data.datamodels.Articles;

public class CountryHeadlineAdapter extends
        RecyclerView.Adapter<CountryHeadlineAdapter.CountryHeadlineViewHolder> {

    private List<Articles> articlesList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CountryHeadlineAdapter(Context context, List<Articles> articlesList){
        this.articlesList = articlesList;

        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CountryHeadlineViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                        int viewType){
        View view = layoutInflater.inflate(R.layout.card_news_data, viewGroup,
                false);

        CountryHeadlineViewHolder countryHeadlineViewHolder = new
                CountryHeadlineViewHolder(view);
        return countryHeadlineViewHolder;
    }

    @Override
    public void onBindViewHolder(CountryHeadlineViewHolder holder, int position){
        Articles currentNewsData = articlesList.get(position);
        holder.setData(currentNewsData, position);

    }
    @Override
    public int getItemCount() {

        return articlesList.size();
    }

    class CountryHeadlineViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_Title, tv_description, tv_author, tv_date;
        ImageView iv_articleImage;

        public CountryHeadlineViewHolder(View articleView) {
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
