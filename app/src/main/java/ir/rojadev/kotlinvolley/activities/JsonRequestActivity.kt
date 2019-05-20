package ir.rojadev.kotlinvolley.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import ir.rojadev.kotlinvolley.R
import ir.rojadev.volleykotling.AppSingleton
import ir.rojadev.volleykotling.constant.Constant
import kotlinx.android.synthetic.main.activity_json_request.*
import org.json.JSONArray
import org.json.JSONObject

class JsonRequestActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_request)


        setupActivity()
        setListeners()


    }

    private fun setupActivity()
    {
        progressBar.visibility = View.GONE

    }

    private fun setListeners()
    {
        btnSendJsonObjectRequest.setOnClickListener({
            progressBar.visibility = View.VISIBLE

            jsonObjectRequest()
        })

        btnSendJsonArrayRequest.setOnClickListener({
            progressBar.visibility = View.VISIBLE
            jsonArrayRequest()
        })

    }

    private fun jsonObjectRequest()
    {
        val url = "http://cafe-seo.ir/rojadev/volley/contactJsonObject.json";

        val listener = Response.Listener<JSONObject> {
            progressBar.visibility = View.GONE
            val mName = it.getString(Constant.KEY_NAME);
            val mPhone = it.getString(Constant.KEY_PHONE);
            val mEmail = it.getString(Constant.KEY_EMAIL);
            txtResult.setText("$mName \n $mPhone \n $mEmail")
        }

        val errorListener = Response.ErrorListener {
            progressBar.visibility = View.GONE
            txtResult.text = it.toString()
        }

        val request = JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener)
        request.retryPolicy = DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        AppSingleton.getInstance(applicationContext).addToRequestQueue(request)
    }

    private fun jsonArrayRequest()
    {
        val url = "http://cafe-seo.ir/rojadev/volley/contactJsonArray.json";

        val listener = Response.Listener<JSONArray> { response ->
            var strResult = ""
            progressBar.visibility = View.GONE
            for (i in 0..response.length() - 1)
            {
                val jsonObject = response.getJSONObject(i)
                val mName = jsonObject.getString(Constant.KEY_NAME);
                val mPhone = jsonObject.getString(Constant.KEY_PHONE);
                val mEmail = jsonObject.getString(Constant.KEY_EMAIL);
                strResult += "$mName \n$mPhone \n$mEmail \n\n\n"
            }
            txtResult.setText("$strResult")
        }

        val errorListener = Response.ErrorListener {
            progressBar.visibility = View.GONE
            txtResult.text = it.toString()
        }

        val request = JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener)
        AppSingleton.getInstance(applicationContext).addToRequestQueue(request)
    }


}
