package com.etna.mob4.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by kumatetsu on 10/07/2018.
 */
object FirebaseInstanceSingleton {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun getInstance(): FirebaseAuth {
        return mAuth
    }
}