package com.example.timely
import android.app.Activity
import android.os.CountDownTimer

class Timer(private var s: Double = 0.0) : Activity() {
    // properties
    private var seconds = s
    var secondsLeft: Int = (seconds % 60).toInt()
    var minutesLeft: Int = kotlin.math.floor(seconds/60).toInt()
    private var isStarted: Boolean = false
    private var isPaused: Boolean = false


    fun setSecondsLeftTo(seconds: Int) {
        secondsLeft = seconds
    }

    fun timerHandler(opt: String) {
        val millisecondsLeft = seconds.toLong() * 1000
        val timer = object: CountDownTimer(millisecondsLeft, 1000) {
            override fun onTick(msTilFinished: Long) {
                seconds = (msTilFinished.toDouble()/1000)
                setSecondsLeftTo(kotlin.math.ceil((msTilFinished.toDouble()/1000) % 60).toInt())
                minutesLeft = kotlin.math.floor(seconds/60).toInt()
            }
            override fun onFinish() {
                // add alert calls here
            }
        }

        when (opt) {
            "start" -> {
                isStarted = true
                isPaused = false
                timer.start()
            }
            "pause" -> {
                isPaused = true
                timer.cancel()
            }
            "reset" -> {
                isStarted = false
                isPaused = true
                seconds = s
            }
            else -> {
                print("no timer option selected")
            }
        }
    }

}
