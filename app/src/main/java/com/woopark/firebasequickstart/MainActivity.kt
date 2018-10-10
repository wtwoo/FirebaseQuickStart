package com.woopark.firebasequickstart

import android.os.Bundle
import com.woopark.firebasequickstart.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
