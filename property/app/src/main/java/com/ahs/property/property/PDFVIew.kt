package com.ahs.property.property

import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ahs.property.R
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnRenderListener
import java.io.File
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.URL

//C9:D2:63:E5:A7:5D:BF:E1:12:B3:4F:E9:32:7E:A9:C6:88:49:6F:CC
//BD:FB:56:8F:9C:90:F6:FE:07:BC:39:7C:0F:89:65:A6:7B:46:CF:4B:FD:D4:A5:4B:7E:34:2E:1D:ED:3F:54:E3
class PDFVIew : AppCompatActivity() {
    lateinit var downloadImage: ImageView
    lateinit var backPress: ImageView
    lateinit var aboutus: TextView
    var titleName: Boolean = false
    lateinit var pdfView : PDFView
    private lateinit var progressDialog: ProgressDialog
    // on below line we are creating a variable for our pdf view url.
    var pdfUrl = "https://ahs-properties.com/wp-content/uploads/2023/06/casa-canal-brouchure.pdf"
    var downloadUrl = ".0https://ahs-properties.com/wp-content/uploads/2023/06/casa-canal-brouchure.pdf"
    var viewUrl = "https://ahs-properties.com/wp-content/uploads/2023/06/casa-canal-brouchure.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfview)
        pdfView = findViewById<PDFView>(R.id.pdfView) as PDFView
        downloadImage = findViewById<ImageView>(R.id.inc_toolbar_with_center_logo_image)
        backPress = findViewById<ImageView>(R.id.inc_toolbar_image)
        aboutus = findViewById<TextView>(R.id.aboutus)
        backPress.visibility = View.VISIBLE
        downloadImage.visibility = View.VISIBLE
        val intent = getIntent()
        //pdfUrl = intent.getStringExtra("url").toString()
        titleName = intent.getBooleanExtra("name",false)
        downloadUrl = intent.getStringExtra("download").toString()
        viewUrl = intent.getStringExtra("view").toString()

        if(titleName){
            aboutus.setText("Villas")
        }

        downloadImage.setOnClickListener(View.OnClickListener { view ->
            var download = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var PdfUri = Uri.parse(downloadUrl)
            var getPdf = DownloadManager.Request(PdfUri)
            getPdf.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            download.enqueue(getPdf)
            Toast.makeText(this@PDFVIew, "Download Started", Toast.LENGTH_LONG).show()
        })

        backPress.setOnClickListener (View.OnClickListener { view ->
            this@PDFVIew.onBackPressedDispatcher.onBackPressed()
        })

        /*pdfView.fromUri(uri)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .enableAnnotationRendering(false)
            .password(null)
            .scrollHandle(null)
            .enableAntialiasing(true)
            .spacing(4)
            .invalidPageColor(Color.WHITE)
            .load()*/

        progressDialog = ProgressDialog(this@PDFVIew)
        progressDialog.setMessage("Loading PDF file....")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setCancelable(false)
        progressDialog.show()
        MyAsyncTask(this@PDFVIew,viewUrl).execute()
    }

    class MyAsyncTask internal constructor(context: PDFVIew,viewPdfUrl: String) : AsyncTask<Unit, Unit, InputStream>() {
        private val activityReference: WeakReference<PDFVIew> = WeakReference(context)
        var viewUrl = viewPdfUrl
        override fun onPreExecute() {

        }

        override fun doInBackground(vararg params: Unit): InputStream {
            return URL(viewUrl).openStream()
        }

        override fun onPostExecute(result: InputStream) {
            val activity = activityReference.get()
            activity!!.pdfView.fromStream(result)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .spacing(4)
                .invalidPageColor(Color.WHITE)
                .onLoad(object : OnLoadCompleteListener {
                    override fun loadComplete(nbPages: Int) {
                        activity.progressDialog.dismiss()
                    }
                })
                .load()
        }
    }

/*class RetrivePDFfromUrl : AsyncTask<String?, Void?, InputStream?>() {
        override fun onPostExecute(inputStream: InputStream?) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load()
        }

        override fun doInBackground(vararg params: String?): InputStream? {
            var inputStream: InputStream? = null
            try {
                val url = URL(params[0])
                val urlConnection: HttpURLConnection = url.openConnection() as HttpsURLConnection
                if (urlConnection.getResponseCode() === 200) {
                    inputStream = BufferedInputStream(urlConnection.getInputStream())
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
            return inputStream
        }
    }*/

   /* companion object{
        class MyAsyncTask internal constructor(context: PDFVIew) : AsyncTask<String, Unit, InputStream>() {
            private val activityReference: WeakReference<PDFVIew> = WeakReference(context)
            override fun onPreExecute() {
                val activity = activityReference.get()
                activity!!.progressBar.visibility = View.VISIBLE
                *//*if (activity == null || activity.isFinishing) {
                    activity?.progressBar?.bringToFront()
                    activity?.progressBar?.visibility = View.VISIBLE
                    return
                }*//*
            }

            override fun doInBackground(vararg params: String): InputStream {
                return URL("https://drive.google.com/uc?export=view&id=1sDHt8KdQ1Bu90FpXUvs-1UxB7YLhK_Iy").openStream()
            }

            override fun onPostExecute(result: InputStream) {
                val activity = activityReference.get()
                activity!!.progressBar.visibility = View.VISIBLE
               *//* if (activity == null || activity.isFinishing) {
                    activity?.progressBar?.bringToFront()
                    activity?.progressBar?.visibility = View.GONE
                    return
                }*//*
                *//*activity.pdfView.fromStream(result)
                    .enableSwipe(true)
                .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true)
                    .spacing(4)
                    .invalidPageColor(Color.WHITE)
                    .load()*//*
            }
        }
    }*/
}

