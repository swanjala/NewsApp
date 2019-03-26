package com.example.newsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapp.R;

import java.util.List;

import data.datamodels.Sources;

public class SourcesAdapter extends
        RecyclerView.Adapter<SourcesAdapter.SourceViewHolder> {

    private List<Sources> mSourceDataSet;
    private LayoutInflater layoutInflater;
    private Context context;

    public SourcesAdapter(Context context, List<Sources> sourceList){
        mSourceDataSet = sourceList;

        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;

    }

    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                int viewType){
        View view = layoutInflater
                .inflate(R.layout.card_source_data,viewGroup, false);
        SourceViewHolder sourceViewHolder = new SourceViewHolder(view);

        return sourceViewHolder;

    }

    @Override
    public void onBindViewHolder(SourceViewHolder holder, int position){
        Sources currentSource = mSourceDataSet.get(position);
        holder.setData(currentSource,position);
    }

    @Override
    public int getItemCount() {
        return mSourceDataSet.size();
    }

    class SourceViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_description, tv_category, tv_language, tv_country;
        public SourceViewHolder(View sourcesView){
            super(sourcesView);

            tv_name = sourcesView.findViewById(R.id.tv_name);
            tv_description = sourcesView.findViewById(R.id.tv_description);
            tv_category = sourcesView.findViewById(R.id.tv_category);
            tv_language = sourcesView.findViewById(R.id.tv_language);
            tv_country = sourcesView.findViewById(R.id.tv_country);

        }

        public void setData(final Sources current, final int position){

            this.tv_name.setText(current.getName());
            this.tv_description.setText(current.getDescription());
            this.tv_category.setText(current.getCategory());
            this.tv_language.setText(current.getLanguage());
            this.tv_country.setText(current.getCountry());
        }

    }
}
