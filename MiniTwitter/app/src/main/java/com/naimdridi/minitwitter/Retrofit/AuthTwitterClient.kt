package com.naimdridi.minitwitter.Retrofit

import com.naimdridi.minitwitter.common.Constans
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient


class AuthTwitterClient {
     private val authTwitterService: AuthTwitterService
    private val retrofit: Retrofit

    init {
        // Incluir en la cabecera de la petición el TOKEN que autoriza al usuario
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(AuthInterceptor())
        val client = okHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constans.API_MINITWITTER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        authTwitterService = retrofit.create(AuthTwitterService::class.java)
    }

    companion object {
        private var instance: AuthTwitterClient? = null

        // Patrón Singleton
        fun getInstance(): AuthTwitterClient {
            if (instance == null) {
                instance = AuthTwitterClient()
            }
            return instance as AuthTwitterClient
        }
    }

    fun getAuthTwitterService(): AuthTwitterService {
        return authTwitterService
    }


}