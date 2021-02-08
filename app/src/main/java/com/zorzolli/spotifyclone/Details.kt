package com.zorzolli.spotifyclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.zorzolli.spotifyclone.Fragments.Home
import kotlinx.android.synthetic.main.activity_details.*

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        intent.extras?.let {
            var album = it.getString("album")
            var titles = it.getString("titles")
            Picasso.get().load(album).into(album_detail)
            album_tittle.setText(titles)
        }

        window.statusBarColor = Color.LTGRAY
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back))
        toolbar.title = null
        toolbar.setNavigationOnClickListener {
            var intent = Intent(this, Home::class.java)
            startActivities(intent)
            finish()
        }

    }

    private fun startActivities(intent: Intent) {

    }
}