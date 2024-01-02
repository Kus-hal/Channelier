package com.example.myapplication.data.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.data.models.Customer
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import javax.inject.Inject

class SendLocationApiImpl @Inject constructor() : SendLocationApi {

    private val locationDetailsLiveData = MutableLiveData<Customer>()

    override fun getLocationDetails(): LiveData<Customer> {
        return locationDetailsLiveData
    }

    override fun sendLocationUpdate(customer: Customer) {
        locationDetailsLiveData.postValue(customer)


//        interface SendLocationApi {
//            fun sendLocation(customer: Customer)
//        }
//        class SendLocationApiImpl: SendLocationApi{
//            @ApplicationContext lateinit var context: Context
//            override fun sendLocation(customer: Customer) {
//        try {
//            val requestQueue = Volley.newRequestQueue(context)
//            val URL = "https://dev.channelier.com/api/public/whatsappCallback"
//            val jsonBody = JSONObject()
//            jsonBody.put("name", customer.name)
//            jsonBody.put("contactNo", customer.contactNo)
//            jsonBody.put("customerId", customer.customerID)
//            jsonBody.put("location", customer.location)
//            val requestBody = jsonBody.toString()
//
//            val stringRequest = object : StringRequest(
//                Request.Method.POST, URL,
//                Response.Listener<String> { response -> Log.i("VOLLEY", response) },
//                Response.ErrorListener { error -> Log.e("VOLLEY", error.toString()) },
//                    jsonBody
//            ) {
//                override fun getBodyContentType(): String {
//                    return "application/json; charset=utf-8"
//                }
//
//                @Throws(AuthFailureError::class)
//                override fun getBody(): ByteArray {
//                    return try {
//                        requestBody.toByteArray(Charsets.UTF_8)
//                    } catch (uee: UnsupportedEncodingException) {
//                        VolleyLog.wtf(
//                            "Unsupported Encoding while trying to get the bytes of %s using %s",
//                            requestBody,
//                            "utf-8"
//                        )
//                        byteArrayOf()
//                    }
//                }
//
//                override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
//                    var responseString = ""
//                    if (response != null) {
//                        responseString = response.statusCode.toString()
//                        // can get more details such as response.headers
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response))
//                }
//            }
//
//            requestQueue.add(stringRequest)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }

//            }
//
//        }
    }
}
