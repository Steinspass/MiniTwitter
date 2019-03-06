package com.naimdridi.ajetpackresponsivedesign.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.naimdridi.ajetpackresponsivedesign.R
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote
import kotlinx.android.synthetic.main.new_fragment.*
import kotlinx.android.synthetic.main.new_fragment.view.*


class NewNoteFragment : DialogFragment() {

    companion object {
        fun newInstance() = NewNoteFragment()
    }

    private lateinit var noteViewModel: NewNoteViewModel
    private lateinit var _view: View




    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("New Note")
        builder.setMessage("Enter the data of the new note")
            .setPositiveButton("Save the new Note", DialogInterface.OnClickListener { dialog, id ->
                val title: String = editTextTitle.text.toString()
                val content: String = editTextContent.text.toString()
                var color = "blue"
                when(_view.RadioGroupColor.checkedRadioButtonId){
                    R.id.radioButtonColorRed ->{
                        color = "Red"
                    }
                    R.id.radioButtonColorGreen ->{
                        color = "Green"
                    }
                }
                val favorite: Boolean = switchNoteFavorite.isChecked

                noteViewModel = ViewModelProviders.of(activity!!).get(NewNoteViewModel::class.java)

                noteViewModel.insert(EntityNote(title,content,favorite,color))
                dialog.dismiss()

            })
            .setNegativeButton("Cancel the new Note", DialogInterface.OnClickListener { dialog, id ->
                // User cancelled the dialog
                dialog.dismiss()
            })
        val inflate: LayoutInflater = activity!!.layoutInflater
        _view = inflate.inflate(R.layout.new_fragment, null)
        builder.setView(_view)
        // Create the AlertDialog object and return it
        return builder.create()
    }



}
