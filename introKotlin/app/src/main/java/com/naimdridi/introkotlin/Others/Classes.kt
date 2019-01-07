package com.naimdridi.introkotlin.Others

import android.util.Log

class Classes {

    private fun showCase1() {
        // Constructor primario

        class Person1
        class Person2 constructor(var firstName: String) // Con constructor Primario
        class Person3 (var firstName: String) // "constructor" puede ser omitido
        class Person4 (val firstName: String, val lastName: String, var age: Int){
            init {
                age = 10
            }

        }

        class Person5 (val firstName: String = "Name", val lastName: String = "SecondName", var age: Int = 21)

        // Para crear instancias de objectos, no usamos la palabra "new"
        val p1 = Person1()
        val p2 = Person2("Name")
        val p3 = Person3("Name")
        val p4 = Person4("Name", "SecondName", 28)
        val p5_1 = Person5("Name1")
        val p5_2 = Person5("Name2", "Second")
        val p5_3 = Person5("name3", "Third", 25)
        val p5_4 = Person5(age = 40, lastName = "lastname1")
        val p6_2 = Person5()


    }

    private fun showCase2() {
        // Constructor Secundario (son menos usados en Kotlin)
        open class Person {
            constructor( firstName: String, lastName: String){
                Log.w("CLASSES-2", "Welcome $firstName $lastName")
            }
        }

        class Person1 : Person{
           constructor(firstName: String) : super(firstName, "name")
            constructor(firstName: String, lastName: String) : super(firstName, "Lastname")
        }

        val p = Person1("Paco","Aurelio")
        // No tenemos acceso a person.firstName, ver showCase numero 5


    }

    private fun showCase3() {
        // Diferencia entre INIT y CONSTRUCTOR secundario

        class Person1{
            // Init se va ejecutar antes que el constructor secundario y despues que el contructor primario
            init {
                Log.w("CLASSES-3", "welcome from Init!") // No puedes acceder a firstName o lastName
            }
            constructor( firstName: String, lastName: String){
                Log.w("CLASSES-3", "welcome from Secondary Constructor!")
            }
        }

        class Person2( firstName: String, lastName: String){
            init {
                // Aqui si podemos acceder a las propiedades, conservando init y constructor secundario
            }
            constructor() : this("Name", "lastName")
        }

        val p1 = Person1("name", "lastName")
        val p2 = Person2("Name1", "newlastname")
        val p3 = Person2() // Quedaria por defecto lo pasado en el constructor secundario

    }

    private fun showCase4() {
        // Val, Var o nada en el constructor?

        class Person1(firstName: String) // Aqui no puedes acceder a firstName cuando crees la instancia
        class Person2(var firstName: String) // Puedes acceder a firstname cuando crees la instancia, Puedes cambiar su valor
        class Person3(val firstName: String) // Puedes acceder a firstname cuando crees la instancia, No puedes cambiar su valor


        val p1 = Person1("")
        val p2 = Person2("")
        val p3 = Person3("")

        //p1.firstName // Error
        p2.firstName = "name" // Podemos cambiar el valor
        p3.firstName // No podemos cambiar el valor

    }

    private fun showCase5() {
        // Propiedades - Getters & Setters

        class Person1(var firstName: String)

        val p1 = Person1("name")
        p1.firstName // Seria el equivalente a getfirstName() en Java
        p1.firstName = "Secondname" // Seria el equivalente a setfirstName() en Java

        class Person2{
            // field = representa a la propiedad, nunca utilizar this.propiedad
            var firstName: String
                get() = field.capitalize()
            set(value) {
                field = value.capitalize()


            }
            constructor(firstName: String, lastName: String){
                this.firstName = firstName

            }
        }



    }

    private fun showCase6(){

    }

    private fun showCase7(){

    }

    private fun showCase8(){

    }

    private fun showCase9(){

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
    }
}