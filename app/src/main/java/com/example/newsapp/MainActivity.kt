package com.example.newsapp

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.example.newsapp.Fragments.MainFragment
import com.google.firebase.analytics.FirebaseAnalytics
import java.lang.invoke.ConstantCallSite

class MainActivity: AppCompatActivity(){
    var DATAFLAG:String = ""
    var LARGE_SCREEN_SIZE:Boolean = false
    var fragmentContainer:Int = 0
    lateinit var firebaseAnalytics:FirebaseAnalytics
    lateinit var fragmentManager:FragmentManager

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mFirebaseAnalytics:FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        var bundle:Bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,
                getString(R.string.firebase_main_activity_log_item_name))
        bundle.putSerializable(FirebaseAnalytics.Param.CONTENT_TYPE,
                getString(R.string.firebase_activity_log))
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        if(findViewById<ConstraintLayout>(R.id.largeScreen) != null){
            this.LARGE_SCREEN_SIZE = true

            if(DATAFLAG != null ){

                if(DATAFLAG.equals(getString(R.string.load_all_data_flag))){
                    loadData()
                } else if (DATAFLAG.equals(getString(R.string.load_data_sources_flag))){
                    loadSourcesList()
                }
            } else if(findViewById<ConstraintLayout>(R.id.cl_country_detail) != null){
                this.LARGE_SCREEN_SIZE = true
                loadCountries()
            }else if (findViewById<FrameLayout>(R.id.fr_main_holder) != null ){
                this.LARGE_SCREEN_SIZE = false;

                if(DATAFLAG != null){
                    if (DATAFLAG != null && DATAFLAG.equals(getString(R.string.load_data_sources_flag))){
                        loadData()
                    } else if (DATAFLAG.equals(getString(R.string.load_data_sources_flag))){
                        loadSourcesList()
                    }
                } else {
                    loadSourcesList()
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (findViewById<ConstraintLayout>(R.id.largeScreen) != null){
            this.LARGE_SCREEN_SIZE = true
            if(DATAFLAG != null){
                if (DATAFLAG.equals(getString(R.string.load_all_data_flag))){
                    loadData();
                } else if(DATAFLAG.equals(getString(R.string.load_data_sources_flag))){
                    loadSourcesList()
                }
            }
        } else if (findViewById<FrameLayout>(R.id.fr_main_holder) != null){
            this.LARGE_SCREEN_SIZE = false;

            if(DATAFLAG != null){
                if (DATAFLAG.equals(getString(R.string.load_all_data_flag))){
                    loadData()
                }
            }
        }
        if (findViewById<ConstraintLayout>(R.id.cl_country_detail) != null){
            this.LARGE_SCREEN_SIZE = true
            loadCountries()
        }
        this.LARGE_SCREEN_SIZE = false
    }

    fun loadData() {
        var mainFragment = MainFragment()
        var bundle:Bundle = Bundle()
        bundle.putString(resources.getString(R.string.data_flag_key), DATAFLAG)

        mainFragment.arguments()
    }

    fun loadSourcesList(){

    }
    fun loadCountries(){

    }



}