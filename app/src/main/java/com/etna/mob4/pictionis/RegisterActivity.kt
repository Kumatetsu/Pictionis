package com.etna.mob4.pictionis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.etna.mob4.utils.FirebaseInstanceSingleton


/**
 * Created by kumatetsu on 09/07/2018.
 */
class RegisterActivity :  AppCompatActivity() {
    private val TAG: String = "CreateAccount"

    private val fAuthInstance = FirebaseInstanceSingleton.getAuthInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
    }

    /**
     * Use Firebase auth function to create an account
     * Return ok if creation is ok
     */
    fun createAccount(view: View) {
        val login = findViewById<EditText>(R.id.user_email)
        val pswd  = findViewById<EditText>(R.id.user_pswd)

        val s_login = login.text.toString()
        val s_pswd  = pswd.text.toString()

        if (s_login.isEmpty() || s_pswd.isEmpty() ){
            Toast.makeText(this, "Login or Password is empty", Toast.LENGTH_SHORT).show()
            return
        }

        fAuthInstance.createUserWithEmailAndPassword(login.text.toString(), pswd.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "Account created.", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "createUserWithEmail:success")
                        fAuthInstance.currentUser
                        setResult(RESULT_OK)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException())
                        Toast.makeText(this, "Account creation failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }
}