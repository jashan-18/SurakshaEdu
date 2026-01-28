package com.example.surakshaedu.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.R

import com.google.firebase.auth.FirebaseAuth

class OtpVerificationActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        findViewById<Button>(R.id.btnVerified).setOnClickListener {
            auth.currentUser?.reload()?.addOnSuccessListener {
                if (auth.currentUser?.isEmailVerified == true) {
                    startActivity(Intent(this, RoleSelectionActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Email not verified yet", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
