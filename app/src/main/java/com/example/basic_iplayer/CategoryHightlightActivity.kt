package com.example.basic_iplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class CategoryHightlightActivity : AppCompatActivity() {

    fun displayCategoryInfo (convertedResponse: CategoryReqObj) {
        Log.i("converted res", convertedResponse.toString())
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