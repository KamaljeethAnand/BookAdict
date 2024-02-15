package com.example.bookadict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class ssmod2 : AppCompatActivity() {
    private lateinit var s62:PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ssmod2)

        s62=findViewById(R.id.smodule2)
        s62.fromAsset("SS2.pdf").load()

    }
}