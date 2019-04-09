package com.woopark.firebasequickstart.mvp.utils

import android.app.Activity
import android.content.Intent
import com.woopark.firebasequickstart.mvp.ui.main.MainActivity
import com.woopark.firebasequickstart.mvp.ui.signIn.SignInActivity

class ActivityUtils {
    companion object {
        fun startSignInActivity(activity: Activity) {
            if (activity !is SignInActivity) {
                val intent = Intent(activity, SignInActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity.startActivity(intent)
            }
        }

        fun startMainActivity(activity: Activity) {
            if (activity !is MainActivity) {
                val intent = Intent(activity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity.startActivity(intent)
            }
        }
    }
}