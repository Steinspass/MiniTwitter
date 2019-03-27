package com.naimdridi.minitwitter.data

import android.arch.lifecycle.AndroidViewModel
import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import android.arch.lifecycle.LiveData
import android.app.Application





class TweetViewModel(application: Application) : AndroidViewModel(application) {
    private val tweetRepository: TweetRepository
    var tweets: LiveData<List<Tweet>>

    init {
        tweetRepository = TweetRepository()
        tweets = tweetRepository.getAllTweets()
    }

    fun insertTweet(mensaje: String) {
        tweetRepository.createTweet(mensaje)
    }

    fun getNewTweets(): LiveData<List<Tweet>> {
        tweets = tweetRepository.getAllTweets()
        return tweets
    }
    fun likeTweet(idTweet: Int){
        tweetRepository.likeTweet(idTweet)
    }
}