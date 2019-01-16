package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.naimdridi.seccion_01_dev_android_in_kotlin.R
import kotlinx.android.synthetic.main.activity_permissions.*


class PermissionsActivity : AppCompatActivity() {

    private val requestCameraPermission = 100
    private val requestCameraPicture = 200

    private lateinit var toolbar: android.support.v7.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        buttonPicture.setOnClickListener { getPictureFromCameraAskingPermissions() }
    }

    // Utilizar la camara sin permisos
    private fun getPictureFromCamera(){
        // Asegurararnos de que no hay permisos de camera en el manifest
        // Crear intent para capturar la foto
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Comprobar que podemos manejar la captura de fotos (Tenemos camara y app de camara)
        if (pictureIntent.resolveActivity(packageManager) != null){
            startActivityForResult(pictureIntent, requestCameraPicture)
        }else{
            // No hay activity que pueda manejar el intent (por ejemplo sin camara)
            Toast.makeText(this, "Picture has failed", Toast.LENGTH_SHORT).show()

        }

    }

    // Utilizar la camara con permisos
    private fun getPictureFromCameraAskingPermissions(){
        // 1) Agragar permiso al manifest
        // 2) Comprobar el permiso de la camara

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // Si no ha sido aceptado previamente (Para versiones desde la 6.0 [API 23] en adelante
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), requestCameraPermission)
        }else{
            // Si ha sido aceptado previamente (Para las versiones inferiores a la 6.0 [API 23])
            goToCameraApp()

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            requestCameraPermission -> {
                if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // Permiso aceptado
                    goToCameraApp()
                }else{
                   // Permiso denegado
                    Toast.makeText(this, "You can't take a picture if you don't allow it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            requestCameraPicture -> {
                // Comprobar si el resultado no viene null
                if (resultCode == Activity.RESULT_OK){
                    // Obtenemos los extras del intent recibido por los parametros
                    val extras = data!!.extras
                    //Formamos el bitmap a apartir de los extras
                    val imageBitMap = extras.get("data") as Bitmap
                    // Cargamos la foto como bitmap en el imageView
                    imageViewPicture.setImageBitmap(imageBitMap)
                }else{
                    // La foto no sido tomada con exito
                    Toast.makeText(this, "Picture has failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun goToCameraApp(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, requestCameraPicture)
    }
}
