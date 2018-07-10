package com.etna.mob4.pictionis

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.main_layout.*

/**
 * Created by kumatetsu on 09/07/2018.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

//        //we get the button in var to put listener on it
//        val obi_button = findViewById<Button>(R.id.obi_button)
//
//        obi_button.setOnClickListener {
//
//        }

    }

    fun goToConnect(view: View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}