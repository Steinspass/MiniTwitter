package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.naimdridi.seccion_01_dev_android_in_kotlin.Adapters.PersonAdapter
import com.naimdridi.seccion_01_dev_android_in_kotlin.R
import com.naimdridi.seccion_01_dev_android_in_kotlin.models.Person
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    private lateinit var adapter: PersonAdapter
    private lateinit var personList: List<Person>
    private lateinit var toolbar: android.support.v7.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        personList = getPersons()
        adapter = PersonAdapter(this, R.layout.list_view_person, personList)
        listView.adapter = adapter

    }

    private fun getPersons(): List<Person> {
        return listOf(
            Person("Pedro","Vega", 23),
            Person("Antonio","Lora", 45),
            Person("Dave","Grohl", 32),
            Person("Jack","Reagan", 78),
            Person("Frank","Miller", 57),
            Person("Shu","Atsuki", 17),
            Person("Naoki","Urakawa", 44),
            Person("Shinichiro","Watanabe", 53),
            Person("Diego","Perez", 21),
            Person("Juan","Lopez", 83),
            Person("Patxi","Amorebita", 35)
        )
    }
}
