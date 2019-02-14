package com.naimdridi.finalapp.Activities.Fragments




import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.toast
import kotlinx.android.synthetic.main.fragment_info.view.*
import java.lang.Exception


class InfoFragment : Fragment() {

    private lateinit var _view: View

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser

    private val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var chatDBRef: CollectionReference

    private var chatSubscription: ListenerRegistration? = null

    




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
       _view = inflater.inflate(R.layout.fragment_info, container, false)

        setUpChatDB()
        setUpCurrentUser()
        setUpCurrentUserInfoUI()


        // Total messages to Firebase Style
        subscribeToTotalMessagesFirebaseStyle()
        



        return _view
    }

    private fun setUpChatDB(){
        chatDBRef = store.collection("chat")
    }

    private fun setUpCurrentUser(){
        currentUser = mAuth.currentUser!!
    }

    private fun setUpCurrentUserInfoUI(){
        _view.textViewInfoEmail.text = currentUser.email
        _view.textViewInfoName.text = currentUser.displayName
            ?.let { currentUser.displayName }
                ?: run { getString(R.string.info_no_name) }

        currentUser.photoUrl?.let {
                Glide.with(this).load(currentUser.photoUrl)
                    .apply(RequestOptions.circleCropTransform().override(100, 100))
                    .into(_view.imageViewInfoAvatar)

                }?: run {
                Glide.with(this).load(R.drawable.ic_person)
                .apply(RequestOptions.circleCropTransform().override(100, 100))
                .into(_view.imageViewInfoAvatar)
                }
    }

    private fun subscribeToTotalMessagesFirebaseStyle(){
        chatDBRef.addSnapshotListener(object: java.util.EventListener, EventListener<QuerySnapshot>{
            override fun onEvent(querysnapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) {
                exception?.let {
                    activity!!.toast("Exception!")
                    return
                }

                querysnapshot?.let { _view.textViewInfoTotalMessage.text = "${it.size()}" }
            }

        })

    }

}
