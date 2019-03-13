package com.naimdridi.minitwitter.Retrofit

import com.naimdridi.minitwitter.common.Constans
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MiniTwitterClient {
    private val miniTwitterService: MiniTwitterService
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constans.API_MINITWITTER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    init {

        miniTwitterService = retrofit.create(MiniTwitterService::class.java)
    }

    companion object {
        private var instance: MiniTwitterClient? = null

        // Patrón Singleton
        fun getInstance(): MiniTwitterClient {
            if (instance == null) {
                instance = MiniTwitterClient()
            }
            return instance as MiniTwitterClient
        }
    }




}