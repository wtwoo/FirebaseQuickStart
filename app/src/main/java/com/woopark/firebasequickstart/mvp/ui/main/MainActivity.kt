package com.woopark.firebasequickstart.mvp.ui.main

import android.os.Bundle
import com.woopark.firebasequickstart.R
import com.woopark.firebasequickstart.mvp.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
