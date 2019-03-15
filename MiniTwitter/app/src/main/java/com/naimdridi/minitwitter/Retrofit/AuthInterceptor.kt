package com.naimdridi.minitwitter.Retrofit

import com.naimdridi.minitwitter.common.Constans
import okhttp3.Interceptor
import okhttp3.Response
import com.naimdridi.minitwitter.common.SharedPreferencesManager



class AuthInterceptor: Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SharedPreferencesManager().getSomeStringValue(Constans.PREF_TOKEN)
        val request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token!!).build()
        return chain.proceed(request)
    }

}