package com.etna.mob4.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by kumatetsu on 10/07/2018.
 */
object FirebaseInstanceSingleton {
    private val mAuth: FirebaseAuth   = FirebaseAuth.getInstance()
    private val mDb: FirebaseDatabase = FirebaseDatabase.getInstance();

    fun getAuthInstance(): FirebaseAuth {
        return mAuth
    }

    fun getDbInstance(): FirebaseDatabase {
        return mDb
    }
}