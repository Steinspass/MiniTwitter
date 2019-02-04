package com.naimdridi.finalapp.Activities.loginActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.goToActivity
import com.naimdridi.my_library_second.Interfaces.Others.toast
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        clickListener()

    }

    private fun singUpByEmail(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("An email has been sent to you. Please confirm before sing in")

                } else {
                    toast("An expected error occurred, please try again ")

                }


            }
    }

    private fun isValidEmailAndPassword(email: String, password: String): Boolean{

        return !email.isNullOrEmpty() && !password.isNullOrEmpty() &&
                password === editTextConfirmPassword.text.toString()
    }

    private fun clickListener(){


        buttonGoLogIn.setOnClickListener {
            goToActivity<LoginActivity>{ flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK} }

        buttonSingUP.setOnClickListener {
            val email = editTextEmailSignUp.text.toString()
            val password = editTextPasswordSignUp.text.toString()

            if (isValidEmailAndPassword(email, password)){
                singUpByEmail(email, password)
            }else{
                toast("Please fill all the data and confirm password is correct")
            }
        }

    }



}
