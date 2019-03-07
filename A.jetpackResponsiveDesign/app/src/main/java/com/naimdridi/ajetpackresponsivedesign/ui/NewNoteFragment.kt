package com.naimdridi.ajetpackresponsivedesign.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.naimdridi.ajetpackresponsivedesign.R
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote
import com.naimdridi.ajetpackresponsivedesign.viewmodel.NewNoteViewModel
import kotlinx.android.synthetic.main.new_fragment.view.*




class NewNoteFragment : DialogFragment() {



    fun newInstance(): NewNoteFragment {
        return NewNoteFragment()
    }

    private lateinit var noteViewModel: NewNoteViewModel
    private lateinit var _view: View




    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val inflate: LayoutInflater = activity!!.layoutInflater
        _view = inflate.inflate(R.layout.new_fragment, null)

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("New Note")
        builder.setMessage("Enter the data of the new note")
            .setPositiveButton("Save") { dialog, _ ->
                val title: String = _view.editTextTitle.text.toString()
                val content: String = _view.editTextContent.text.toString()
                var color = "blue"
                when(_view.RadioGroupColor.checkedRadioButtonId){
                    R.id.radioButtonColorRed ->{
                        color = "Red"
                    }
                    R.id.radioButtonColorGreen ->{
                        color = "Green"
                    }
                }
                val favorite: Boolean = _view.switchNoteFavorite.isChecked

                noteViewModel = ViewModelProviders.of(activity!!).get(NewNoteViewModel::class.java)

                noteViewModel.insert(EntityNote(title,content,favorite,color))
                dialog.dismiss()

            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // User cancelled the dialog
                dialog.dismiss()
            }
        builder.setView(_view)
        // Create the AlertDialog object and return it
        return builder.create()

    }



}
