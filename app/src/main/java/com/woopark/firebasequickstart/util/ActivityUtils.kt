package com.woopark.firebasequickstart.util

import android.app.Activity
import android.content.Intent
import com.woopark.firebasequickstart.signIn.SignInActivity

class ActivityUtils {
    companion object {
        fun startSignInActivity(activity: Activity) {
            if (activity !is SignInActivity) {
                val intent = Intent(activity, SignInActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity.startActivity(intent)
            }
        }
    }
}