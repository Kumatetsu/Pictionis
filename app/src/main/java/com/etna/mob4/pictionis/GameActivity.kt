package com.etna.mob4.pictionis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by kumatetsu on 10/07/2018.
 */
class GameActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
    }

    fun sendMessage() {}
}