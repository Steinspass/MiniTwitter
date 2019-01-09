package com.naimdridi.introkotlin.Others

import android.util.Log

class CompanionObject {


    private fun showCase1(){
        // Normal Companion Object
        val value = MyClass1.flag
        MyClass1.welcome()
    }

    private fun showCase2(){
        // Implementando Interfaz en Companion Object
        MyClass2.welcome()

    }

    fun showCases(){
        showCase1()
        showCase2()
    }

}

class MyClass1 {
    companion object {
        val flag = 1

        fun welcome(){
            Log.w("COMPANION-OBJECT-1", "Welcome from Companion Object!")
        }
    }
}

class MyClass2{
    companion object: welcome {
        override fun welcome() {
            Log.w("COMPANION-OBJECT-2", "Welcome from Companion Object + Interface!")
        }

    }

}

interface welcome{
    fun welcome()
}