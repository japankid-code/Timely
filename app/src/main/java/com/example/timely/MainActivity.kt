package com.example.timely

import android.app.Activity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.timely.databinding.ActivityTimerBinding

class MainActivity : Activity()  {

    private var timer = Timer(300.0)
    private lateinit var binding: ActivityTimerBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityTimerBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val btnPlayPause = findViewById<ImageButton>(R.id.btn_play_pause)
            val secondsLeft = findViewById<TextView>(R.id.seconds_left)
            val minutesLeft = findViewById<TextView>(R.id.minutes_left)
            btnPlayPause.setOnClickListener {
                Toast.makeText(this@MainActivity, "hehe.", Toast.LENGTH_SHORT).show()
                timer.timerHandler("start")
                secondsLeft.text = String.format("%02d", timer.secondsLeft)
                minutesLeft.text = String.format("%02d", timer.minutesLeft)
            }
        }
}