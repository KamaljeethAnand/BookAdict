package com.example.bookadict

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

public final class PDFListActivity : AppCompatActivity() {
    private lateinit var pdfRecyclerView: RecyclerView
    private lateinit var pdfListAdapter: PDFListAdapter
    private lateinit var pdfList: List<StorageReference>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_list)

        pdfRecyclerView = findViewById(R.id.pdfRecyclerView)
        pdfRecyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve the list of PDF files from Firebase Storage
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val pdfsRef = storageRef.child("uploads")
        pdfsRef.listAll()
            .addOnSuccessListener { listResult ->
                pdfList = listResult.items
                pdfListAdapter = PDFListAdapter(this, pdfList)
                pdfRecyclerView.adapter = pdfListAdapter
            }
            .addOnFailureListener { exception ->
                // Handle the error if necessary
            }
    }
}
