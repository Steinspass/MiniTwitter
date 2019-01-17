package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.naimdridi.seccion_01_dev_android_in_kotlin.MainActivity
import com.naimdridi.seccion_01_dev_android_in_kotlin.Others.*
import com.naimdridi.seccion_01_dev_android_in_kotlin.R
import kotlinx.android.synthetic.main.activity_extensions_funcions.*

class ExtensionsFuncionsActivity : AppCompatActivity() {

    private lateinit var toolbar: android.support.v7.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extensions_funcions)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        buttonsListener()


    }



    private fun buttonsListener(){
        buttonToast.setOnClickListener { toastId(R.string.message_toast_ext_fun, duration = Toast.LENGTH_LONG) }
        buttonSnackBar.setOnClickListener { snackBar("Has hecho click en Button SnackBar",action = "Undo"){
            toast("No has hecho click en el Button SnackBar")
        } }
        buttonLoadUrl.setOnClickListener { imageViewLoadByUrl.loadByUrl("https://images3.alphacoders.com/978/978454.jpg") }
        buttonGoToActivity.setOnClickListener { goToActivity<MainActivity> { toast("Ahora estamos en el MainActivity") } }
    }
}
