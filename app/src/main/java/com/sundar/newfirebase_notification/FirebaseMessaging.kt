package com.sundar.newfirebase_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import java.util.*

class FirebaseMessaging : FirebaseMessagingService() {
    lateinit var tittle: String
    lateinit var message: String
    lateinit var manager: NotificationManager
    var CHANNEL_ID = "CHANNEl"
    override fun onMessageReceived(remotemessage: RemoteMessage) {
        super.onMessageReceived(remotemessage)
       /* tittle = remotemessage.data["tittle"]!!
        message = remotemessage.data["message"]!!*/

         tittle = remotemessage.notification!!.title!!
        message = remotemessage.notification!!.body!!


        /*If you have any costomise data from backend, Like: Api data,
        * data = remotemessage.data.get("Your_keys")
        * */
/*
        if (message == null) {
            message = Objects.requireNonNull(remotemessage.notification!!.body)!!
        }*/

         manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        sendNotification()
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    private fun sendNotification() {
        var intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("tittle", tittle)
        intent.putExtra("message", message)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            var channel = NotificationChannel(
                CHANNEL_ID,
                "pushnotification",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(tittle)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentText(message)

        var pendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        builder.setContentIntent(pendingIntent)
        manager.notify(0, builder.build())

    }

  /*  fun getManger(): NotificationManager {
        var manager = this.getSystemService(Context.NOTIFICATION_SERVICE)
        return (manager as NotificationManager)
    }*/


}