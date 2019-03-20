package com.naimdridi.minitwitter.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.minitwitter.R
import com.naimdridi.minitwitter.Retrofit.AuthTwitterClient
import com.naimdridi.minitwitter.Retrofit.AuthTwitterService
import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class TweetListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var adapter: MyTweetRecyclerViewAdapter? = null
    private lateinit var recycler: RecyclerView
    var tweetList: List<Tweet>? = null
    lateinit var authTwitterService: AuthTwitterService
    lateinit var authTwitterClient: AuthTwitterClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tweet_list, container, false)

        if (view is RecyclerView) {
            val context = view.getContext()
            recycler = view as RecyclerView
            if (columnCount <= 1) {
                recycler.layoutManager = LinearLayoutManager(context)
            } else {
                recycler.layoutManager = GridLayoutManager(context, columnCount)
            }

            retrofitInit()
            loadTweetData()
        }
        return view
    }

    private fun retrofitInit() {
        authTwitterClient = AuthTwitterClient.getInstance()
        authTwitterService = authTwitterClient.getAuthTwitterService()
    }

    private fun loadTweetData(){
        val call = authTwitterService.allTweets
        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    tweetList = response.body()
                    adapter = MyTweetRecyclerViewAdapter(
                        activity!!,
                        tweetList!!
                    )
                    recycler.adapter = adapter
                } else {
                    Toast.makeText(activity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Toast.makeText(activity, "Error en la conexión", Toast.LENGTH_SHORT).show()
            }
        })

    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TweetListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
