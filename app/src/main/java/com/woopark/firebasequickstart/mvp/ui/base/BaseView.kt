package com.woopark.firebasequickstart.mvp.ui.base

interface BaseView<T> {
    var presenter: T
    fun showProgressDialog()
    fun hideProgressDialog()
}
