package com.example.timely
import android.os.CountDownTimer

class Timer(private var seconds: Long) {
    // properties
    private var secondsLeft: Long = seconds
    private var isStarted: Boolean = false
    private var isPaused: Boolean = false

    fun intTimer(opt: String) {
        val millisecondsLeft = seconds * 1000
        val timer = object: CountDownTimer(millisecondsLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsLeft = millisUntilFinished/1000
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
                secondsLeft = seconds
            }
            else -> {
                print("no timer option selected")
            }
        }
    }
}
