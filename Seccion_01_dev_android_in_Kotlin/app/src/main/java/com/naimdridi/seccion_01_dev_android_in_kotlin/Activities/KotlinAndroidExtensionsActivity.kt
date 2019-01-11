package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.naimdridi.seccion_01_dev_android_in_kotlin.R
import kotlinx.android.synthetic.main.activity_kotlin_android_extensions.*

class KotlinAndroidExtensionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_android_extensions)

        val btn = findViewById<Button>(R.id.buttonById)
        btn.setOnClickListener { Toast.makeText(this, "Click By Id!", Toast.LENGTH_SHORT).show() }

        buttonByKat.setOnClickListener { Toast.makeText(this, "Click By Kotlin Android Extensions!", Toast.LENGTH_SHORT).show() }

    }
}
