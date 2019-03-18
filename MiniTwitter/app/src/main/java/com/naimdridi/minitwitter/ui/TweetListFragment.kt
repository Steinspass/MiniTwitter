package com.naimdridi.minitwitter.ui

import android.content.Context
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









class TweetListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var adapter: MyTweetRecyclerViewAdapter? = null
    private lateinit var recycler: RecyclerView
    var tweetList: List<Tweet>? = null

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

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                loadTweetData()
            }
        }
        return view
    }

    private fun loadTweetData(){
        adapter = MyTweetRecyclerViewAdapter(
            activity,
            tweetList
        )
        recycler.adapter = adapter
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
