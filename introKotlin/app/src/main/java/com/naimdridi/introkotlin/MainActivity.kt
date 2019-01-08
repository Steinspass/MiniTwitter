package com.naimdridi.introkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.naimdridi.introkotlin.Others.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Variables().showCases()
        Operators().showCases()
        ControlFlow().showCases()
        Interfaces().showCases()
        Classes().showCases()

    }
}
