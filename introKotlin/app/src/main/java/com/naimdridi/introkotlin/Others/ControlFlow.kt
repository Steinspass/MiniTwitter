package com.naimdridi.introkotlin.Others

import android.util.Log


class ControlFlow {


    private fun showCases1() {
        //If
        var num1 = 5
        var num2 = 10

        if (num1 > num2) {
            Log.w("CONTROL-FLOW-1", "num1 es mayor que num2")
        }

        if (num2 > num1) {
            Log.w("CONTROL-FLOW-1", "num2 es mayor que num1")
        }

    }

    private fun showCases2() {
        //If Else
        val num1 = 5
        val num2 = 10

        if (num1 > num2) {
            Log.w("CONTROL-FLOW-2", "num1 es mayor que num2")
        }else{
            Log.w("CONTROL-FLOW-2", "num2 es mayor que num1")
        }

        // Como expresion
        if (num1 > num2) Log.w("CONTROL-FLOW-2", "num1 es mayor que num2")
        else             Log.w("CONTROL-FLOW-2", "num2 es mayor que num1")
        // recogerlo como una varible
        var result = if (num1 > num2) num1 else num2



    }

    private fun showCases3() {
        //When, lo que seria switch en Java
        val x = 1

        // when con argumentos (x)

        when (x) {
            1 -> Log.w("CONTROL-FLOW-3", " x == 1") // case1
            2 -> Log.w("CONTROL-FLOW-3", " x == 2") // case2
            else -> Log.w("CONTROL-FLOW-3", " x es otro numero") // case default, que no es obligatorio
        }

        when (x) {
            0, 1 -> Log.w("CONTROL-FLOW-3", " x == 0 o x == 1 ") // case1

        }

        // when sin argumento (x)

        when {
            (x % 2 == 0) -> Log.w("CONTROL-FLOW-3", " numero es par")
            (x % 2 == 1) -> Log.w("CONTROL-FLOW-3", " numero es impar")
        }

        // sin argumento y devolviendo un valor

        val esPar = when {
            (x % 2 == 0) -> true
            else -> false
        }


    }

    private fun showCases4() {
        //Bucles For

        val numbers = arrayOf(1, 2, 3, 4, 5)
        val palabra = "Matematicas"

        for (number in numbers) Log.w("CONTROL-FLOW-4", number.toString())
        for (letra in palabra) Log.w("CONTROL-FLOW-4", letra.toString())

        // Especificando el tipo

        for (number: Int in numbers) {
            Log.w("CONTROL-FLOW-4", number.toString())

        }

        // Con indices

        for ((index, number) in numbers.withIndex()) {
            Log.w("CONTROL-FLOW-4", "$index: $number")
        }


    }

    private fun showCases5() {
        //While & do while

        var x = 10

        while (x > 0){
            Log.w("CONTROL-FLOW-5", x--.toString())

        }

        do {
            Log.w("CONTROL-FLOW-5", "Primera y unica iteracion")
        }while (x > 0)

    }


    fun showCases() {
        showCases1()
        showCases2()
        showCases3()
        showCases4()
        showCases5()
    }
}