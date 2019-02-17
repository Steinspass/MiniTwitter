package com.naimdridi.finalapp.Activities.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naimdridi.finalapp.Activities.Models.Rate
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.inflateM
import kotlinx.android.synthetic.main.fragment_rate_item.view.*
import java.text.SimpleDateFormat

class RateAdapter(private val items: List<Rate>): RecyclerView.Adapter<RateAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflateM(R.layout.fragment_rate_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(rate: Rate) {
            itemView.textViewRate.text = rate.text
            itemView.textViewStar.text = rate.rate.toString()
            itemView.textViewCalendar.text = SimpleDateFormat("dd, MMM, yyyy").format(rate.createdAt)
            Glide.with(itemView).load(rate.profileImgURL)
                .apply(RequestOptions.circleCropTransform().override(60, 60))
                .into(itemView.imageViewProfile)

        }
    }

}