package com.example.bookadict
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookadict.PDFViewerActivity
import com.example.bookadict.R
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PDFListAdapter(private val context: Context, private val pdfList: List<StorageReference>) :
    RecyclerView.Adapter<PDFListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_pdf, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PDFListAdapter.ViewHolder, position: Int) {
        val pdfReference = pdfList[position]
        val pdfName = pdfReference.name
        holder.pdfName.text = pdfName

        holder.itemView.setOnClickListener {
            // Download the PDF file to local storage
            val localFile = createLocalFile(pdfName)
            downloadPDF(pdfReference, localFile)
        }
    }

    override fun getItemCount(): Int {
        return pdfList.size
    }

    private fun createLocalFile(pdfName: String): File {
        val dir = context.cacheDir // Or any other desired directory
        return File(dir, pdfName)
    }

    private fun downloadPDF(pdfReference: StorageReference, localFile: File) {
        if (localFile.exists()) {
            // File already exists locally, open the PDF viewer directly
            openPDFViewer(localFile.absolutePath)
        } else {
            pdfReference.getFile(localFile)
                .addOnSuccessListener {
                    openPDFViewer(localFile.absolutePath)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(context, "Failed to download PDF: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun openPDFViewer(filePath: String) {
        val intent = Intent(context, PDFViewerActivity::class.java)
        intent.putExtra(PDFViewerActivity.EXTRA_PDF_FILE_PATH, filePath)
        context.startActivity(intent)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pdfName: TextView = itemView.findViewById(R.id.pdfName)
    }
}
