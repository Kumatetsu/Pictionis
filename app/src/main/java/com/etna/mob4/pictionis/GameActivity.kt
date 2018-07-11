package com.etna.mob4.pictionis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.etna.mob4.utils.FirebaseInstanceSingleton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener





/**
 * Created by kumatetsu on 10/07/2018.
 */
class GameActivity  : AppCompatActivity() {
    private val mauth       = FirebaseInstanceSingleton.getAuthInstance()
    private val fDbInstance = FirebaseInstanceSingleton.getDbInstance()
    private val chatRef     = fDbInstance.getReference("chat")
    private val drawingRef  = fDbInstance.getReference("drawing")
    private val MESSAGE_TAG = "CHAT"

    private val messages: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        chatRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                var value = dataSnapshot.getValue(String::class.java)
                value = value ?: "null"
                Log.d(MESSAGE_TAG, "Value is: " + value)

                if (!value.equals("null"))
                    messages.add(value)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(MESSAGE_TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun sendMessage(view: View) {
        chatRef.setValue("""{
            "user": ${mauth.currentUser!!.email},
            "message": "Hello, World!"
        }
        """)
    }
}