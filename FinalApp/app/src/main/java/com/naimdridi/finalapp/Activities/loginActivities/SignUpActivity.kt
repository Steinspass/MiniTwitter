package com.naimdridi.finalapp.Activities.loginActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern


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
                    goToActivity<LoginActivity>{ flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK}
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
            goToActivity<LoginActivity>{ flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK}
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out) }



        buttonSingUP.setOnClickListener {
            val email = editTextEmailSignUp.text.toString()
            val password = editTextPasswordSignUp.text.toString()

            if (isValidEmailAndPassword(email, password)){
                singUpByEmail(email, password)

            }else{
                toast("Please fill all the data and confirm password is correct")
            }

            editTextEmailSignUp.validate {

                editTextEmailSignUp.error = if (isValidEmail(it)) null else " Email is not valid "
            }

            editTextPasswordSignUp.validate {

                editTextPasswordSignUp.error = if (isValidPassword(it)) null else " Password should contain 1 lowercase, 1 uppercase, 1 number, 8 characters length at least"
            }

            editTextConfirmPassword.validate {

                editTextConfirmPassword.error = if (isValidConfirmPassword(editTextConfirmPassword.text.toString(), it)) null else " Confirm Password does not match with Password"
            }
        }

    }



}
