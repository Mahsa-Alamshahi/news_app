package org.nextoptech.news.ui

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class NewsApplication: MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()

    }
}