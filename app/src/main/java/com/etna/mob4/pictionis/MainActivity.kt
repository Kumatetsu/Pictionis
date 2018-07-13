package com.etna.mob4.pictionis

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.etna.mob4.utils.FirebaseInstanceSingleton
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by kumatetsu on 09/07/2018.
 */
class MainActivity : AppCompatActivity() {
    val SIGNIN: Int = 1;
    val mauth: FirebaseAuth = FirebaseInstanceSingleton.getAuthInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        if (mauth.currentUser == null)
            startActivityForResult(Intent(this, SignInActivity::class.java), SIGNIN)
    }

    /**
     * waiting for connection
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode.equals(SIGNIN)) {
            if (resultCode.equals(RESULT_OK)) {
                Toast.makeText(this, "Menu after account created", Toast.LENGTH_SHORT).show()
            } else {
                startActivityForResult(Intent(this, SignInActivity::class.java), SIGNIN)
            }
        }
    }

    /**
     * launch game activity
     */
    fun goToGame(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    /**
     * disconnect and reload activity
     */
    fun disconnect(view: View) {
        FirebaseAuth.getInstance().signOut()
        finish()
        startActivity(getIntent())
    }

}