package com.woopark.firebasequickstart.signIn

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.woopark.firebasequickstart.R
import com.woopark.firebasequickstart.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : BaseActivity(), SignInContract.View, View.OnClickListener {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var presenter: SignInContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Button listeners
        signInButton.setOnClickListener(this)
        signOutButton.setOnClickListener(this)
        disconnectButton.setOnClickListener(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()

        presenter = SignInPresenter(this).apply {
            start()
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }

        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        presenter.firebaseAuthWith(credential)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    private fun signOut() {
        mAuth.signOut()

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            updateUI(null)
        }

    }

    private fun revokeAccess() {
        // Firebase sign out
        mAuth.signOut()

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this) {
            updateUI(null)
        }

    }

    override fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            status.text = getString(R.string.google_status_fmt, user.email)
            detail.text = getString(R.string.firebase_status_fmt, user.uid)

            signInButton.visibility = View.GONE
            signOutAndDisconnect.visibility = View.VISIBLE
        } else {
            status.setText(R.string.signed_out)
            detail.text = null

            signInButton.visibility = View.VISIBLE
            signOutAndDisconnect.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.signInButton -> signIn()
            R.id.signOutButton -> signOut()
            R.id.disconnectButton -> revokeAccess()
        }
    }

    companion object {
        private val TAG = SignInActivity::class.java.simpleName
        private const val RC_SIGN_IN = 9001
    }
}