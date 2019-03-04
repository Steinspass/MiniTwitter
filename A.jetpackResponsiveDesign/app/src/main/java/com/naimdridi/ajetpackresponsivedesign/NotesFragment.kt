package com.naimdridi.ajetpackresponsivedesign

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class NotesFragment : Fragment() {


    // TODO: Customize parameters
    private var columnCount = 3

    private lateinit var notesList: List<EntityNote>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notes_list, container, false)



        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                val displayMetrics = context!!.resources.displayMetrics
                val dpWidth = displayMetrics.widthPixels / displayMetrics.density
                val numColumns = (dpWidth / 180).toInt()
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> StaggeredGridLayoutManager(numColumns , VERTICAL)
                }

                notesList = getNotes()
                adapter = MyNotesRecyclerViewAdapter(notesList, context)

            }

        }
        return view
    }




    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            NotesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    private fun getNotes(): List<EntityNote>{
        return listOf(
            EntityNote("Lista de la compra", "leche 1, cereales 2, fruta variada", true, android.R.color.holo_blue_dark),
            EntityNote("recoger", "leche 1, cereales 2, fruta variada", false, android.R.color.darker_gray),
            EntityNote("Quedada", "Malaga centro ", true, android.R.color.holo_orange_dark),
            EntityNote("Vaacaciones", "sitios a los que ir .......", false, android.R.color.holo_purple)
        )
    }




}
