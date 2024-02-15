package com.example.bookadict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class ssmod4 : AppCompatActivity() {
    private lateinit var s64: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ssmod4)

        s64=findViewById(R.id.smodule4)
        s64.fromAsset("SS4.pdf").load()

    }
}