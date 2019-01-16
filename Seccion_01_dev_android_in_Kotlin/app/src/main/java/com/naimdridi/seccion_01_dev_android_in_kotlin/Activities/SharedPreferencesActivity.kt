package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.naimdridi.seccion_01_dev_android_in_kotlin.App.preferences
import com.naimdridi.seccion_01_dev_android_in_kotlin.R
import kotlinx.android.synthetic.main.activity_shared_preferences.*

class SharedPreferencesActivity : AppCompatActivity() {

    private lateinit var toolbar: android.support.v7.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        buttonSave.setOnClickListener { setValuesSharedPreferences(); cleanEditText(); getValuesSharedPreferences(); }
        //getValuesSharedPreferences()

    }

    private fun getValuesSharedPreferences(){
        if (preferences.name.isNotEmpty() && preferences.age >= 0){
            textViewSharedPreferences.text = "Welcome ${preferences.name}, your age is ${preferences.age}"
        }else{
            textViewSharedPreferences.text = "Welcome. Please save your name and your age"
        }
    }

    private fun setValuesSharedPreferences(){
        if (editTextName.text.toString().isNotEmpty() && editTextAge.text.toString().isNotEmpty()) {
            preferences.name = editTextName.text.toString()
            preferences.age = editTextAge.text.toString().toInt()
            Toast.makeText(this, "Values have been saved successfully", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Please fill the name and the age before saving", Toast.LENGTH_LONG).show()
        }
    }

    private fun cleanEditText(){
        editTextName.text.clear()
        editTextAge.setText("")

    }

}
