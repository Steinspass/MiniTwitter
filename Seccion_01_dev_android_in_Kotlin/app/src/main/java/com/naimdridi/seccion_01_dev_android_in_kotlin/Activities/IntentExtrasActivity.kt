package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.naimdridi.seccion_01_dev_android_in_kotlin.R
import com.naimdridi.seccion_01_dev_android_in_kotlin.models.Student
import kotlinx.android.synthetic.main.activity_intent_extras.*

class IntentExtrasActivity : AppCompatActivity() {

    private lateinit var toolbar: android.support.v7.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_extras)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        buttonBack.setOnClickListener { startActivity(Intent(this, IntentsActivity::class.java)) }

        val isExtraSet = setIntentExtrasFromPreviousActivity()
        val isParcelableSet = setObjectFromPreviousActivity()

        if (!isExtraSet && !isParcelableSet){
            checkBoxDevelopers.visibility = View.INVISIBLE
        }

    }

    private fun setIntentExtrasFromPreviousActivity(): Boolean{

        val name = intent.getStringExtra("name")
        val lastName = intent.getStringExtra("lastName")
        val age = intent.getIntExtra("age", -1)
        val developer = intent.getBooleanExtra("developer", false)

        if (name != null && lastName != null && age>= 0){
            textViewName.text = name
            textViewLastName.text = lastName
            textViewAge.text = "$age"
            checkBoxDevelopers.text = "Developer"
            checkBoxDevelopers.isChecked = developer
            return true
        }
        return false
    }

    private fun setObjectFromPreviousActivity(): Boolean{

        val student = intent.getParcelableExtra<Student>("student")

        student?.let{
            textViewName.text = student.name
            textViewLastName.text = student.lastName
            textViewAge.text = "${student.age}"
            checkBoxDevelopers.text = "Developer"
            checkBoxDevelopers.isChecked = student.isDeveloper
            return true
        }
        return false
    }
}
