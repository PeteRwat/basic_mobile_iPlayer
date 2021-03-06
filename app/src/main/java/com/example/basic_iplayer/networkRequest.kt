package com.example.basic_iplayer

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class networkRequest( url: String ){
    var client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    fun <T : Any> makeRequest(responseObj: T, callBack: (T) -> Unit) {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful()) {
                    val stringResponse: String = response.body()!!.string()

                    val gson = Gson()
                    val convertedResponse = gson.fromJson(stringResponse, responseObj::class.java)

                    callBack(convertedResponse)
                }
            }
        })

    }
}