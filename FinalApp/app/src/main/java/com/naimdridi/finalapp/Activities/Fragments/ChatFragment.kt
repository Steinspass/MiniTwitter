package com.naimdridi.finalapp.Activities.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.naimdridi.finalapp.Activities.Adapters.ChatAdapter
import com.naimdridi.finalapp.Activities.Models.Message

import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.toast
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.view.*
import java.util.*
import kotlin.collections.HashMap


class ChatFragment : Fragment() {

    private lateinit var _view: View
    private lateinit var  adapter: ChatAdapter
    private val messageList: ArrayList<Message> = ArrayList()

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser

    private val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var chatDBRef: CollectionReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _view = inflater.inflate(R.layout.fragment_chat, container, false)

        setUpChatDB()
        setUpCurrentUser()
        setUpRecyclerView()
        setUpChatBtn()

        subscribeToChatMessage()


        return _view
    }

    private fun setUpChatDB(){
        chatDBRef = store.collection("chat")
    }

    private fun setUpCurrentUser(){
        currentUser = mAuth.currentUser!!
    }

    private fun setUpRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
        adapter = ChatAdapter(messageList, currentUser.uid)

        _view.recyclerView.setHasFixedSize(true)
        _view.recyclerView.layoutManager = layoutManager
        _view.recyclerView.itemAnimator = DefaultItemAnimator()
        _view.recyclerView.adapter = adapter
    }

    private fun setUpChatBtn(){
        _view.buttonSend.setOnClickListener {
            val messageText = editTextMessage.text.toString()
            if (messageText.isNotEmpty()){
                val message = Message(currentUser.uid, messageText, currentUser.photoUrl.toString(), Date())
                // Guardaremos el mensaje en Firebase
                saveMessage(message)
                _view.editTextMessage.setText("")
            }
        }
    }

    private fun saveMessage(message: Message){
        val newMessage = HashMap<String, Any>()
        newMessage["authorId"] = message.authorId
        newMessage["message"] = message.message
        newMessage["profileImageUrl"] = message.profileImageUrl
        newMessage["sentAt"] = message.sentAt

        chatDBRef.add(newMessage)
            .addOnCompleteListener{
                activity!!.toast("Message Added!!!")
            }
            .addOnFailureListener {
                activity!!.toast("Message error, Try again")
            }


    }

    private fun subscribeToChatMessage(){
        chatDBRef.addSnapshotListener(object: java.util.EventListener, EventListener<QuerySnapshot>{

            override fun onEvent(snapshot: QuerySnapshot?, exepcion: FirebaseFirestoreException?) {
                exepcion?.let {
                    activity!!.toast("Exception!")
                    return
                }
                snapshot?.let {
                    messageList.clear()
                    val messages = it.toObjects(Message::class.java)
                    messageList.addAll(messages)
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }

}
