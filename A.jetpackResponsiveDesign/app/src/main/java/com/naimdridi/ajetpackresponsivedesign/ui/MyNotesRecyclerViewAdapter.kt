package com.naimdridi.ajetpackresponsivedesign.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.naimdridi.ajetpackresponsivedesign.R
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote
import com.naimdridi.ajetpackresponsivedesign.viewmodel.NewNoteViewModel





class MyNotesRecyclerViewAdapter(
    private var mValues: List<EntityNote>?,
    private val ctx: Context,
    noteViewModel: NewNoteViewModel
) :
    RecyclerView.Adapter<MyNotesRecyclerViewAdapter.ViewHolder>() {
    internal var viewModel: NewNoteViewModel = ViewModelProviders.of(ctx as AppCompatActivity).get(NewNoteViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_notes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues!![position]
        holder.tvTitulo.text = holder.mItem!!.title
        holder.tvContenido.text = holder.mItem!!.content

        if (holder.mItem!!.favorite) {
            holder.ivFavorita.setImageResource(R.drawable.ic_star_black_24dp)
        }

        holder.ivFavorita.setOnClickListener(View.OnClickListener {
            if (holder.mItem!!.favorite) {
                holder.mItem!!.favorite = false
                holder.ivFavorita.setImageResource(R.drawable.ic_star_border_black_24dp)

            } else {
                holder.mItem!!.favorite = true
                holder.ivFavorita.setImageResource(R.drawable.ic_star_black_24dp)
            }
            viewModel.update(holder.mItem!!)
        })
    }

    override fun getItemCount(): Int {
        return mValues!!.size
    }

    fun setNewNotes(newNotes: List<EntityNote>) {
        this.mValues = newNotes
        notifyDataSetChanged()
    }

    inner class ViewHolder( mView: View) : RecyclerView.ViewHolder(mView) {
        val tvTitulo: TextView = mView.findViewById(R.id.textViewTitle)
        val tvContenido: TextView = mView.findViewById(R.id.textViewContent)
        val ivFavorita: ImageView = mView.findViewById(R.id.imageViewStar)
        var mItem: EntityNote? = null

        override fun toString(): String {
            return super.toString() + " '" + tvTitulo.text + "'"
        }
    }
}




