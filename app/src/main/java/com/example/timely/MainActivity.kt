package com.example.timely

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import android.widget.TextView
import com.example.timely.databinding.ActivityTimerBinding

class MainActivity : Activity()  {

    private lateinit var binding: ActivityTimerBinding

    private var initLength = 360000L

    private lateinit var timer: CountDownTimer
    private var isRunning: Boolean = false
    private var timeInMilliseconds = 0L

    private lateinit var btnPlayPause: ImageButton
    private lateinit var btnRestart: ImageButton
    private lateinit var secondsLeft: TextView
    private lateinit var minutesLeft: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_timer)

        btnPlayPause = findViewById(R.id.btn_play_pause)
        btnRestart = findViewById(R.id.btn_restart)
        secondsLeft = findViewById(R.id.seconds_left)
        minutesLeft = findViewById(R.id.minutes_left)

        btnPlayPause.setOnClickListener {
            if (isRunning) {
                pauseTimer()
            } else {
                setRemainingTimeLeft();
                startTimer(timeInMilliseconds)
                btnPlayPause.setImageResource(R.drawable.ic_pause)
            }
        }
        btnRestart.setOnClickListener {
            if (isRunning) {
                pauseTimer()
                resetTimer()
            } else {
                resetTimer()
            }
        }

    }

    private fun pauseTimer() {
        timer.cancel()
        isRunning = false
        btnPlayPause.setImageResource(R.drawable.ic_play)
    }

    private fun startTimer(time_in_seconds: Long) {
        timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                //
            }

            override fun onTick(ms: Long) {
                timeInMilliseconds = ms
                updateTextUI()
            }
        }
        timer.start()

        isRunning = true

    }

    private fun resetTimer() {
        timeInMilliseconds = initLength
        updateTextUI()
        btnPlayPause.setImageResource(R.drawable.ic_play)
    }

    private fun updateTextUI() {
        val minutes = String.format("%02d",((timeInMilliseconds / 1000) / 60))
        val seconds = String.format("%02d",((timeInMilliseconds / 1000) % 60))
        secondsLeft.text = seconds
        minutesLeft.text = minutes
    }

    private fun setRemainingTimeLeft() {
        val seconds = secondsLeft.text.toString().toInt()*1000.toLong()
        val minutes = minutesLeft.text.toString().toInt()*1000*60.toLong()
        timeInMilliseconds = minutes + seconds
    }

}