package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities


import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.naimdridi.seccion_01_dev_android_in_kotlin.Others.LifeCycleEventsActivity
import com.naimdridi.seccion_01_dev_android_in_kotlin.Others.ToolbarActivity
import com.naimdridi.seccion_01_dev_android_in_kotlin.R

class LifeCycleActivity : LifeCycleEventsActivity() {

    private var exitEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
    }

    override fun onBackPressed() {
        if (exitEnabled){
            super.onBackPressed()
        }
        exitEnabled = true
        Toast.makeText(this, "Click back again to exit this screen", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ exitEnabled = false }, 2000)

    }


}
