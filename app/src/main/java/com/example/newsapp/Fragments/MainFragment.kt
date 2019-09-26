package com.example.newsapp.Fragments

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

import butterknife.BindView
import butterknife.ButterKnife
import com.example.newsapp.viewmodels.NewsViewModel

class MainFragment : Fragment() {

    @BindView(R.id.fb_news_items)
    internal var fb_news_items: FloatingActionButton? = null

    @BindView(R.id.fb_news_back)
    internal var fb_news_back: FloatingActionButton? = null

    @BindView(R.id.rv_news_layout)
    internal var rv_news_layout: RecyclerView? = null

    @BindView(R.id.adView)
    internal var mAdView: AdView? = null

    private var mainAdapter: MainAdapter? = null

    private var model: NewsViewModel? = null

    override fun onCreateView(layoutInflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_main_news,
                container,
                false)
        ButterKnife.bind(this, view)
        return view

    }

    @SuppressLint("RestrictedApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        MobileAds.initialize(context,
                getString(R.string.mobile_ads_id))

        val adRequest = AdRequest.Builder().build()
        mAdView!!.loadAd(adRequest)
        mAdView!!.bringToFront()

        val linearLayoutManager = LinearLayoutManager(rv_news_layout!!
                .context)

        rv_news_layout!!.layoutManager = linearLayoutManager


        if (arguments != null && arguments!!
                        .getString(getString(R.string.main_fragment_get_data_flag_key)) != null) {

            this.model = ViewModelProviders
                    .of(this)
                    .get(NewsViewModel::class.java)

            DATAFLAG = arguments!!
                    .getString(getString(R.string.main_fragment_get_data_flag_key))

            if (DATAFLAG == getString(R.string.all_data_flag)) {
                model!!.fetchAllArticlesNoQuery().observe(this, { articlesList ->
                    mainAdapter = MainAdapter(context, articlesList)
                    rv_news_layout!!.adapter = mainAdapter
                    mainAdapter!!.notifyDataSetChanged()

                })
            } else if (DATAFLAG == getString(R.string.load_favorites_flag)) {
                model!!.fetchDataByFavorite().observe(this, { articlesList ->
                    mainAdapter = MainAdapter(context, articlesList)
                    rv_news_layout!!.adapter = mainAdapter
                    mainAdapter!!.notifyDataSetChanged()
                })
            } else if (DATAFLAG == getString(R.string.load_set_to_read)) {
                model!!.fetchDataBySetToRead().observe(this, { articlesList ->
                    mainAdapter = MainAdapter(context, articlesList)
                    rv_news_layout!!.adapter = mainAdapter
                    mainAdapter!!.notifyDataSetChanged()
                })
            } else if (DATAFLAG == getString(R.string.main_fragment_domain_search_value)) {

                model!!.fetchArticlesByDomain(arguments!!
                        .getString(getString(R.string.main_fragment_query_value)))
                        .observe(this, { articlesList ->
                            mainAdapter = MainAdapter(context, articlesList)
                            rv_news_layout!!.adapter = mainAdapter
                            mainAdapter!!.notifyDataSetChanged()
                        })
            } else if (DATAFLAG == getString(R.string.title_search_value)) {

                model!!.fetchArticlesByTitle(arguments!!
                        .getString(getString(R.string.main_fragment_query_value)))
                        .observe(this, { articlesList ->
                            mainAdapter = MainAdapter(context, articlesList)
                            rv_news_layout!!.adapter = mainAdapter
                            mainAdapter!!.notifyDataSetChanged()
                        })
            } else if (DATAFLAG == getString(R.string.main_fragment_data_by_url_value)) {
                model!!.fetchArticlesByDomain(arguments!!
                        .getString(getString(R.string.current_url_key)))
                        .observe(this, { articlesList ->
                            mainAdapter = MainAdapter(context, articlesList)

                            rv_news_layout!!.adapter = mainAdapter
                            mainAdapter!!.notifyDataSetChanged()
                        })
            } else if (arguments!!
                            .getString(getString(R.string.main_fragment_get_data_flag_key)) == getString(R.string.articles_by_country_value)) {

                val countryName = arguments!!
                        .getString(getString(R.string.country_data_flag))

                model!!.fetchArticlesByCountry(countryName).observe(this, { articlesList ->

                    mainAdapter = MainAdapter(context,
                            articlesList)
                    rv_news_layout!!.adapter = mainAdapter
                    mainAdapter!!.notifyDataSetChanged()
                })


            }
        }

        fb_news_items!!.setOnClickListener { v ->
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

            fb_news_back!!.visibility = View.VISIBLE

            fb_news_back!!.setOnClickListener { v ->
                fragmentManager.popBackStack()

            }

        }

    }

    companion object {

        private var DATAFLAG: String? = null
    }
}
