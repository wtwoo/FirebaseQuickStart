package com.woopark.firebasequickstart.mvp.ui.signIn

import android.app.Activity
import android.widget.Toast
import com.androidhuman.rxfirebase2.auth.RxFirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.woopark.firebasequickstart.R
import io.reactivex.disposables.CompositeDisposable

class SignInPresenter(private val view: SignInContract.View)
    : SignInContract.Presenter {
    private val compositeDisposable by lazy { CompositeDisposable() }
    private val mGoogleSignInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(getActivity(), gso)
    }

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getActivity().getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    private val auth: FirebaseAuth  by lazy {
        FirebaseAuth.getInstance()
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    private fun getActivity(): Activity {
        return view as Activity
    }

    override fun start() {
    }

    override fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        getActivity().startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    /**
     * Google sign out
     */
    override fun signOut() {
        auth.signOut()

        mGoogleSignInClient.signOut().addOnCompleteListener(getActivity()) {
            view.updateUI(null)
        }
    }

    /**
     * Firebase sign out
     * Google revoke access
     */
    override fun revokeAccess() {
        auth.signOut()

        mGoogleSignInClient.revokeAccess().addOnCompleteListener(getActivity()) {
            view.updateUI(null)
        }
    }


    override fun firebaseAuthWith(authCredential: AuthCredential) {
        RxFirebaseAuth.signInWithCredential(auth, authCredential)
                .doOnSubscribe { view.showProgressDialog() }
                .doAfterTerminate { view.hideProgressDialog() }
                .subscribe({
                    view.updateUI(it)
                }, {
                    Toast.makeText(getActivity(), "AuthCredential failed.", Toast.LENGTH_SHORT).show()
                }).let { compositeDisposable.add(it) }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}