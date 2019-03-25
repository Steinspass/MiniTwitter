package com.naimdridi.minitwitter.Retrofit

import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import retrofit2.Call
import retrofit2.http.GET
import com.naimdridi.minitwitter.Retrofit.Request.RequestCreateTweet
import retrofit2.http.POST
import retrofit2.http.Body


interface AuthTwitterService {

    @get:GET("tweets/all")
    val allTweets: Call<List<Tweet>>

    @POST("tweets/create")
    fun createTweet(@Body requestCreateTweet: RequestCreateTweet): Call<Tweet>
}