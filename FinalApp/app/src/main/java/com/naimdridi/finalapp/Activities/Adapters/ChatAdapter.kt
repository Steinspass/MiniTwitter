package com.naimdridi.finalapp.Activities.Adapters


import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naimdridi.finalapp.Activities.Models.Message
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.inflateM
import kotlinx.android.synthetic.main.fragment_chat_item_left.view.*
import kotlinx.android.synthetic.main.fragment_chat_item_right.view.*
import java.text.SimpleDateFormat

class ChatAdapter(val items: List<Message>, val userId: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val GLOBAL_MESSAGE = 1
    private val MY_MESSAGE = 2

    private val layoutRight = R.layout.fragment_chat_item_right
    private val layoutLeft = R.layout.fragment_chat_item_left


    override fun getItemViewType(position: Int) = if (items[position].authorId == userId) MY_MESSAGE else GLOBAL_MESSAGE


    override fun getItemCount() = items.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when(viewType){
                MY_MESSAGE -> ViewHolderR(parent.inflateM(layoutRight))
                else -> ViewHolderL(parent.inflateM(layoutLeft))
            }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            MY_MESSAGE -> (holder as ViewHolderR).bind(items[position])
            GLOBAL_MESSAGE -> (holder as ViewHolderL).bind(items[position])
        }
    }

    class ViewHolderR(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(message: Message) = with(itemView){
            textViewMessageRight.text = message.message
            textViewTimeRight.text = SimpleDateFormat("hh:mm").format(message.sentAt)
            // Glide load image here
            Glide.with(context).load(message.profileImageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(imageViewProfileRight)

        }

        }


    class ViewHolderL(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(message: Message) = with(itemView){
            textViewMessageLeft.text = message.message
            textViewTimeLeft.text = SimpleDateFormat("hh:mm").format(message.sentAt)
            // Glide load image here
            Glide.with(context).load(message.profileImageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(imageViewProfileLeft)
        }
    }

}