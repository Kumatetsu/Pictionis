package com.etna.mob4.pictionis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.etna.mob4.utils.FirebaseInstanceSingleton


/**
 * Created by kumatetsu on 10/07/2018.
 */
class SignInActivity :  AppCompatActivity()  {
    private val mAuth: FirebaseAuth = FirebaseInstanceSingleton.getAuthInstance()
    private val TAG: String = "SignInAccount"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_layout)
    }

    fun connect(view: View) {
        val login = findViewById<EditText>(R.id.user_email)
        val pswd = findViewById<EditText>(R.id.user_pswd)

        val s_login = login.text.toString()
        val s_pswd = pswd.text.toString()

        if (s_login.isEmpty() || s_pswd.isEmpty()) {
            Toast.makeText(this, "Login or Password is empty", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(s_login, s_pswd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(this, "Authentication successfull.",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }
}