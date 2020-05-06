package com.example.firstandmainwilliamson

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent?) {
        val i: Intent = Intent(p0, NotificationService::class.java)
        p0.startForegroundService(i)
        Log.d("MyBroadcastReceiver: ", "Started")
    }
}