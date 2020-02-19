package com.example.deh_kotlin_mpp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_login_btn.setOnClickListener {
            doLogin()
        }

        hasAccount_login_tv.setOnClickListener {
            finish()
        }
    }

    private fun doLogin() {
        val email = email_login_et.text.toString().trim()
        val password = password_login_et.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "${isEmptyFields()}", Toast.LENGTH_LONG).show()
            return
        }

        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "${onLoginSuccess()}!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "${onLoginFail()}",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}