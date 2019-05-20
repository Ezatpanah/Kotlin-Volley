package ir.rojadev.kotlinvolley.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import ir.rojadev.kotlinvolley.R
import ir.rojadev.volleykotling.AppSingleton
import kotlinx.android.synthetic.main.activity_image_request.*

class ImageRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_request)
        
        setupActivity()
        setListeners()

    }

    private fun setupActivity()
    {
        progressBar.visibility = View.GONE
    }

    private fun setListeners()
    {
        btnSendImageRequest.setOnClickListener({
            progressBar.visibility = View.VISIBLE
            sendImageRequest()
        })
    }

    private fun sendImageRequest()
    {
        val url = "http://cafe-seo.ir/rojadev/volley/Rojadev_ir.jpg"

        val listener = Response.Listener<Bitmap> { response ->
            progressBar.visibility = View.GONE
            imgImage.setImageBitmap(response)
        }

        val errorListener = Response.ErrorListener { error ->
            progressBar.visibility = View.GONE
            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
        }

        val request =
            ImageRequest(url, listener, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, errorListener)
        AppSingleton.getInstance(applicationContext).addToRequestQueue(request)
    }

}
