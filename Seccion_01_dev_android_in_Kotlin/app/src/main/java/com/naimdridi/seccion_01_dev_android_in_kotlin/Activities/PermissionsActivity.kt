package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.naimdridi.seccion_01_dev_android_in_kotlin.R

class PermissionsActivity : AppCompatActivity() {

    private lateinit var toolbar: android.support.v7.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }
}
