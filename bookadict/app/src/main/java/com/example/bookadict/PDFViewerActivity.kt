package com.example.bookadict
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnRenderListener
import java.io.File

class PDFViewerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PDF_FILE_PATH = "pdf_file_path"

        fun createIntent(context: Context, filePath: String): Intent {
            val intent = Intent(context, PDFViewerActivity::class.java)
            intent.putExtra(EXTRA_PDF_FILE_PATH, filePath)
            return intent
        }
    }

    private lateinit var pdfView: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfviewer)

        pdfView = findViewById(R.id.pdfView)

        val filePath = intent.getStringExtra(EXTRA_PDF_FILE_PATH)
        if (filePath != null) {
            displayPDF(filePath)
        }
    }

    private fun displayPDF(filePath: String) {
        val file = File(filePath)
        if (file.exists()) {
            pdfView.fromFile(file)
                .onRender(object : OnRenderListener {
                    override fun onInitiallyRendered(nbPages: Int) {
                        // Called when the PDF document is initially rendered
                    }
                })
                .load()
        } else {
            // Handle the case when the file does not exist
        }
    }
}
