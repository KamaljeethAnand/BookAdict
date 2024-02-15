package com.example.bookadict

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bookadict.databinding.ActivityLoginBinding
import com.example.bookadict.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener{
            val email=binding.loginEmail.text.toString()
            val password =binding.loginPassword.text.toString()
            val bundle=Bundle()


            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val user: FirebaseUser? = firebaseAuth.currentUser
                        val userId: String? = user?.uid
                        val db = FirebaseFirestore.getInstance()
                        val userDocument: DocumentReference = db.collection("users").document(userId.toString())

                        userDocument.get()
                            .addOnSuccessListener { documentSnapshot ->
                                if (documentSnapshot.exists()) {
                                    val adminValue = documentSnapshot.getLong("admin")

                                    // Check if the "admin" field exists and retrieve its value
                                    val isAdmin = adminValue?.toInt()  ==1
                                    if (isAdmin)
                                    {
                                        bundle.putBoolean("admin",isAdmin)
                                        val intent=Intent(this,MainActivity::class.java)
                                        intent.putExtras(bundle)
                                        startActivity(intent)
                                    }

                                        else
                                     {
                                         val intent=Intent(this,MainActivity::class.java)
                                         startActivity(intent)

                                     }
                                    }


                    }
                }
            }

        }
        binding.signupRedirectText.setOnClickListener{
            val signupintent=Intent(this,signupActivity::class.java)
            startActivity(signupintent)
        }
    }
}}