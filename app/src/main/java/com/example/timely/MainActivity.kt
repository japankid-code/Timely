package com.example.timely

import android.app.Activity
import android.os.Bundle
import com.example.timely.databinding.ActivityTimerBinding

public class MainActivity : Activity()  {

    private lateinit var binding: ActivityTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}