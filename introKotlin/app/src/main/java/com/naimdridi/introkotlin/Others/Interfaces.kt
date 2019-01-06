package com.naimdridi.introkotlin.Others

import android.util.Log

class Interfaces{

    private var anonymousObjectImplementingInterface: Any? = null


    private fun showCase1(){

        anonymousObjectImplementingInterface = object : Interface1 {
            override fun abstractMethod() {
                // Poner codigo es totalmente opcional
            }
        }
        // Nesesitamos castear ya que la declaracion lo hacemos como Any y no puede inferir el tipo correctamente
        (anonymousObjectImplementingInterface as Interface1).methodWithImplementationByDefault()
        (anonymousObjectImplementingInterface as Interface1).abstractMethod()

    }

    private fun showCase2(){

        val aoii = object : Interface2{
            override val propertyAbstract: Int
                get() = 10
        }
        aoii.propertyAbstract
        aoii.propertyWithImplementation
        aoii.hello()
    }

    fun showCases(){
        showCase1()
        showCase2()
    }
}

interface Interface1 {

    fun abstractMethod() // este metodo es abstracto por que no implementa nada

    fun methodWithImplementationByDefault(){
        // este otro no es abstracto por que aun estando vacio pero esta abierto
    }
}

interface Interface2 {

    val propertyAbstract: Int // esta propiedad es abstracta por que tiene ningun valor asignado
    var propertyWithImplementation: String
        get() = "ByDefault"
        set(value) {this.propertyWithImplementation = value}

    fun hello() {
        Log.w("INTERFACE-2", "Is it working $propertyWithImplementation?, YaY! $propertyAbstract :)")
    }

}