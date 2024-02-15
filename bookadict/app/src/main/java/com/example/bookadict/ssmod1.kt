package com.example.bookadict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.github.barteksc.pdfviewer.PDFView

class ssmod1 : AppCompatActivity() {
    private lateinit var s61: PDFView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ssmod1)

        s61=findViewById(R.id.smodule1)
        s61.fromAsset("SS1.pdf").load()


    }
}