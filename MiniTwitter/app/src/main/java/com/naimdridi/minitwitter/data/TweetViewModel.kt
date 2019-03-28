package com.naimdridi.minitwitter.data

import android.arch.lifecycle.AndroidViewModel
import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import android.arch.lifecycle.LiveData
import android.app.Application
import android.arch.lifecycle.MutableLiveData












class TweetViewModel(application: Application) : AndroidViewModel(application) {
    private val tweetRepository: TweetRepository
    var tweets: LiveData<List<Tweet>>
    private var favTweets: LiveData<List<Tweet>>? = null

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

    fun getFavTweets(): LiveData<List<Tweet>> {
        favTweets = tweetRepository.getFavsTweets()
        return favTweets as MutableLiveData<List<Tweet>>
    }

    fun getNewFavTweets(): LiveData<List<Tweet>> {
        getNewTweets()
        return getFavTweets()
    }

    fun likeTweet(idTweet: Int){
        tweetRepository.likeTweet(idTweet)
    }
}