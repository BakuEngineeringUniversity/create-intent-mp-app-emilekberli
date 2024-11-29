package com.example.basicprofil

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mailButton: Button = findViewById(R.id.mailButton)
        mailButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:leleeeeeeeeeeeees@gmail.com")
            }

            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "No email client installed on this device.",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        val phoneButton: Button = findViewById(R.id.phoneButton)
        phoneButton.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+994506564907")
            }
            startActivity(phoneIntent)
        }


        val mapButton: Button = findViewById(R.id.mapButton)
        mapButton.setOnClickListener {
            val geoUri = "geo:0,0?q=Ismayilli, Azerbaijan"
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri)).apply {
                setPackage("com.google.android.apps.maps")
            }
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                startActivity(fallbackIntent)
               }
          }

        val musicButton: Button = findViewById(R.id.musicButton)
        musicButton.setOnClickListener {
            if (::mediaPlayer.isInitialized.not()) {
                mediaPlayer = MediaPlayer.create(this, R.raw.music_file)
            }
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                Toast.makeText(this, "Music paused", Toast.LENGTH_SHORT).show()
            } else {
                mediaPlayer.start()
                Toast.makeText(this, "Music started", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}
