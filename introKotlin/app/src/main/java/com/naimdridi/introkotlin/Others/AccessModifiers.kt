package com.naimdridi.introkotlin.Others


    //  	DOC: #https://kotlinlang.org/docs/reference/visibility-modifiers.html#classes-and-interfaces


open class AccessModifiers {

    val prop1 = "Public by default"
    private val prop2 = "Marked as private"
    protected val prop3 = "Marked as Protected"

   protected fun  showCase(){
       val test = AccessModifiers()
       test.prop1 // Para hacer refenrencia a el AccessModifiers.val prop1
       //this.prop1 // A la variable prop1 sin mencionar la clase

   }
}

class AccessModifiersChild : AccessModifiers() {

    private fun showCaseX(){
        super.showCase()
        prop1
        // prop2 // Por ser private no puede ser accsesible
        prop3

    }
}

class AnotherClass {

    private fun showCase() {
        val test = AccessModifiers()
        test.prop1
    }
}