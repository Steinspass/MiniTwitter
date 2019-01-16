package com.naimdridi.seccion_01_dev_android_in_kotlin.App

import android.app.Application
import com.naimdridi.seccion_01_dev_android_in_kotlin.Others.MySharedPreferences

val preferences: MySharedPreferences by lazy { MyApp.prefs!! }

class MyApp : Application(){

    companion object {
        var prefs: MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = MySharedPreferences(applicationContext)
    }
}