package com.woopark.firebasequickstart.util

import com.woopark.firebasequickstart.BuildConfig

/**

 */
object Log {
    val TAG = "FIREBASE_QUICK_START"

    private val DEBUG = BuildConfig.DEBUG
    fun v(tag: String, msg: String) {
        if (DEBUG) {
            android.util.Log.v(TAG, "$tag.$msg")
        }
    }

    fun i(tag: String, msg: String) {
        if (DEBUG) {
            android.util.Log.i(TAG, "$tag.$msg")
        }
    }

    fun w(tag: String, msg: String) {
        if (DEBUG) {
            android.util.Log.w(TAG, "$tag.$msg")
        }
    }

    fun d(tag: String, msg: String) {
        if (DEBUG) {
            android.util.Log.d(TAG, "$tag.$msg")
        }
    }

    fun e(tag: String, msg: String) {
        if (DEBUG) {
            android.util.Log.e(TAG, "$tag.$msg")
        }
    }

    fun w(tag: String, msg: String, ex: Throwable) {
        if (DEBUG) {
            android.util.Log.w(TAG, "$tag.$msg", ex)
        }
    }


    fun d(tag: String, msg: String, ex: Throwable) {
        if (DEBUG) {
            android.util.Log.d(TAG, "$tag.$msg", ex)
        }
    }


    fun e(tag: String, msg: String, ex: Throwable) {
        if (DEBUG) {
            android.util.Log.e(TAG, "$tag.$msg", ex)
        }
    }
}