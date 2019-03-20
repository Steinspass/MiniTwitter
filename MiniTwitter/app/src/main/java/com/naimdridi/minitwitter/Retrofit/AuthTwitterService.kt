package com.naimdridi.minitwitter.Retrofit

import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import retrofit2.Call
import retrofit2.http.GET

interface AuthTwitterService {

    @get:GET("tweets/all")
    val allTweets: Call<List<Tweet>>
}