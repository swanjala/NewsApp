package com.example.newsapp.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapp.R;

import java.util.List;

import data.api.ApiManager;
import data.datamodels.Articles;
import data.datamodels.DataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsCategoryAdapter extends RecyclerView.Adapter<
        NewsCategoryAdapter.NewsCategoryViewHolder> {

    private List<String> mNewsCategoryList;
    private LayoutInflater layoutInflater;
    private Context context;

    public NewsCategoryAdapter(Context context, List<String> categoryList){
        this.mNewsCategoryList = categoryList;

        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;

    }

    @Override
    public NewsCategoryViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                     int ViewType){

        View view = layoutInflater.inflate(R.layout.card_category_list,
                viewGroup, false);

        NewsCategoryViewHolder newsCategoryViewHolder = new NewsCategoryViewHolder(view);
        return newsCategoryViewHolder;

    }

    @Override
    public void onBindViewHolder(NewsCategoryViewHolder holder, int position){
        String currentCategory = mNewsCategoryList.get(position);
        holder.setData(currentCategory, position);
    }

    @Override
    public int getItemCount() {
        return mNewsCategoryList.size();
    }


    class NewsCategoryViewHolder extends  RecyclerView.ViewHolder{

        TextView tv_category;

        public NewsCategoryViewHolder(View view){
            super(view);

            tv_category = view.findViewById(R.id.tv_categories);
        }

        public void setData(final  String currentCategory, final int position){

            this.tv_category.setText(currentCategory.substring(0,1).toUpperCase()+
                    currentCategory.substring(1));

        }

    }



}
