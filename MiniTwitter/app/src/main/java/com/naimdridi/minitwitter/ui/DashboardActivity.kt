package com.naimdridi.minitwitter.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.naimdridi.minitwitter.R
import com.naimdridi.minitwitter.common.Constans
import com.naimdridi.minitwitter.common.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_dashboard.*




class DashboardActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tweets_likes -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportActionBar!!.hide()

        val navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, TweetListFragment())
            .commit()

        setUpFab()

        val photoUrl = SharedPreferencesManager().getSomeStringValue(Constans.PREF_PHOTOURL)
        if (photoUrl!!.isNotEmpty()) {
            Glide.with(this)
                .load(Constans.API_MINITWITTER_FILES_URL + photoUrl)
                .into(this.imageViewToolbarPhoto)
        }

    }

    private fun setUpFab(){

        fab.setOnClickListener {
           val dialog = NewTweetDialogFragment()
            dialog.show(supportFragmentManager, "NewTweetDialogFragment")

        }
    }
}
