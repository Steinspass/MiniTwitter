package com.naimdridi.seccion_01_dev_android_in_kotlin.Others

import android.content.Context

class MySharedPreferences(context: Context){

    // Nombre del fichero de la la sharedPreferences

    private val fileName = "mis_preferencias"

    // Instancia de ese fichero

    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    //Por cada una de las variables que vamos a guardar en nuestro fichero de las shared preferences

    var name: String
        get() = prefs.getString("name", "")
        set(value) = prefs.edit().putString("name", value).apply()

    var age: Int
        get() = prefs.getInt("age", -1)
        set(value) = prefs.edit().putInt("age", value).apply()

}