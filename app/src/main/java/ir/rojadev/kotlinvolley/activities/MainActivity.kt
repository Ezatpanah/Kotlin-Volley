package ir.rojadev.kotlinvolley.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ir.rojadev.kotlinvolley.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setListeners()
    }

    private fun setListeners() {
        imgStringRequest.setOnClickListener({
            startActivity(Intent(this, StringRequestActivity::class.java))
        })

        imgJsonRequest.setOnClickListener({
            startActivity(Intent(this, JsonRequestActivity::class.java))
        })

        imgImageRequest.setOnClickListener({
            startActivity(Intent(this, ImageRequestActivity::class.java))
        })
    }
}
