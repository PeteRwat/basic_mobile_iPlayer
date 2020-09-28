package com.example.basic_iplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CategoryHightlightActivity : AppCompatActivity(), OnElementClickListener {
    private lateinit var elementRecyclerView: RecyclerView

    override fun onElementClicked(element: Element) {
        if(element.type == "episode"){
            val epTitle: String = element.title
            val epMasterBrand: String = element.master_brand.titles.small
            val epDetails: String = element.subtitle
            val epSynopses: String = element.synopses.medium
            val epDuration: String = element.versions[0].duration.text
            val epFirstShown: String = element.release_date
            val epAvailability: String = element.versions[0].availability.remaining.text
            val epImageURL: String = element.images.standard.replace("{recipe}","432x243")

            val intent = Intent(this, EpisodeActivity::class.java).apply {
                putExtra("EPISODE_TITLE", epTitle)
                putExtra("EPISODE_MASTER_BRAND", epMasterBrand)
                putExtra("EPISODE_DETAILS", epDetails)
                putExtra("EPISODE_SYNOPSES", epSynopses)
                putExtra("EPISODE_DURATION", epDuration)
                putExtra("EPISODE_FIRST_SHOWN", epFirstShown)
                putExtra("EPISODE_AVAILABILITY", epAvailability)
                putExtra("EPISODE_IMAGE_URL", epImageURL)
            }
            startActivity(intent)
        }
    }

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
                    Log.i("cat obj", convertedResponse.toString())
                    adapter = ElementRecyclerAdapter(convertedResponse.category_highlights.elements, this@CategoryHightlightActivity)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_hightlight)

        val categoryID = intent.getStringExtra("CATEGORY-ID")
        val categoryTitle = intent.getStringExtra("CATEGORY-TITLE")

        findViewById<TextView>(R.id.categoryTitle).text = categoryTitle

        val url = "https://ibl.api.bbci.co.uk/ibl/v1/categories/${categoryID}/highlights?lang=en&rights=mobile&availability=available"
        networkRequest(url).makeRequest(CategoryReqObj(), this::displayCategoryInfo)
    }
}