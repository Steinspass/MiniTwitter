package com.naimdridi.finalapp.Activities.loginActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.goToActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        buttonGoLogIn.setOnClickListener {
            goToActivity<LoginActivity>{ flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK } }
    }
}
