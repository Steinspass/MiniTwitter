package com.naimdridi.introkotlin.Others

import android.util.Log
import java.util.*

class Functions {


    private fun showCase1(){
        // Funciones, parametros y Unit

        fun function1(){} // Si no se retorna ningun valor es un Unit por defecto
        val result1 = function1() // result1 = de tipo Unit

        fun function2(): Unit  {}
        val result2: Unit = function2()

        // Los parametros de las funciones nesesitan especificar su tipo siempre
        fun function3(param1: String, param2: String): String{
            return "$param1, $param2"
        }
        val result3 = function3("String 1", "String 2")

        // Aqui se le especifica el valor y devuelve dicho valor si no se pasa  otro
        fun function4(param: Int = 5){}

        // Aqui habria que pasarle un valor obligatoriamente y tendria que ser Int o null por utilizar operador nullable
        fun function4(param: Int?){}

    }

    private fun showCase2(){
       // Funciones con funciones como parametros(callback) y llamadas por lambda

        fun printSum(num1: Int, num2: Int, printer: (result: Int) -> Unit){
            printer(num1 + num2)
        }

        printSum(5,5) { result ->
            Log.w("FUNCTIONS-2", "Lambda expressions printing the sum result (10) => $result")
        }

        // Si el callback solo recibe un parametro, podemos omitir ese "result" -> it
        printSum(12,12) {
            Log.w("FUNCTIONS-2", "Lambda expressions printing the sum result (24) => $it")
        }

        fun printSum2(num1: Int, num2: Int, printer: (result: Int, param1: Int, param2: Int) -> Unit){
            printer(num1 + num2, num1, num2)
        }

        printSum2(7,7) { result, param1, param2 ->
            Log.w("FUNCTIONS-2", "Lambda expressions printing the sum result (14) => $result")
        }

        // Como buena practica los parametros no usados cambiarlos por '_'
        printSum2(9,9) { result, _, _ ->
            Log.w("FUNCTIONS-2", "Lambda expressions printing the sum result (18) => $result")
        }




    }

    private fun showCase3(){
        // Named arguments

        fun funtion1(firstName: String, lastName: String, age: Int, city: String, date: Date){}

        // Diferentes formas de llamar a la funcion cambiando el orden de los parametros requeridos o solo pasando los que quieras
        funtion1("name", "Secondname", 26, "malaga", Date())
        funtion1(age = 29, firstName = "NAME", date = Date(), city = "cadiz", lastName = "Second")
    }

    private fun showCase4(){
        // Single-Expressions functions

        // Classic sytle
        fun sum1(param1: Int, param2: Int): Int{
            return param1 + param2
        }

        // Single sytle // De esta forma infiere el tipo por lo que no que decirle que tipo quiere de vuelta
        fun sum2(param1: Int, param2: Int) = param1 + param2


    }

    private fun showCase5(){
        // Variable number of arguments (Varargs)
        fun sumAll(vararg numbers: Int) = numbers.sum()

        val result = sumAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        Log.w("FUNCTIONS-5", "vararg result is 55 => $result")


    }

    private fun showCase6(){
    // Extension Functions

        fun String.welcome(){
           Log.w("FUNCTIONS-6", "Welcome to Kotlin and to Extensions Functions!")
        }

        "".welcome()
    }

    fun showCases(){
        showCase1()
        showCase2()
        showCase3()
        showCase4()
        showCase5()
        showCase6()
    }
}

// Si es declarada aqui se tendra acceso en toda la app
fun String.welcomeKotlin(){
    Log.w("FUNCTIONS-6", "Welcome to Kotlin and to Extensions Functions Globally!")
}
