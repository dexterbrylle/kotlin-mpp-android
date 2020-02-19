package com.example.deh_kotlin_mpp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
        val email = email_reg_et.text.toString().trim()
        val password = password_reg_et.text.toString().trim()
        val firstName = firstname_reg_et.text.toString().trim()
        val lastName = lastname_reg_et.text.toString().trim()

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        if (firstName.isEmpty() || lastName.isEmpty() ||
                email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "${isEmptyFields()}", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val user = hashMapOf(
                        "first_name" to firstName,
                        "last_name" to lastName,
                        "uid" to currentUser?.uid
                    )


                    val intent = Intent(this, LoginActivity::class.java)
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Toast.makeText(baseContext, "${onRegSuccess()}", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Main", "${onRegFail()}")
                        }
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "${onRegFail()}",
                        Toast.LENGTH_LONG).show()
                }
            }
    }
}
