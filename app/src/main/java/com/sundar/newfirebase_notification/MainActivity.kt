package com.sundar.newfirebase_notification

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics.getInstance
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessaging.getInstance
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var tittle = ""
    var message = ""

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                if (key == "tittle") {
                    tittle = intent.extras!!.getString("tittle", "")
                }
                if (key == "message") {
                    message = intent.extras!!.getString("message", "")
                }
            }
        }


        //New Firebase token generate

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d(TAG,"mytoken: " + token.toString())


            // Log and toast
          //  val msg = getString(R.string.msg_token_fmt, token)
          //  Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

        txt_tittle.text = tittle
        txt_discription.text = message

    }
}