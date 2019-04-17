package com.example.newsapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment {


    @BindView(R.id.et_search_parameter)
    EditText et_search_parameter;

    @BindView(R.id.bt_search)
    Button bt_search;

    private static String DATAFLAG;
    private int fragmentContainer;
    private FragmentManager fragmentManager;


    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = layoutInflater
                .inflate(R.layout.fragment_search_news_items,
                        container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        bt_search.setOnClickListener(v -> {
            String queryValue = et_search_parameter.getText().toString();
            navigationHandler(getString(R.string.navigation_handler_title_search_key)
                    ,queryValue);
        });


    }

    private void navigationHandler(String valueType, String queryValue){

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();

        if (valueType.equals("Domain Search")){

            this.DATAFLAG = "domain_search";

            bundle.putString("DataFlag", DATAFLAG);
            bundle.putString("QueryValue", queryValue);
            mainFragment.setArguments(bundle);

            fragmentContainer = R.id.fr_main_holder;
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(fragmentContainer, mainFragment)
                    .commitAllowingStateLoss();
        }
        else if (valueType.trim()
                .equals(getString(R.string.navigation_handler_title_search_key))){

            this.DATAFLAG = "title_search";

            bundle.putString("DataFlag", DATAFLAG);
            bundle.putString("QueryValue", queryValue);
            mainFragment.setArguments(bundle);

            fragmentContainer = R.id.fr_main_holder;
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(fragmentContainer, mainFragment)
                    .commitAllowingStateLoss();


        } else {
            Toast.makeText(getContext(),
                    getString(R.string.search_value_error_message),
                    Toast.LENGTH_LONG).show();
        }
    }
}
