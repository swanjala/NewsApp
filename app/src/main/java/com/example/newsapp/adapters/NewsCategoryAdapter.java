package com.example.newsapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapp.Fragments.SourcesFragment;
import com.example.newsapp.R;

import java.util.List;

public class NewsCategoryAdapter extends RecyclerView.Adapter<
        NewsCategoryAdapter.NewsCategoryViewHolder> {

    private List<String> mNewsCategoryList;
    private LayoutInflater layoutInflater;
    private Context context;
    private int fragmentContainer;

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


    class NewsCategoryViewHolder extends  RecyclerView.ViewHolder {

        private FragmentManager fragmentManager = ((AppCompatActivity)context)
                .getSupportFragmentManager();

        FragmentTransaction fragmentTransaction;
        TextView tv_category;

        public NewsCategoryViewHolder(View view){
            super(view);

            tv_category = view.findViewById(R.id.tv_categories);
        }

        public void setData(final  String currentCategory, final int position){

            this.tv_category.setText(currentCategory.substring(0,1).toUpperCase()+
                    currentCategory.substring(1));

            tv_category.setOnClickListener(v -> {

                SourcesFragment sourcesFragment = new SourcesFragment();
                Bundle bundle = new Bundle();

                fragmentContainer = R.id.fr_main_holder;

                bundle.putString(context.getString(R.string.source_category_key)
                        , currentCategory);
                sourcesFragment.setArguments(bundle);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(fragmentContainer,sourcesFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commitAllowingStateLoss();
            });
        }

    }

}
