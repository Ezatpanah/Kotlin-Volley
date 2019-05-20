package ir.rojadev.kotlinvolley.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import ir.rojadev.kotlinvolley.R
import ir.rojadev.volleykotling.AppSingleton
import kotlinx.android.synthetic.main.activity_string_request.*

class StringRequestActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_request)

        setupActivity()
        setListeners()


    }

    private fun setupActivity()
    {
        progressBar.visibility = View.GONE

    }

    private fun setListeners()
    {
        btnSendStringRequest.setOnClickListener({
            progressBar.visibility=View.VISIBLE

            sendStringRequest()
        })

    }

    private fun sendStringRequest()
    {
        val url = "http://cafe-seo.ir/rojadev/volley/stringRequest.php"


        val listener = Response.Listener<String> { response ->
            progressBar.visibility = View.GONE
            txtResult.text = response.toString()
        }

        val errorListener = Response.ErrorListener{error ->
            progressBar.visibility=View.GONE
            txtResult.text= error.toString()
            }

        val requset=StringRequest(Request.Method.GET,url,listener,errorListener)
        AppSingleton.getInstance(applicationContext).addToRequestQueue(requset)



    }
}
