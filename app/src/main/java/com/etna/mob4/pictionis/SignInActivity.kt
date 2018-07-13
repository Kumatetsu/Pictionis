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
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.etna.mob4.utils.FirebaseInstanceSingleton


/**
 * Created by kumatetsu on 10/07/2018.
 */
class SignInActivity :  AppCompatActivity()  {
    val REGISTER_ACCOUNT: Int = 1
    private val mAuth: FirebaseAuth = FirebaseInstanceSingleton.getAuthInstance()
    private val TAG: String = "SignInAccount"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_layout)
    }

    /**
     * change the activity to registerActivity
     * waiting for result
     */
    fun goToCreateUser(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivityForResult(intent, REGISTER_ACCOUNT)
    }


    /**
     * block the back button to avoid the return and refresh of the activity
     */
    override fun onBackPressed() {
    }

    /**
     * If register return RESULT_OK, finish this activity to go to home page
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode.equals(REGISTER_ACCOUNT)) {
            if (resultCode.equals(RESULT_OK)) {
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    /**
     * Call Firebase auth function to connect to Pictionis.
     */
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
                        setResult(RESULT_OK)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }
}