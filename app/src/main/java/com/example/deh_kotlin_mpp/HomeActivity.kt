package com.example.deh_kotlin_mpp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<TextView>(R.id.main_text).text = createApplicationScreenMessage()

        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        var currentUser = null

        db.collection("users")
            .whereEqualTo("uid", auth.currentUser?.uid)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Home", "${document.id} => ${document.data}")
                    findViewById<TextView>(R.id.main_text_2).text = "Hello there, ${document.getString("first_name")} ${document.getString("last_name")}"
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Home", "Error getting documents.", exception)
            }

    }
}