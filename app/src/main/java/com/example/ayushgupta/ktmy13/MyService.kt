package com.example.ayushgupta.ktmy13

import android.app.IntentService
import android.content.Intent
import android.graphics.BitmapFactory
import java.net.URL

class MyService : IntentService("Myservice") {
    override fun onHandleIntent(intent: Intent?) {
        val url = URL("https://picsum.photos/300/400/?random")
        val isp = url.openStream()
        val bmp = BitmapFactory.decodeStream(isp)
        val i = Intent()
        i.putExtra("image", bmp)
        i.action = "Image_Loading"
        sendBroadcast(i)
    }
}