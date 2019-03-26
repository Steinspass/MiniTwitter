package com.naimdridi.minitwitter.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.naimdridi.minitwitter.R
import com.naimdridi.minitwitter.Retrofit.Response.Tweet
import com.naimdridi.minitwitter.common.Constans
import com.naimdridi.minitwitter.common.SharedPreferencesManager






class MyTweetRecyclerViewAdapter(private val ctx: Context, private var mValues: List<Tweet>?) :
    RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder>() {
    internal var username: String? = null

    init {
        username = SharedPreferencesManager().getSomeStringValue(Constans.PREF_USERNAME)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_tweet, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mValues != null){
            holder.mItem = mValues!![position]

            holder.tvUsername.text = holder.mItem.user!!.username
            holder.tvMessage.text = holder.mItem.mensaje
            holder.tvLikesCount.text = holder.mItem.likes.size.toString()

            val photo = holder.mItem.user!!.photoUrl
            if (photo != "") {
                Glide.with(ctx)
                    .load("https://www.minitwitter.com/apiv1/uploads/photos/" + photo!!)
                    .into(holder.ivAvatar)
            }

            Glide.with(ctx)
                .load(R.drawable.ic_like)
                .into(holder.ivLike)
            //holder.tvLikesCount.setTextColor(ctx.resources.getColor(android.R.color.black))
            holder.tvLikesCount.setTypeface(null, Typeface.NORMAL)

            for (like in holder.mItem.likes) {
                if (like.username == username) {
                    Glide.with(ctx)
                        .load(R.drawable.ic_like_black)
                        .into(holder.ivLike)
                    holder.tvLikesCount.setTextColor(R.color.pink)
                    holder.tvLikesCount.setTypeface(null, Typeface.BOLD)
                    break
                }
            }
        }


    }

    override fun getItemCount(): Int {
        if(mValues != null)
            return mValues!!.size
        else return 0
    }

    fun setData(tweetList: List<Tweet>) {
        this.mValues = tweetList
        notifyDataSetChanged()
    }


    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val ivAvatar: ImageView
        val ivLike: ImageView
        val tvUsername: TextView
        val tvMessage: TextView
        val tvLikesCount: TextView
        lateinit var mItem: Tweet

        init {
            ivAvatar = mView.findViewById(R.id.imageViewAvatar)
            ivLike = mView.findViewById(R.id.imageViewLike)
            tvUsername = mView.findViewById(R.id.textViewUsername)
            tvMessage = mView.findViewById(R.id.textViewMessage)
            tvLikesCount = mView.findViewById(R.id.textViewLikes)
        }

        override fun toString(): String {
            return super.toString() + " '" + tvUsername.text + "'"
        }
    }
}
