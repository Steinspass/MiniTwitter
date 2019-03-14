package com.naimdridi.minitwitter.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.naimdridi.minitwitter.R
import com.naimdridi.minitwitter.Retrofit.MiniTwitterClient
import kotlinx.android.synthetic.main.activity_sign_up.*
import com.naimdridi.minitwitter.Retrofit.MiniTwitterService




class SignUpActivity : AppCompatActivity() {

    lateinit var miniTwitterClient: MiniTwitterClient
    lateinit var miniTwitterService: MiniTwitterService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar!!.hide()
        clicksListeners()
    }



    private fun clicksListeners(){

        buttonSignUp.setOnClickListener {  }
        textView.setOnClickListener { goToLogin() }
    }

    private fun goToLogin(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
