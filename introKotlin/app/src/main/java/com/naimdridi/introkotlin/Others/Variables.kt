package com.naimdridi.introkotlin.Others

import android.util.Log

// DOC: #https://kotlinlang.org/docs/reference/basic-types.html

/*
 En kotlin todo es un objecto.
 No hay tipos basicos todos son objectos y tienen referencias, y no existe void.
 Si algo no devuelve nada esta devolviendo Unit object.
 las variables pueden ser mutables o inmutables.
 Mutables que son variables que pueden cambiar el valor que tienen.
 Inmutables son como los Final en java tienen un valor fijo invariable.
 Usar inmutables siempre que se posible.
 Los tipos son definidos despues del nombre de la variable con dos puntos y espacio.
 ( var nombre: tipo)
 los tipos pueden ser String, int, boolean etc...
 MUTABLES = var
 INMUTABLES = val

 */

class Variables {

    private var variable0: Int = 1
    private var variable1 = 1.toByte()
    private var variable2 = -123
    private var variable3 = 23434567845
    private var variable4 = 1.1.toFloat()
    private var variable5 = 1.9
    private var variable6 = 'a'
    private var variable7 = "a"
    private var variable8 = true
    private var variable9 = arrayOf(1, 2, 3, 4, 5)
    private var variable10 = arrayOfNulls<Int>(10) // [null, null] solo acepta int
    private val variable11 = "Esta variable es read-only/inmutable/constante"


    // Show case para Byte
    private fun showCase1() {
        Log.w("VARIABLE-0", "es variable0 un Integer -->" + (variable0 is Int) + "Por que no un Byte?" )
        Log.w("VARIABLE-1", "es variable1 un Byte -->" + (variable1 is Byte))

    }

    // Show case para Int
    private fun showCase2() {
        Log.w("VARIABLE-2", "es un valor entero"  )
        variable2 = Int.MAX_VALUE


    }
    // Show case para Long
    private fun showCase3() {
        Log.w("VARIABLE-3", "es un valor Long? --> " + (variable3 is Long))

    }

    // Show case para Float
    private fun showCase4() {
        Log.w("VARIABLE-4", "es un valor Float? --> " + (variable4 is Float))

    }

    // Show case para Double
    private fun showCase5() {
        Log.w("VARIABLE-5", "es un valor double? --> " + (variable5 is Double))
    }

    // Show case para Char
    private fun showCase6() {
        Log.w("VARIABLE-6", "es un valor char? --> " + (variable6 is Char))

    }

    // Show case para String
    private fun showCase7() {
        Log.w("VARIABLE-7", "es un valor string? --> " + (variable7 is String))

        // String Literals
        variable7 = "Hola mundo!!!\n Adios mundo!" // Java's style
        variable7 = """
                    Hola mundo!!!
                    Adios mundo!

                    """ // Kotlin's style

        // String Templates
        var points = 9
        var maxPoints = 10
        variable7 = "hola tengo " + points + "puntos sobre " + maxPoints // Java's style
        variable7 = "hola tengo $points puntos sobre $maxPoints" // Kotlin's style
        variable7 = "hola tengo ${points * 10} puntos sobre ${maxPoints * 10}" // Kotlin's style


    }
    // Show case para Boolean
    private fun showCase8() {
        Log.w("VARIABLE-8", "es un valor Boolean? --> " + (variable8 is Boolean))
    }

    // Show case para Array<Int>
    private fun showCase9() {
        Log.w("VARIABLE-9", "es un Array<Int> --> " + (variable9 is Array<Int>))

    }

    // Show case para Array<Int?>
    private fun showCase10() {
        Log.w("VARIABLE-10", "es un Array<Int?> --> " + (variable10 is Array<Int?>))

        variable10 = arrayOfNulls(3)
        variable10 = arrayOf(1, 2, 5, 4, 56)

        variable10[0]?.toFloat() // Safe Calls -- En caso de ser null, devuelve null de nuevo
        variable10[0]?.toFloat() ?: "Error" // Elvis Operator -- En caso de ser null, devuelve "Error"
        variable10[0]!!.toFloat()// The !! Operator -- En caso de estar seguro al 100% de que no es null , si lo fuera, lanza Null Pointer Excepcion

    }

    fun showCases(){
        showCase1()
        showCase2()
        showCase3()
        showCase4()
        showCase5()
        showCase6()
        showCase7()
        showCase8()
        showCase9()
        showCase10()
    }


}