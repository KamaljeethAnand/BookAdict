package com.example.bookadict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class ssmod5 : AppCompatActivity() {
    private lateinit var s65: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ssmod5)

        s65=findViewById(R.id.smodule5)
        s65.fromAsset("SS5.pdf").load()

    }
}