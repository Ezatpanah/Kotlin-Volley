package ir.rojadev.volleykotling

import android.content.Context

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.Volley

/**
 * Created by Reyhaneh Ezatpanah on 05/20/2018.
 **/


class AppSingleton private constructor(context: Context)
{

    private var mRequestQueue: RequestQueue? = null
    private val TAG = ""


    val requestQueue: RequestQueue?
        get()
        {
            if (mRequestQueue == null)
            {
                mRequestQueue = Volley.newRequestQueue(mContext!!.applicationContext)
            }
            return mRequestQueue
        }

    init
    {
        mContext = context
        mRequestQueue = requestQueue
        VolleyLog.DEBUG = true
    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String)
    {
        req.tag = tag
        requestQueue!!.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>)
    {
        req.tag = TAG
        requestQueue!!.add(req)
    }

    fun cancelPendingRequests(tag: Any)
    {
        if (mRequestQueue != null)
        {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    fun cancelPendingRequests()
    {
        if (mRequestQueue != null)
        {
            mRequestQueue!!.cancelAll(TAG)
        }
    }

    companion object
    {
        private var mAppSingletonInstance: AppSingleton? = null
        private var mContext: Context? = null

        @Synchronized
        fun getInstance(context: Context): AppSingleton
        {
            if (mAppSingletonInstance == null)
            {
                mAppSingletonInstance = AppSingleton(context)
            }
            return mAppSingletonInstance!!
        }
    }
}