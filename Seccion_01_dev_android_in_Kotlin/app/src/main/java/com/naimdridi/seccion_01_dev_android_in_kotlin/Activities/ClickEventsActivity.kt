package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.naimdridi.seccion_01_dev_android_in_kotlin.R

class ClickEventsActivity : AppCompatActivity(), View.OnLongClickListener {


    // 1) Click en XML, no utilizar ya que agrega logica en el XML que es donde deberia haber solo parte grafica y de diseño
    // 2) Click en linea
    // 3) Click by IDs en el 'when'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_events)
        clickInLine()
        ClickLongListener()


    }

    //Regla obligatoria para clicks en XMLS:
    // El metodo tiene que se publico, nombres tiene que coincidir y recibe un solo parametro(View)

    fun xmlClick(view: View){
        Toast.makeText(this, "Click by XML!", Toast.LENGTH_SHORT).show()
    }

    private fun clickInLine(){
        val btn = findViewById<Button>(R.id.buttonClickInLine)
        btn.setOnClickListener { Toast.makeText(this, "Click in Line!", Toast.LENGTH_SHORT).show() }
    }

    private fun ClickLongListener(){
        val btn1 = findViewById<Button>(R.id.buttonClickMulti1)
        val btn2 = findViewById<Button>(R.id.buttonClickMulti2)
        val btn3 = findViewById<Button>(R.id.buttonClickMulti3)

        btn1.setOnLongClickListener(this)
        btn2.setOnLongClickListener(this)
        btn3.setOnLongClickListener(this)
    }

    override fun onLongClick(view: View): Boolean {
        when(view.id){
            R.id.buttonClickMulti1 -> Toast.makeText(this, "Click Multi 1!", Toast.LENGTH_SHORT).show()
            R.id.buttonClickMulti2 -> Toast.makeText(this, "Click Multi 2!", Toast.LENGTH_SHORT).show()
            R.id.buttonClickMulti3 -> Toast.makeText(this, "Click Multi 3!", Toast.LENGTH_SHORT).show()
        }
        return true
    }


}
