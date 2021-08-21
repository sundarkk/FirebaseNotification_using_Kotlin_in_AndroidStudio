# FirebaseNotification_using_Kotlin_in_AndroidStudio
//Add INTERNER permission.
//Token generate Firebase

 FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d(TAG, "mytoken: " + token.toString())


            // Log and toast
            //  val msg = getString(R.string.msg_token_fmt, token)
            //  Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
