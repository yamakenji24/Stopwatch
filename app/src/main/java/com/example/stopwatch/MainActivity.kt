package com.example.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val handler = Handler()
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeText: TextView = findViewById<TextView>(R.id.timeText)
        val startButton: Button = findViewById<Button>(R.id.start) as Button
        val stopButton: Button = findViewById<Button>(R.id.stop)
        val resetButton: Button = findViewById<Button>(R.id.reset)

        val runnable = object : Runnable {
            override fun run() {
                timeValue++
                timeToText(timeValue)?.let {
                    timeText.text = it
                }
                handler.postDelayed(this, 1000)
            }
        }

        startButton.setOnClickListener {
            handler.post(runnable)
        }
        stopButton.setOnClickListener {
            handler.removeCallbacks(runnable)
        }
        resetButton.setOnClickListener {
            handler.removeCallbacks(runnable)
            timeValue = 0
            timeToText()?.let {
                timeText.text = it
            }
        }
    }

    private fun timeToText(time: Int = 0): String? {
        return if (time < 0) {
            null
        } else if ( time == 0) {
            "00:00:00"
        } else {
            val h = time/3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
}
