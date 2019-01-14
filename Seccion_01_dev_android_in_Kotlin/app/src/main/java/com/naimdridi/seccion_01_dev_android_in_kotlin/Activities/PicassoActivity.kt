package com.naimdridi.seccion_01_dev_android_in_kotlin.Activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.naimdridi.seccion_01_dev_android_in_kotlin.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*
import java.lang.Exception

class PicassoActivity : AppCompatActivity() {

    private lateinit var toolbar: android.support.v7.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        buttonLoad.setOnClickListener { loadImages() }

       //Picasso.get().load().fetch("https://images4.alphacoders.com/982/982990.jpg")
    }

    private fun loadImages(){
        Picasso
            .get()
            .load("https://images4.alphacoders.com/982/982990.jpg")
            .resize(400, 400)
            .centerInside()
            .into(imageViewTop)

        //Picasso
        //    .get()
        //    .load("https://images5.alphacoders.com/979/979758.jpg")
        //    .fit()
        //    .into(imageViewButton)
        //
        //
        //Picasso
        //    .get()
        //    .load("https://images5.alphacoders.com/979/979758.jpg")
        //    .fit()
        //    .transform(CircleTransform()) // podemos renderizar como un circulo
        //    .into(imageViewButton)

        Picasso
            .get()
            .load("https://images5.alphacoders.com/979/979758.jpg")
            .fetch(object: Callback{
                override fun onSuccess() {
                    imageViewButton.alpha = 0f
                    Picasso.get()
                        .load("https://images5.alphacoders.com/979/979758.jpg")
                        .fit()
                        .into(imageViewButton)
                    imageViewButton.animate().setDuration(1300).alpha(1f).start()

                }

                override fun onError(e: Exception?) {

                }

            })

    }

}
