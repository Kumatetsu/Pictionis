package com.etna.mob4.pictionis

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser




/**
 * Created by kumatetsu on 09/07/2018.
 */
class SignInActivity :  AppCompatActivity() {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val TAG: String = "CreateAccount"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_layout)
    }

    fun createAccount(view: View) {
        val login = findViewById<EditText>(R.id.user_login)
        val pswd = findViewById<EditText>(R.id.user_pswd)
        mAuth.createUserWithEmailAndPassword(login.text.toString(), pswd.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth!!.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException())
                        Toast.makeText(this, "Account creation failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }
}