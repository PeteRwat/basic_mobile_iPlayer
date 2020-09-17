package com.example.basic_iplayer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onItemClicked(title: String) {
        Log.i("USER_",title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var client = OkHttpClient()
        val request = Request.Builder()
            .url("https://ibl.api.bbci.co.uk/ibl/v1/categories?rights=mobile")
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful()) {
                    val stringResponse :String = response.body()!!.string()

                    val gson = Gson()
                    val convertedResponse  = gson.fromJson(stringResponse, iPlayerReqObj::class.java)
                    viewAdapter = RecyclerAdapter(convertedResponse.categories, this@MainActivity)
                    viewManager = LinearLayoutManager(this@MainActivity)


                    runOnUiThread{
                        recyclerView = findViewById<RecyclerView>(R.id.main_menu).apply {
                            // use this setting to improve performance if you know that changes
                            // in content do not change the layout size of the RecyclerView
                            setHasFixedSize(true)

                            // use a linear layout manager
                            layoutManager = viewManager

                            // specify an viewAdapter (see also next example)
                            adapter = viewAdapter
                        }
                    }

                }
            }

        })



    }
}