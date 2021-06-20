package com.suki.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}