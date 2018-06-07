package com.example.ayushgupta.ktmy13

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var iv1: ImageView? = null
    private var pb1: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv1 = findViewById(R.id.iv1)
        pb1 = findViewById(R.id.pb1)

        val filter = IntentFilter()
        filter.addAction("Image_Loading")
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val image = intent?.getParcelableExtra<Bitmap>("image")
                iv1?.setImageBitmap(image)
                pb1?.visibility = View.GONE
            }
        }, filter)
        val btn1: Button = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nm: NetworkInfo? = cm.activeNetworkInfo
            val isConnected = nm?.isConnectedOrConnecting == true
            if (isConnected) {
                pb1?.visibility = View.VISIBLE
                val i = Intent(this, MyService::class.java)
                startService(i)
            } else {
                Toast.makeText(this, "Turn on data/WiFi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
