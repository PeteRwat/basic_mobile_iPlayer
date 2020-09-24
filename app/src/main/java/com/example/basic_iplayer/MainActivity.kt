package com.example.basic_iplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException



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

    override fun onItemClicked(id: String, title: String) {
        val intent = Intent(this, CategoryHightlightActivity::class.java).apply {
            putExtra("CATEGORY-ID", id)
            putExtra("CATEGORY-TITLE", title)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://ibl.api.bbci.co.uk/ibl/v1/categories?rights=mobile"
        networkRequest(url).makeRequest(iPlayerReqObj(), this::useAdapter)

    }
}