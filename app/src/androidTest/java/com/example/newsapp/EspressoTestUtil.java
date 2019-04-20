package com.example.newsapp;

import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

public class EspressoTestUtil {

    public static void activityLoader(ActivityTestRule<?
                extends FragmentActivity> activityTestRule){
        activityTestRule.getActivity().getSupportFragmentManager()
                .registerFragmentLifecycleCallbacks(
            new FragmentManager.FragmentLifecycleCallbacks(){
                @Override
                public void onFragmentViewCreated(FragmentManager fragmentManager,
                                                  Fragment fragment, View views,
                                                  Bundle savedInstanceState){
                    traverseViews(views);
                }
            }, true);
    }

    public static void traverseViews(View view){
        if (view instanceof ViewGroup){
            traverseViewGroup((ViewGroup) view);

        }
    }
    private static void traverseViewGroup(ViewGroup view){
        final int count = view.getChildCount();
        for (int i = 0; i < count ; i++) {
            traverseViews(view.getChildAt(i));
        }
    }
}
