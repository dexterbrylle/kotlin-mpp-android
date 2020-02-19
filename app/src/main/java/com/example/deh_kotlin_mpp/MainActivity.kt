package com.example.deh_kotlin_mpp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_reg_btn.setOnClickListener {
            doRegister()
        }

        hasAccount_textview_reg.setOnClickListener {
            Log.d("MainActivity", "Show login activity")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun doRegister() {
        val email = email_reg_et.text.toString()
        val password = password_reg_et.text.toString()
        val auth = FirebaseAuth.getInstance()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("Main", "createUserWithEmail:success $user")
                    Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("Main", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
