package com.naimdridi.ajetpackresponsivedesign.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL
import android.view.*
import com.naimdridi.ajetpackresponsivedesign.R
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote
import com.naimdridi.ajetpackresponsivedesign.viewmodel.NewNoteViewModel




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
        // Indicamos que el fragmento tiene un menu de opciones propio
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notes_list, container, false)
        // Set the adapter

        if (view is RecyclerView) {
            with(view) {
                val displayMetrics = context.resources.displayMetrics
                val dpWidth = displayMetrics.widthPixels / displayMetrics.density
                val numColumns = (dpWidth / 180).toInt()
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> StaggeredGridLayoutManager(numColumns , VERTICAL)
                }
                notesList = ArrayList()
                noteViewModel = ViewModelProviders.of(activity!!).get(NewNoteViewModel::class.java)
                adapter = MyNotesRecyclerViewAdapter(notesList, context, noteViewModel)
                view.adapter = adapter
                goToViewModel()

            }
        }
        return view
    }


    private fun goToViewModel(){
        noteViewModel = ViewModelProviders.of(activity!!).get(NewNoteViewModel::class.java)
        noteViewModel.allNotes.observe(activity!!,
            Observer<List<EntityNote>> {
                adapterNotes?.setNewNotes(it!!)
            })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add_note ->{
               dialogNewNote()

            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun dialogNewNote(){
       val fm: FragmentManager = activity!!.supportFragmentManager
        val dialogNote = NewNoteFragment()
        dialogNote.show(fm, "NewNoteDialogFragment")
        newInstance(columnCount)
        NewNoteFragment().newInstance()

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
