package com.naimdridi.ajetpackresponsivedesign.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.ajetpackresponsivedesign.R
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote




class NotesFragment : Fragment() {


    // TODO: Customize parameters
    private var columnCount = 3
    private lateinit var noteViewModel: NewNoteViewModel

    private lateinit var notesList: List<EntityNote>
    private val adapterNotes: MyNotesRecyclerViewAdapter? = null




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
                adapter = MyNotesRecyclerViewAdapter(notesList, context)
                goToViewModel()
            }
        }
        return view
    }

    private fun goToViewModel(){
        noteViewModel = ViewModelProviders.of(this).get(NewNoteViewModel::class.java)
        noteViewModel.allNotes.observe(this,
            Observer<List<EntityNote>> {
                adapterNotes?.setNewsNotes(it!!)
            })


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






}
