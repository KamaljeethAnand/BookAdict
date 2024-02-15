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
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class signupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseStore = FirebaseFirestore.getInstance()

        binding.signupButton.setOnClickListener {
            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()
            val confirmPassword = binding.signupConfirm.text.toString()
            val db = Firebase.firestore

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val user: FirebaseUser? = firebaseAuth.currentUser
                                val userId: String? = user?.uid

                                if (true) {
                                    val userDocument: DocumentReference = firebaseStore.collection("users").document(
                                        userId.toString()
                                    )
                                    val userInfo = HashMap<String, Any>()
                                    userInfo["email"] = email
                                    userInfo["admin"]=0

                                    userDocument.set(userInfo)
                                        .addOnSuccessListener {
                                            Toast.makeText(this, "User information added to Firestore.", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this, loginActivity::class.java)
                                            startActivity(intent)
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(this, "Error adding user information: " + e.message, Toast.LENGTH_SHORT).show()
                                        }
                                }
                                Toast.makeText(this,"created",Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this, loginActivity::class.java)
                                            startActivity(intent)
                                        }

                            }
                        }

                } else {
                    Toast.makeText(this, "field cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            binding.loginRedirectText.setOnClickListener {
                val loginIntent = Intent(this, loginActivity::class.java)
                startActivity(loginIntent)
            }


        }
    }
