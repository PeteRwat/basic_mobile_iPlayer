package com.example.basic_iplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class EpisodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)

        var imageURL = intent.getStringExtra("EPISODE_IMAGE_URL")
        Picasso.get().load(imageURL).into(findViewById<ImageView>(R.id.epImage))

        findViewById<TextView>(R.id.epMasterBrand).text = intent.getStringExtra("EPISODE_MASTER_BRAND")
        findViewById<TextView>(R.id.epTitle).text = intent.getStringExtra("EPISODE_TITLE")
        findViewById<TextView>(R.id.epDetails).text = intent.getStringExtra("EPISODE_DETAILS")
        findViewById<TextView>(R.id.epSynopses).text = intent.getStringExtra("EPISODE_SYNOPSES")
        findViewById<TextView>(R.id.epDuration).text = intent.getStringExtra("EPISODE_DURATION")
        findViewById<TextView>(R.id.epFirstShown).text = intent.getStringExtra("EPISODE_FIRST_SHOWN")
        findViewById<TextView>(R.id.epAvailability).text = intent.getStringExtra("EPISODE_AVAILABILITY")
    }
}