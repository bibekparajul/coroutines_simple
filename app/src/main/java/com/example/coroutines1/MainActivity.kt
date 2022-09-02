package com.example.coroutines1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var countMe = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvCount)
        val countButton = findViewById<Button>(R.id.btnCount)
        val downloadButton = findViewById<Button>(R.id.buttonDownload)

        countButton.setOnClickListener {
            textView.text = countMe++.toString()
        }
        downloadButton.setOnClickListener {

            // coroutines used here
            CoroutineScope(Dispatchers.IO).launch {
                downloadMe()

            }
        }
    }
// when the  dounload is clicked then the for loop executes and then during that time if the other process
    //are taking into execution then the app may crash or freez ...to overcome this coroutines are used...
    private suspend fun downloadMe() {
        for (i in 1..300000) {
            Log.i("Tagme", "Is downloading $i in ${Thread.currentThread().name}")
            delay(100)
        }
    }
}

