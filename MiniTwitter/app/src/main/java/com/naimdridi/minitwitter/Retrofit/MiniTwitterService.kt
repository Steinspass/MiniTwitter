package com.naimdridi.minitwitter.Retrofit

import retrofit2.http.POST
import com.naimdridi.minitwitter.RequestLogin
import com.naimdridi.minitwitter.RequestSignUp
import com.naimdridi.minitwitter.ResponseAuth
import retrofit2.Call
import retrofit2.http.Body




interface MiniTwitterService {

    @POST("/auth/login")
    fun doLogin(@Body requestLogin: RequestLogin): Call<ResponseAuth>

    @POST("auth/signup")
    fun doSignUp(@Body requestSignUp: RequestSignUp): Call<ResponseAuth>
}