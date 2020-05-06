package com.example.firstandmainwilliamson

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class NotificationService : LifecycleService() {
    val TAG = "MyService"
    var list: List<PromoItem> = listOf()
    override fun onBind(p0: Intent): IBinder? {
        super.onBind(p0)
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MY_CHANEL",
                "MY_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "MY_NOTIFICATION_CHANNEL_DESCRIPTION"
            mNotificationManager.createNotificationChannel(channel)
        }


        val difference: MutableList<PromoItem> = mutableListOf()
        getPromos().observe(this, Observer { it ->
            it.forEach { x ->
                if (!list.contains(x)) {
                    difference.add(x)
                }
            }
            list = it
            if (difference.size > 0) {
                val promo = difference.random()
                val builder = NotificationCompat.Builder(this, "MY_CHANEL")
                    .setContentTitle(promo.name)
                    .setContentText(promo.description)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("First and Main")
                    .setContentInfo("Information")
                mNotificationManager.notify(0, builder.build())
            }
            Log.d(TAG, "Deployed")
        })
        Log.d(TAG, "Setup Service")
        return super.onStartCommand(intent, flags, startId)
    }

    private fun getPromos(): MutableLiveData<List<PromoItem>> {
        val firestoreDB = FirebaseFirestore.getInstance()
        val storeCollection = firestoreDB.collection("promos")
        val savedPromos: MutableLiveData<List<PromoItem>> = MutableLiveData()
        storeCollection.addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                //savedPromos.value = null
                return@EventListener
            }
            val savedPromoList: MutableList<PromoItem> = mutableListOf()
            for (s in value!!) {
                val promoItem = s.toObject(PromoItem::class.java)
                savedPromoList.add(promoItem)
            }
            savedPromos.value = savedPromoList
        })
        return savedPromos
    }
}