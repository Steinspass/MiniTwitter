package com.naimdridi.finalapp.Activities.dialog

import android.app.Dialog
import android.support.v7.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.toast

class RateDialog : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        AlertDialog.Builder(context!!)
            .setTitle(getString(R.string.dialog_title))
            .setView(R.layout.dialog_rate)
            .setPositiveButton(getString(R.string.dialog_ok)){ dialogOk, which ->
                // toast
                activity!!.toast("Pressed Ok")
            }
            .setNegativeButton(getString(R.string.dialog_cancel)){ dialogCancel, which ->
                // toast
                activity!!.toast("Pressed Cancel")
            }
            .create()

        return super.onCreateDialog(savedInstanceState)
    }
}