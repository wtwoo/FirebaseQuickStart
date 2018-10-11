package com.woopark.firebasequickstart.mvp.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.woopark.firebasequickstart.R

open class BaseActivity : AppCompatActivity() {

    @VisibleForTesting
    val mProgressDialog by lazy {
        ProgressDialog(this)
    }

    fun showProgressDialog() {
        mProgressDialog.setMessage(getString(R.string.loading))
        mProgressDialog.isIndeterminate = true
        mProgressDialog.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }

    fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    public override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }
}