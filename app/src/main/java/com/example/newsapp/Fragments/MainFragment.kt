package com.example.newsapp.Fragments

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.adapters.MainAdapter
import com.example.newsapp.viewmodels.NewsViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_main_news.adView
import kotlinx.android.synthetic.main.fragment_main_news.fb_news_back
import kotlinx.android.synthetic.main.fragment_main_news.fb_news_items
import kotlinx.android.synthetic.main.fragment_main_news.rv_news_layout

class MainFragment : Fragment() {

    private var mainAdapter: MainAdapter? = null
    private var model: NewsViewModel? = null

    override fun onCreateView(layoutInflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return layoutInflater.inflate(R.layout.fragment_main_news,
                container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        MobileAds.initialize(context,
                getString(R.string.mobile_ads_id))

        val adRequest = AdRequest.Builder().build()
        adView.apply {
            loadAd(adRequest)
            bringToFront()
        }

        val linearLayoutManager = LinearLayoutManager(rv_news_layout.context)
        rv_news_layout.layoutManager = linearLayoutManager

        arguments?.let {
            if(it.getString(getString(R.string.main_fragment_get_data_flag_key)) != null ){

                model = ViewModelProviders
                    .of(this).get(NewsViewModel::class.java)
                DATAFLAG = it.getString(getString(R.string.main_fragment_get_data_flag_key))

                model?.let { newsModel ->

                    when (DATAFLAG) {
                        getString(R.string.all_data_flag) -> {
                            newsModel.fetchAllArticlesNoQuery()?.observe(this, Observer { articlesList ->
                                mainAdapter = MainAdapter(context!!, articlesList!!)
                                rv_news_layout.adapter = mainAdapter
                                mainAdapter?.notifyDataSetChanged()

                            })
                        }
                        getString(R.string.load_favorites_flag) -> {
                            newsModel.fetchDataByFavorite().observe(this, Observer { articlesList ->
                                mainAdapter = MainAdapter(context!!, articlesList!!)
                                rv_news_layout.adapter = mainAdapter
                                mainAdapter?.notifyDataSetChanged()
                            })
                        }

                        getString(R.string.load_set_to_read) -> {
                            newsModel.fetchDataBySetToRead().observe(this, Observer { articlesList ->
                                mainAdapter = MainAdapter(context!!, articlesList!!)
                                rv_news_layout!!.adapter = mainAdapter
                                mainAdapter?.notifyDataSetChanged()
                            })
                        }

                        getString(R.string.main_fragment_domain_search_value)-> {
                            newsModel.fetchArticlesByDomain(arguments!!.getString(getString(R.string.main_fragment_query_value)))
                                .observe(this, Observer { articlesList ->
                                    mainAdapter = MainAdapter(context!!, articlesList!!)
                                    rv_news_layout.adapter = mainAdapter
                                    mainAdapter?.notifyDataSetChanged()
                                })
                        }
                        getString(R.string.main_fragment_data_by_url_value) -> {
                            newsModel.fetchArticlesByDomain(arguments!!.getString(getString(R.string.current_url_key)))
                                .observe(this, Observer { articlesList ->
                                    mainAdapter = MainAdapter(context!!, articlesList!!)
                                    rv_news_layout.adapter = mainAdapter
                                    mainAdapter?.notifyDataSetChanged()
                                })
                        }
                        else -> {
                            val countryName = it.getString(getString(R.string.country_data_flag))

                            newsModel.fetchArticlesByCountry(countryName).observe(this, Observer{ articlesList ->
                                mainAdapter = MainAdapter(context!!,
                                    articlesList!!)
                                rv_news_layout.adapter = mainAdapter
                                mainAdapter?.notifyDataSetChanged()
                            })
                        }
                    }
                }
            }
        }

        fb_news_items.setOnClickListener { v ->
            val fragmentContainer: Int
            val fragmentTransaction: FragmentTransaction

            val searchFragment = SearchFragment()
            fragmentContainer = R.id.fr_main_holder
            assert(fragmentManager != null)
            fragmentTransaction = fragmentManager!!.beginTransaction()

            fragmentTransaction.replace(fragmentContainer, searchFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commitAllowingStateLoss()

        }

        val fragmentManager = fragmentManager!!

        if (fragmentManager.backStackEntryCount > 0) {
            fb_news_back.apply {
                visibility = View.VISIBLE
                setOnClickListener{
                    fragmentManager.popBackStack()
                }
            }
        }
    }

    companion object {
        private var DATAFLAG: String? = null
    }
}
