package com.woopark.firebasequickstart.signIn

import android.app.Activity
import android.widget.Toast
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.woopark.rxfirebase.auth.RxFirebaseAuth
import io.reactivex.disposables.CompositeDisposable

class SignInPresenter(private val view: SignInContract.View)
    : SignInContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val auth: FirebaseAuth  by lazy {
        FirebaseAuth.getInstance()    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    private fun getActivity(): Activity {
        return view as Activity
    }

    override fun start() {
    }

    override fun firebaseAuthWith(authCredential : AuthCredential) {
        RxFirebaseAuth.signInWithCredential(auth, authCredential)
                .doOnSubscribe { view.showProgressDialog() }
                .doAfterTerminate { view.hideProgressDialog() }
                .subscribe({
                    view.updateUI(it)
                }, {
                    Toast.makeText(getActivity(), "AuthCredential failed.", Toast.LENGTH_SHORT).show()
                }).let { compositeDisposable.add(it) }
    }
}