package com.naimdridi.minitwitter.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.minitwitter.R
import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import com.naimdridi.minitwitter.data.TweetViewModel
import android.arch.lifecycle.Observer
import android.os.Build
import kotlinx.android.synthetic.main.fragment_tweet_list.view.*
import android.support.v7.widget.GridLayoutManager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.annotation.Nullable


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


        val context = view.context
        val recyclerView = view.list
        val swipeRefreshLayout = view.swiperefreshlayout
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.colorBlue, resources.newTheme()))
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            loadNewData()
        }

        if (columnCount <= 1) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, columnCount)
        }

        adapter = MyTweetRecyclerViewAdapter(
            activity!!,
            tweetList
        )
        recyclerView.adapter = adapter

        loadTweetData()

        return view
    }



    private fun loadTweetData(){

        tweetViewModel.tweets.observe(activity!!,
            Observer<List<Tweet>> { tweets ->
                tweetList = tweets
                adapter!!.setData(tweetList!!)
            })

    }

    private fun loadNewData() {
        tweetViewModel.getNewTweets().observe(activity!!, object : Observer<List<Tweet>> {
            override fun onChanged(@Nullable tweets: List<Tweet>?) {
                tweetList = tweets
                view!!.swiperefreshlayout.isRefreshing = false
                adapter!!.setData(tweetList!!)
                tweetViewModel.getNewTweets().removeObserver(this)
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
