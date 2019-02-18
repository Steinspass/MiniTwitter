package com.naimdridi.finalapp.Activities.dialog

import android.app.Dialog
import android.support.v7.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.naimdridi.finalapp.Activities.Models.NewRateEvent
import com.naimdridi.finalapp.Activities.Models.Rate
import com.naimdridi.finalapp.Activities.Utils.RxBus
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.toast
import kotlinx.android.synthetic.main.dialog_rate.view.*
import java.util.*

class RateDialog : DialogFragment(){

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser

    private fun setUpCurrentUser() {
        currentUser = mAuth.currentUser!!
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        setUpCurrentUser()
        val view = activity!!.layoutInflater.inflate(R.layout.dialog_rate, null)

        return AlertDialog.Builder(context!!)
            .setTitle(getString(R.string.dialog_title))
            .setView(view)
            .setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
                val textRate = view.editTextRateFeedback.text.toString()
                if (textRate.isNotEmpty()) {
                    val imgURL = currentUser.photoUrl?.toString() ?: run { "" }
                    val rate = Rate(currentUser.uid, textRate, view.ratingBarFeedback.rating, Date(), imgURL)
                    RxBus.publish(NewRateEvent(rate))
                }
            }
            .setNegativeButton(getString(R.string.dialog_cancel)) { _, _ ->
                activity!!.toast("Pressed Cancel")
            }
            .create()
    }
}