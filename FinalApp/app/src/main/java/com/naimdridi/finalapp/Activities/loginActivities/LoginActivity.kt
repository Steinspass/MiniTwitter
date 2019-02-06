package com.naimdridi.finalapp.Activities.loginActivities


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.*
import kotlinx.android.synthetic.main.activity_login.*




class LoginActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        clickListeners()

    }

    private fun clickListeners(){

        buttonLogIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (isValidEmail(email) && isValidPassword(password)){
                logInByEmail(email, password)
            }else{
                toast("Please make sure all the data is correct")
            }

        }

        editTextEmail.validate {

            editTextEmail.error = if (isValidEmail(it)) null else " Email is not valid "
        }

        editTextPassword.validate {

            editTextPassword.error = if (isValidPassword(it)) null else " Your password should contain 8 characters length at least"
        }

        textViewForgotPassword.setOnClickListener { goToActivity<ForgotPasswordActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)}


        buttonCreateAccount.setOnClickListener { goToActivity<SignUpActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)}
    }

    private fun logInByEmail(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                if (mAuth.currentUser!!.isEmailVerified){
                    toast("User is now Logged in")
                }else{
                    toast("User must confirm email first")
                }

            }else{
                toast("An unexpected error occurred, please try again")
            }
        }

    }



}
