package com.example.newsapp.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsapp.Fragments.SourcesFragment
import com.example.newsapp.R
import kotlinx.android.synthetic.main.card_category_list.view.*

class NewsCategoryAdapter(context: Context, categoryList:List<String>) : RecyclerView.Adapter<NewsCategoryAdapter.NewsCategoryViewHolder>() {

    lateinit var layoutInflater: LayoutInflater
    var mContext:Context = context
    var mNewsCategoryList:List<String> = categoryList


    class NewsCategoryViewHolder(view: View): RecyclerView.ViewHolder(view){
        var fragmentManager = (this as AppCompatActivity).supportFragmentManager

        lateinit var fragmentTransaction:FragmentTransaction
         var tv_category:TextView = view.findViewById(R.id.tv_categories)

        fun setData(currentCategory:String, position:Int){
            this.tv_category.setText(currentCategory.substring(0,1).toUpperCase() +
                currentCategory.substring(1))

            tv_category.setOnClickListener {
                var sourcesFragment:SourcesFragment = SourcesFragment()
                var bundle: Bundle = Bundle()
                var fragmentContainer = R.id.fr_main_holder

                bundle.putString("sourceCategory",currentCategory)
                sourcesFragment.arguments = bundle

                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(fragmentContainer, sourcesFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss()

            }
        }


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup,
                                    viewType: Int): NewsCategoryViewHolder {

        var view:View = layoutInflater.inflate(R.layout.card_category_list, viewGroup,
                false)

        return NewsCategoryViewHolder(view)

    }
    override fun onBindViewHolder(holder:NewsCategoryViewHolder, position:Int){
        var currentCategory:String = mNewsCategoryList.get(position)
        holder.setData(currentCategory, position)
    }

    override fun getItemCount(): Int {
        return mNewsCategoryList.size
    }


}