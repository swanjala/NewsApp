package com.example.newsapp.dependencies

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.newsapp.NewsApplication
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector

object AppInjector {
    fun init(application: NewsApplication) {
        DaggerAppComponent.builder()
            .application(application)
            .build()
            .inject(application)

        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {
                    /*Not yet implemented*/
                }

                override fun onActivityResumed(activity: Activity) {
                    /*Not yet implemented*/
                }

                override fun onActivityPaused(activity: Activity) {
                    /*Not yet implemented*/
                }

                override fun onActivityStopped(activity: Activity) {
                    /*Not yet implemented*/
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                    /*Not yet implemented*/
                }

                override fun onActivityDestroyed(activity: Activity) {
                    /*Not yet implemented*/
                }
            }
        )
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasAndroidInjector) {
            AndroidInjection.inject(activity)
        }
    }
}
