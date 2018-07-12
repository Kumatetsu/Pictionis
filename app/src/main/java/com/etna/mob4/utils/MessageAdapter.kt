package com.etna.mob4.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.etna.mob4.entities.Message
import com.etna.mob4.pictionis.R
import kotlinx.android.synthetic.main.message_sent.view.*
import kotlinx.android.synthetic.main.message_received.view.*

/**
 * Created by kumatetsu on 12/07/2018.
 */
private const val VIEW_TYPE_MY_MESSAGE = 1
private const val VIEW_TYPE_OTHER_MESSAGE = 2

class MessageAdapter (val context: Context) : RecyclerView.Adapter<MessageViewHolder>() {
    private val messages: ArrayList<Message> = ArrayList()
    private val mauth = FirebaseInstanceSingleton.getAuthInstance()

    fun addMessage(message: Message){
        messages.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages.get(position)

        return if(mauth.currentUser!!.email == message.user) {
            VIEW_TYPE_MY_MESSAGE
        }
        else {
            VIEW_TYPE_OTHER_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == VIEW_TYPE_MY_MESSAGE) {
            MyMessageViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.message_sent, parent, false)
            )
        } else {
            OtherMessageViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.message_received, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages.get(position)

        holder?.bind(message)
    }


    inner class MyMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.send_message_body
        private var userText: TextView = view.send_name

        override fun bind(message: Message) {
            messageText.text = message.message
            userText.text = message.user
        }
    }

    inner class OtherMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.received_message_body
        private var userText: TextView = view.received_name

        override fun bind(message: Message) {
            messageText.text = message.message
            userText.text = message.user
        }
    }
}

open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(message: Message) {}
}