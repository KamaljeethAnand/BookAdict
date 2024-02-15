package com.example.bookadict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class ssmod3 : AppCompatActivity() {
    private lateinit var s63: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ssmod3)

        s63=findViewById(R.id.smodule3 )
        s63.fromAsset("SS3.pdf").load()

    }
}