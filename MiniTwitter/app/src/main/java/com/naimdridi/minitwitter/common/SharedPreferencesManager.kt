package com.naimdridi.minitwitter.common

import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE



class SharedPreferencesManager{

    private val APP_SETTINGS_FILE = "APP_SETTINGS"



    private fun getSharedPreferences(): SharedPreferences {
        return MyApp.context!!
            .getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE)
    }

    fun setSomeStringValue(dataLabel: String, dataValue: String) {
        val editor = getSharedPreferences().edit()
        editor.putString(dataLabel, dataValue)
        editor.apply()
    }

    fun setSomeBooleanValue(dataLabel: String, dataValue: Boolean) {
        val editor = getSharedPreferences().edit()
        editor.putBoolean(dataLabel, dataValue)
        editor.apply()
    }

    fun getSomeStringValue(dataLabel: String): String? {
        return getSharedPreferences().getString(dataLabel, null)
    }

    fun getSomeBooleanValue(dataLabel: String): Boolean {
        return getSharedPreferences().getBoolean(dataLabel, false)
    }
}