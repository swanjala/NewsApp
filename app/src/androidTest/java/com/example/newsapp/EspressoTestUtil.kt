package com.example.newsapp

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.ViewGroup

object EspressoTestUtil {

    fun activityLoader(activityTestRule: ActivityTestRule<out FragmentActivity>) {
        activityTestRule.activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentViewCreated(fragmentManager: FragmentManager,
                                                               fragment: Fragment, views: View,
                                                               savedInstanceState: Bundle?) {
                                traverseViews(views)
                            }
                        }, true)
    }

    fun traverseViews(view: View) {
        if (view is ViewGroup) {
            traverseViewGroup(view)

        }
    }

    private fun traverseViewGroup(view: ViewGroup) {
        val count = view.childCount
        for (i in 0 until count) {
            traverseViews(view.getChildAt(i))
        }
    }
}
