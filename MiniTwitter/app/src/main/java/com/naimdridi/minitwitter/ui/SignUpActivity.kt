package com.naimdridi.minitwitter.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.naimdridi.minitwitter.R
import com.naimdridi.minitwitter.Retrofit.MiniTwitterClient
import kotlinx.android.synthetic.main.activity_sign_up.*
import com.naimdridi.minitwitter.Retrofit.MiniTwitterService
import com.naimdridi.minitwitter.Retrofit.Request.RequestSignUp
import com.naimdridi.minitwitter.Retrofit.Response.ResponseAuth
import com.naimdridi.minitwitter.common.Constans
import com.naimdridi.minitwitter.common.SharedPreferencesManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {

    lateinit var miniTwitterClient: MiniTwitterClient
    lateinit var miniTwitterService: MiniTwitterService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar!!.hide()
        retrofitInit()
        clicksListeners()
    }

    private fun retrofitInit() {
        miniTwitterClient = MiniTwitterClient.getInstance()
        miniTwitterService = miniTwitterClient.getMiniTwitterService()
    }

    private fun signUpUsernameAndEmail(){
        val username = editTextUsername.text.toString()
        val email = editTextSignUpEmail.text.toString()
        val password = editTextSignUpPassword.text.toString()

        if (username.isEmpty()|| username.length<4){
            editTextUsername.error = "The username is required and must have 4 characters"
        }else if (email.isEmpty()){
            editTextSignUpEmail.error = "The email is required"
        }else if (password.isEmpty()|| password.length<8){
            editTextSignUpPassword.error = "The password "
        }else{
            val code = "UDEMYANDROID"
            val request = RequestSignUp(username, email, password, code)

           val call = miniTwitterService.doSignUp(request)

            call.enqueue(object: Callback<ResponseAuth>{

                override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
                        if (response.isSuccessful){

                            SharedPreferencesManager().setSomeStringValue(Constans.PREF_TOKEN, response.body()?.token!!)
                            SharedPreferencesManager().setSomeStringValue(Constans.PREF_USERNAME, response.body()?.username!!)
                            SharedPreferencesManager().setSomeStringValue(Constans.PREF_EMAIL, response.body()?.email!!)
                            SharedPreferencesManager().setSomeStringValue(Constans.PREF_PHOTOURL, response.body()?.photoUrl!!)
                            SharedPreferencesManager().setSomeStringValue(Constans.PREF_CREATED, response.body()?.created!!)
                            SharedPreferencesManager().setSomeBooleanValue(Constans.PREF_ACTIVE, response.body()?.active!!)

                            val intent = Intent(this@SignUpActivity, DashboardActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@SignUpActivity, "Something went wrong check your access data", Toast.LENGTH_SHORT).show()
                        }
                }

                override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, "Connection Failed, try Again", Toast.LENGTH_SHORT).show()
                }

            })


        }
    }


    private fun clicksListeners(){

        buttonSignUp.setOnClickListener { signUpUsernameAndEmail() }
        textView.setOnClickListener { goToLogin() }
    }

    private fun goToLogin(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
