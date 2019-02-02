package com.naimdridi.finalapp.Activities.loginActivities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.toast




class SignUpActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val currentUser = mAuth.currentUser
        if (currentUser !== null){
            toast("User is NOT logged in")
            createAccount("shady221.SH@gmail.com", "1234576889")
        }else{
            toast("User IS logged in")
        }



    }

    private fun createAccount(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    toast("createUserWithEmail:success")
                    val user = mAuth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    toast("createUserWithEmail:failure")


                }


            }
    }


}
