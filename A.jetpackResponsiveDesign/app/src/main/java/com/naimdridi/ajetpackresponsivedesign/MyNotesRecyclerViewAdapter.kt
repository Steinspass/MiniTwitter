package com.naimdridi.ajetpackresponsivedesign

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup




import kotlinx.android.synthetic.main.fragment_notes.view.*


class MyNotesRecyclerViewAdapter(
    private val mValues: List<Note>,
    private val mListener: NotesIteractionListener?
) : RecyclerView.Adapter<MyNotesRecyclerViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_notes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.title.text = item.title
        holder.content.text = item.content
        if (item.favorite){
            holder.stars.setImageResource(R.drawable.ic_star_black_24dp)
        }

        holder.stars.setOnClickListener {
            if (null != mListener){
                mListener.favoriteNoteClick(item)
            }
        }

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title = mView.textViewTitle
        val content = mView.textViewContent
        val stars = mView.imageViewStar


        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }

    }


}
