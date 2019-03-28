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
import android.support.annotation.Nullable
import com.naimdridi.minitwitter.common.Constans







class TweetListFragment : Fragment() {


    private var tweetListType = 1
    private var adapter: MyTweetRecyclerViewAdapter? = null
    private lateinit var recycler: RecyclerView
    var tweetList: List<Tweet>? = null
    private lateinit var tweetViewModel: TweetViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tweetViewModel = ViewModelProviders.of(activity!!).get(TweetViewModel::class.java)

        arguments?.let {
            tweetListType = it.getInt(Constans.TWEET_LIST_TYPE)
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
            if( tweetListType == Constans.TWEET_LIST_ALL) {
                loadNewData()
            } else if( tweetListType == Constans.TWEET_LIST_FAVS) {
                loadNewFavData()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)


        adapter = MyTweetRecyclerViewAdapter(
            activity!!,
            tweetList
        )
        recyclerView.adapter = adapter

        if( tweetListType == Constans.TWEET_LIST_ALL) {
            loadTweetData()
        } else if( tweetListType == Constans.TWEET_LIST_FAVS) {
            loadFavTweetData()
        }

        return view
    }


    private fun loadNewFavData(){
        tweetViewModel.getNewFavTweets().observe(activity!!, object : Observer<List<Tweet>> {
            override fun onChanged(tweets: List<Tweet>?) {
                tweetList = tweets
                view!!.swiperefreshlayout.isRefreshing = false
                adapter!!.setData(tweetList!!)
                tweetViewModel.getNewFavTweets().removeObserver(this)
            }
        })
    }

    private fun loadFavTweetData(){
        tweetViewModel.getFavTweets().observe(activity!!,
            Observer { tweets ->
                tweetList = tweets
                adapter!!.setData(tweetList!!)
            })
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
        fun newInstance(tweetListType: Int) =
            TweetListFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constans.TWEET_LIST_TYPE, tweetListType)
                }
            }
    }
}
