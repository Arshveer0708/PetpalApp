package com.GC200451204.petpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petpal.R
import com.example.petpal.databinding.ActivityStyledSigninBinding
import com.google.firebase.auth.FirebaseAuth

class StyledSigninActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStyledSigninBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStyledSigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SigninWithEmailButton.setOnClickListener {
            startActivity(Intent(this, EnterUserCredentialsActivity::class.java))
        }
    }
}