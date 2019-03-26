package com.example.newsapp.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapp.R;

import java.util.HashMap;
import java.util.List;

public class CountryAdapter extends
        RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<String> mCountryList;
    private LayoutInflater layoutInflater;
    private Context context;
    private HashMap<String, String> countryData;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public CountryAdapter(Context context, List<String> countryList,
                          HashMap<String, String> countryData){

        mCountryList = countryList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.countryData = countryData;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                int viewType){
        View view = layoutInflater.inflate(R.layout.card_country_list,
                viewGroup,false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view, countryData);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position){
        String currentCountry = mCountryList.get(position);
        holder.setData(currentCountry, position);
    }
    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    class CountryViewHolder extends  RecyclerView.ViewHolder{

        TextView tv_country;
        HashMap<String, String> countryData;

        public CountryViewHolder(View countryView, HashMap<String, String> countryData){
            super(countryView);

            tv_country = countryView.findViewById(R.id.tv_country);
            this.countryData = countryData;
        }

        public void setData(final String currentCountry, final int position){

            this.tv_country.setText(countryData.get(currentCountry.toUpperCase()));
        }

    }
}
