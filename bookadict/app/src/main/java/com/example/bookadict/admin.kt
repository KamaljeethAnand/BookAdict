package com.example.bookadict

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class admin : AppCompatActivity() {

    private lateinit var pdfBtn: Button
    private lateinit var uriTextView: TextView

    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        pdfBtn = findViewById(R.id.pdfbtn)
        uriTextView = findViewById(R.id.uritxt)

        storageReference = FirebaseStorage.getInstance().reference

        pdfBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), PDF_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == PDF_REQUEST_CODE) {
                val uri = data?.data
                uriTextView.text = uri.toString()
                uploadFile(uri)
            }
        }
    }

    private fun uploadFile(fileUri: Uri?) {
        Log.d("chk","chk")
        if (fileUri != null) {
            val fileName = fileUri.lastPathSegment
            val fileRef = storageReference.child("uploads/$fileName")

            fileRef.putFile(fileUri)
                .addOnSuccessListener {
                    Toast.makeText(this, "Upload successful", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Upload failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
//    private fun upload2() {
//        val mReference = mStorage.child(uri.lastPathSegment.toString())
//        mReference.putFile(uri).addOnSuccessListener { taskSnapshot ->
//            mReference.downloadUrl.addOnSuccessListener { uri ->
//                val url = uri.toString()
//                //val dwntxt = findViewById<View>(R.id.dwntxt) as TextView
//                dwntxt.text = url
//                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
    companion object {
        private const val PDF_REQUEST_CODE = 1
    }
}
