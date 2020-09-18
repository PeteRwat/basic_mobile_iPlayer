package com.example.basic_iplayer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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


class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView

    fun useAdapter(convertedResponse: iPlayerReqObj) {
        runOnUiThread {
            recyclerView = findViewById<RecyclerView>(R.id.main_menu).apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)
                // use a linear layout manager
                layoutManager = LinearLayoutManager(this@MainActivity)
                // specify an viewAdapter (see also next example)
                if(convertedResponse is iPlayerReqObj){
                    adapter = RecyclerAdapter(convertedResponse.categories, this@MainActivity)
                }
            }
        }
    }

    override fun onItemClicked(title: String) {
        Log.i("USER_",title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://ibl.api.bbci.co.uk/ibl/v1/categories?rights=mobile"
        networkRequest(url).makeRequest(iPlayerReqObj(), this::useAdapter)

    }
}