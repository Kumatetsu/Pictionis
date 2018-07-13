package com.etna.mob4.pictionis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.etna.mob4.entities.Message
import com.etna.mob4.utils.FirebaseInstanceSingleton
import com.etna.mob4.utils.MessageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.game_layout.*


/**
 * Created by kumatetsu on 10/07/2018.
 */
class GameActivity  : AppCompatActivity() {
    private val mauth       = FirebaseInstanceSingleton.getAuthInstance()
    private val fDbInstance = FirebaseInstanceSingleton.getDbInstance()
    private val chatRef     = fDbInstance.getReference("chat")
    private val drawingRef  = fDbInstance.getReference("drawing")
    private lateinit var adapter: MessageAdapter
    private val MESSAGE_TAG = "CHAT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        messageList.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(this)
        messageList.adapter = adapter

        chatRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                var value = dataSnapshot.getValue(Message::class.java)
                value = value ?: null
                Log.d(MESSAGE_TAG, "Value is: " + value)

                if (value != null)
                    runOnUiThread {
                        adapter.addMessage(value!!)
                        messageList.scrollToPosition(adapter.itemCount - 1);
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(MESSAGE_TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun sendMessage(view: View) {
        if(txtMessage.text.isNotEmpty()) {
            val message = Message(
                    mauth.currentUser!!.email.toString(),
                    txtMessage.text.toString()
            )

            chatRef.setValue(message)
        }
    }
}