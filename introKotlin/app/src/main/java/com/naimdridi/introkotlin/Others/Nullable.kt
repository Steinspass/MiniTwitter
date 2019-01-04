package com.naimdridi.introkotlin.Others

import java.lang.NullPointerException

// #https://kotlinlang.org/docs/reference/null-safety.html

/*
   Operadores relaciones con la nulabilidad:
        - only-safe (?)
        - non-null asserted (!!)

   Sistema de tipado de Kotlin esta pensado para eliminar de nuestro codigo el NullPointerExcepcion.
   Los 4 unicos posibles casos para NPE son:

   1) Una llamada explicita al error NullPointerException()
   2) Uso del operador !!
   3) Codigo externo Java
   4) Alguna insconsistencia de datos en relacion a la inicializacion

 */

    class Nullable{

        private lateinit var variable1: String
        private var variable2: String? = null


        private fun showCase1() {
            throw NullPointerException()
        }

        private fun showCase2(var1: String?){
            var1.toString() // Devuelve null en caso que var1 sea null
            var1!!.toString() // = NullPointerException en caso de que sea null

        }

        private fun showCase3(){
            Other.getNPE() // Devuelve un NullPointerException
        }

        private fun showCase4(){
            variable1.length // Devuelve un NPE porque variable1 no sido latenit y se ha marcado como tal

            variable2?.length
            variable2!!.length
        }

        fun showCases(){
            showCase1()
            showCase2("")
            showCase3()
            showCase4()
        }
    }



