package com.naimdridi.minitwitter.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.minitwitter.R
import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import com.naimdridi.minitwitter.data.TweetViewModel
import android.arch.lifecycle.Observer







class TweetListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var adapter: MyTweetRecyclerViewAdapter? = null
    private lateinit var recycler: RecyclerView
    var tweetList: List<Tweet>? = null
    private lateinit var tweetViewModel: TweetViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tweetViewModel = ViewModelProviders.of(activity!!).get(TweetViewModel::class.java)
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

            adapter = MyTweetRecyclerViewAdapter(
                activity!!,
                tweetList
            )
            recycler.adapter = adapter

            loadTweetData()
        }
        return view
    }



    private fun loadTweetData(){

        tweetViewModel.tweets.observe(activity!!,
            Observer<List<Tweet>> { tweets ->
                tweetList = tweets
                adapter!!.setData(tweetList!!)
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
