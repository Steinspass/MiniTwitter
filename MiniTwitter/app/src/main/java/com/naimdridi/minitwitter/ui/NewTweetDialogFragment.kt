package com.naimdridi.minitwitter.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naimdridi.minitwitter.R
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.bumptech.glide.Glide
import com.naimdridi.minitwitter.common.SharedPreferencesManager
import com.naimdridi.minitwitter.common.Constans
import com.naimdridi.minitwitter.data.TweetViewModel
import android.arch.lifecycle.ViewModelProviders
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.new_tweet_full_dialog.view.*


class NewTweetDialogFragment: DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.new_tweet_full_dialog, container, false)



        // Seteamos la imagen del usuario de perfil
        val photoUrl = SharedPreferencesManager().getSomeStringValue(Constans.PREF_PHOTOURL)
        if (!photoUrl!!.isEmpty()) {
            Glide.with(activity!!)
                .load(Constans.API_MINITWITTER_FILES_URL + photoUrl)
                .into(view.imageViewAvatar)
        }

        onClick(view)

        return view
    }

    private fun onClick(view: View) {

        val mensaje = view.editTextMensaje.text.toString()

        view.buttonTwittear.setOnClickListener {
            if (mensaje.isNotEmpty()){
                val tweetViewModel = ViewModelProviders
                    .of(activity!!).get(TweetViewModel::class.java)
                tweetViewModel.insertTweet(mensaje)
                dialog.dismiss()
            }else{
                Toast.makeText(activity, "Debe escribir un texto en el mensaje", Toast.LENGTH_SHORT).show()
            }
        }

        view.imageViewClose.setOnClickListener {
            if (mensaje.isNotEmpty()){
                showDialogConfirm()
            }else{
                dialog.dismiss()
            }
        }
    }

    private fun showDialogConfirm() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        val builder = AlertDialog.Builder(activity!!)

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("¿Desea realmente eliminar el tweet? El mensaje se borrará")
            .setTitle("Cancelar tweet")

        builder.setPositiveButton("Eliminar") { dialog, id ->
            dialog.dismiss()
            getDialog().dismiss()
        }
        builder.setNegativeButton(
            "Cancelar"
        ) { dialog, id -> dialog.dismiss() }

        // 3. Get the AlertDialog from create()
        val dialog = builder.create()

        dialog.show()
    }
}