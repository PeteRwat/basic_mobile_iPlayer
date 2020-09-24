package com.example.basic_iplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryHightlightActivity : AppCompatActivity() {
    private lateinit var elementRecyclerView: RecyclerView

    fun displayCategoryInfo (convertedResponse: CategoryReqObj) {
        runOnUiThread {
            elementRecyclerView = findViewById<RecyclerView>(R.id.categoryHighlights).apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)
                // use a linear layout manager
                layoutManager = LinearLayoutManager(this@CategoryHightlightActivity)
                // specify an viewAdapter (see also next example)
                if(convertedResponse is CategoryReqObj){
                    adapter = ElementRecyclerAdapter(convertedResponse.category_highlights.elements)
                }
            }
        }
        Log.i("converted res", convertedResponse.category_highlights.elements[4].title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_hightlight)

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val categoryID = intent.getStringExtra("CATEGORY-ID")
        val categoryTitle = intent.getStringExtra("CATEGORY-TITLE")

        findViewById<TextView>(R.id.categoryTitle).text = categoryTitle

        val url = "https://ibl.api.bbci.co.uk/ibl/v1/categories/${categoryID}/highlights?lang=en&rights=mobile&availability=available"
        networkRequest(url).makeRequest(CategoryReqObj(), this::displayCategoryInfo)
    }
}