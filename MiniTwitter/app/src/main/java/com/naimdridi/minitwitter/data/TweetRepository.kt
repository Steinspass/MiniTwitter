package com.naimdridi.minitwitter.data

import com.naimdridi.minitwitter.Retrofit.AuthTwitterClient
import com.naimdridi.minitwitter.Retrofit.AuthTwitterService
import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import android.arch.lifecycle.LiveData
import android.widget.Toast
import com.naimdridi.minitwitter.common.MyApp
import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class TweetRepository internal constructor() {
    internal var authTwitterService: AuthTwitterService
    internal var authTwitterClient: AuthTwitterClient
    internal var allTweets: LiveData<List<Tweet>>

    init {
        authTwitterClient = AuthTwitterClient.getInstance()
        authTwitterService = authTwitterClient.getAuthTwitterService()
        allTweets = getAllTweets()
    }

    fun getAllTweets(): LiveData<List<Tweet>> {
        val data = MutableLiveData<List<Tweet>>()

        val call = authTwitterService.allTweets
        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    data.setValue(response.body())
                } else {
                    Toast.makeText(MyApp.context, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error en la conexión", Toast.LENGTH_SHORT).show()
            }
        })

        return data
    }
}