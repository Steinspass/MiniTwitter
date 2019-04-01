package com.naimdridi.minitwitter

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.naimdridi.minitwitter.common.Constans
import com.naimdridi.minitwitter.data.TweetViewModel
import kotlinx.android.synthetic.main.bottom_modal_tweet_fragment.view.*


class BottomModalTweetFragment : BottomSheetDialogFragment() {

    private lateinit var tweetViewModel: TweetViewModel
    private var idTweetEliminar: Int = 0

    fun newInstance(idTweet: Int): BottomModalTweetFragment {
        val fragment = BottomModalTweetFragment()
        val args = Bundle()
        args.putInt(Constans.ARG_TWEET_ID, idTweet)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            idTweetEliminar = arguments!!.getInt(Constans.ARG_TWEET_ID)
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.bottom_modal_tweet_fragment, container, false)

        val nav = view.navigation_view_bottom_tweet
        nav.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                val id = menuItem.itemId

                if (id == R.id.action_delete_tweet) {
                    tweetViewModel.deleteTweet(idTweetEliminar)
                    dialog.dismiss()
                    return true
                }

                return false
            }
        })



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tweetViewModel = ViewModelProviders.of(this).get(TweetViewModel::class.java)
    }

}
