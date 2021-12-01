package com.example.timely

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
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
    private lateinit var secondsScroller: ScrollView
    private lateinit var minutesScroller: ScrollView
    //private lateinit var secondsWrapper: LinearLayout
    //private lateinit var minutesWrapper: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_timer)

        btnPlayPause = findViewById(R.id.btn_play_pause)
        btnRestart = findViewById(R.id.btn_restart)
        minutesScroller = findViewById(R.id.minutes_view)
        minutesLeft = findViewById(R.id.minutes_left)
        secondsScroller = findViewById(R.id.seconds_view)
        secondsLeft = findViewById(R.id.seconds_left)
        updateRemainingTime()
        updateTextUI()

        minutesScroller.removeAllViews()
        secondsScroller.removeAllViews()

        minutesScroller.addView(minuteChipView())
        secondsScroller.addView(secondChipView())
        btnPlayPause.setOnClickListener {
            if (isRunning) {
                updateRemainingTime()
                pauseTimer()
                btnPlayPause.setImageResource(R.drawable.ic_play)
            } else {
                updateRemainingTime()
                startTimer(timeInMilliseconds)
                btnPlayPause.setImageResource(R.drawable.ic_pause)
            }
        }
        btnRestart.setOnClickListener {
            if (isRunning) pauseTimer()
            resetTimer()
        }
    }

    private fun pauseTimer() {
        timer.cancel()
        isRunning = false
    }


    private fun startTimer(time_in_seconds: Long) {
        timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                Toast.makeText(this@MainActivity, "time up hehe", Toast.LENGTH_SHORT).show()
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
        btnPlayPause.setImageResource(R.drawable.ic_play)
        timeInMilliseconds = initLength
        updateTextUI()
    }

    private fun updateTextUI() {
        val minutes = String.format("%02d",((timeInMilliseconds / 1000) / 60))
        val seconds = String.format("%02d",((timeInMilliseconds / 1000) % 60))
        secondsLeft.text = seconds
        minutesLeft.text = minutes
    }

    private fun updateRemainingTime() {
        val secondsToMs = secondsLeft.text.toString().toInt()*1000.toLong()
        val minutesToMs = minutesLeft.text.toString().toInt()*1000*60.toLong()
        timeInMilliseconds = minutesToMs + secondsToMs
    }

    private fun secondChipView(): View {
        val chipList = LinearLayout(this)
        chipList.orientation = LinearLayout.VERTICAL
        for (i in 0..59) {
            val second = TextView(this)
            second.text = String.format("%02d", i)
            second.textSize = 55.0F
            chipList.addView(second)
        }
        return chipList
    }

    private fun minuteChipView(): View {
        val chipList = LinearLayout(this)
        chipList.orientation = LinearLayout.VERTICAL
        for (i in 0..99) {
            val minute = TextView(this)
            minute.text = String.format("%02d", i)
            minute.textSize = 55.0F
            chipList.addView(minute)
        }
        return chipList
    }

}