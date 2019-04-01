package com.naimdridi.minitwitter.data

import com.naimdridi.minitwitter.Retrofit.AuthTwitterClient
import com.naimdridi.minitwitter.Retrofit.AuthTwitterService
import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import android.widget.Toast
import com.naimdridi.minitwitter.common.MyApp
import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.naimdridi.minitwitter.Retrofit.Request.RequestCreateTweet
import com.naimdridi.minitwitter.common.Constans
import com.naimdridi.minitwitter.common.SharedPreferencesManager
import com.naimdridi.minitwitter.Retrofit.Response.TweetDeleted






class TweetRepository internal constructor() {
    internal var authTwitterService: AuthTwitterService
    internal var authTwitterClient: AuthTwitterClient
    internal var allTweets: MutableLiveData<List<Tweet>>
    var favTweets: MutableLiveData<List<Tweet>>? = null
    var userName: String? = null

    init {
        authTwitterClient = AuthTwitterClient.getInstance()
        authTwitterService = authTwitterClient.getAuthTwitterService()
        allTweets = getAllTweets()
        userName = SharedPreferencesManager().getSomeStringValue(Constans.PREF_USERNAME)
    }

    fun getAllTweets(): MutableLiveData<List<Tweet>> {
        if (allTweets == null) {
            allTweets = MutableLiveData()
        }



        val call = authTwitterService.allTweets
        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    allTweets.setValue(response.body())
                } else {
                    Toast.makeText(MyApp.context, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error en la conexión", Toast.LENGTH_SHORT).show()
            }
        })

        return allTweets
    }

    fun getFavsTweets(): MutableLiveData<List<Tweet>> {
        if (favTweets == null)
            favTweets = MutableLiveData()

        val newFavList = ArrayList<Tweet>()
        val itTweets = allTweets.value!!.iterator()

        while (itTweets.hasNext()) {
            val current = itTweets.next()
            val itLikes = current.likes.iterator()
            var enc = false
            while (itLikes.hasNext() && !enc) {
                if (itLikes.next().username == userName) {
                    enc = true
                    newFavList.add(current)
                }
            }
        }

        favTweets!!.value = newFavList

        return favTweets as MutableLiveData<List<Tweet>>
    }


    fun createTweet(mensaje: String) {
        val requestCreateTweet = RequestCreateTweet(mensaje)
        val call = authTwitterService.createTweet(requestCreateTweet)

        call.enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    val listClone = ArrayList<Tweet>()
                    // Añadimos en primer lugar el nuevo tweet que nos llega del server
                    listClone.add(response.body()!!)
                    for (i in 0 until allTweets.value!!.size) {
                        listClone.add(Tweet(allTweets.value!![i]))
                    }
                    allTweets.value = listClone
                } else {
                    Toast.makeText(MyApp.context, "Algo ha ido mal, inténtelo de nuevo", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error en la conexión. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun likeTweet(idTweet: Int) {
        val call = authTwitterService.likeTweet(idTweet)

        call.enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    val listaClonada = ArrayList<Tweet>()

                    for (i in 0 until allTweets.value!!.size) {
                        if (allTweets.value!![i].id == idTweet) {
                            // Si hemos encontrado en la lista original
                            // el elemento sobre el que hemos hecho like,
                            // introducimos el elemento que nos ha llegado del
                            // servidor
                            listaClonada.add(response.body()!!)
                        } else {
                            listaClonada.add(Tweet(allTweets.value!![i]))
                        }
                    }
                    allTweets.setValue(listaClonada)
                } else {
                    Toast.makeText(MyApp.context, "Algo ha ido mal, inténtelo de nuevo", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error en la conexión. Inténtelo de nuevo.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun deleteTweet(idTweet: Int) {
        val call = authTwitterService.deleteTweet(idTweet)

        call.enqueue(object : Callback<TweetDeleted> {
            override fun onResponse(call: Call<TweetDeleted>, response: Response<TweetDeleted>) {
                if (response.isSuccessful) {
                    val clonedTweets = ArrayList<Tweet>()
                    for (i in 0 until allTweets.value!!.size) {
                        if (allTweets.value!![i].id != idTweet) {
                            clonedTweets.add(Tweet(allTweets.value!![i]))
                        }
                    }

                    allTweets.value = clonedTweets
                    getFavsTweets()
                } else {
                    Toast.makeText(MyApp.context, "Algo ha ido mal, inténtelo de nuevo", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TweetDeleted>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error en la conexión. Inténtelo de nuevo.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}