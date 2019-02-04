package com.naimdridi.finalapp.Activities.loginActivities


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.goToActivity
import com.naimdridi.my_library_second.Interfaces.Others.toast
import kotlinx.android.synthetic.main.activity_login.*




class LoginActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        clickListeners()

        if(mAuth.currentUser === null){
            toast("Nope")
        }else{
            toast("Yep")
            mAuth.signOut()
        }


    }


    private fun logInByEmail(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                toast("User is now Logged in")
            }else{
                toast("An unexpected error occurred, please try again")
            }
        }

    }

    private fun isValidEmailAndPassword(email: String, password: String): Boolean{
        return !email.isNullOrEmpty() && !password.isNullOrEmpty()
    }


    private fun clickListeners(){

        buttonLogIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (isValidEmailAndPassword(email, password)){
                logInByEmail(email, password)
            }

        }

        textViewForgotPassword.setOnClickListener { goToActivity<ForgotPasswordActivity>() }
        buttonCreateAccount.setOnClickListener { goToActivity<SignUpActivity>() }
    }

}
